package com.oy.scw.user.service.impl;


import com.oy.scw.user.bean.TMember;
import com.oy.scw.user.bean.TMemberExample;
import com.oy.scw.user.enums.UserExceptionEnum;
import com.oy.scw.user.exp.UserException;
import com.oy.scw.user.mapper.TMemberMapper;
import com.oy.scw.user.service.MemberService;
import com.oy.scw.user.vo.rep.UserRegistVo;
import com.oy.scw.user.vo.resp.UserRespVo;
import com.sun.javafx.tk.TKSystemMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author OY
 * @Date 2021/2/12
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    @Autowired
    TMemberMapper tMemberMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    /**
     *  @Transactional 只有才异常是运行时异常才会回滚
     * @param vo
     * @return
     */
//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = RuntimeException.class)
    @Transactional
    @Override
    public int saveTMenber(UserRegistVo vo) {

        try {
            // 将vo属性对拷到do 对象中
            TMember member = new TMember();
            BeanUtils.copyProperties(vo,member);
            member.setUsername(vo.getLoginacct());

            String userpswd = vo.getUserpswd();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            member.setUserpswd(encoder.encode(userpswd));

            int i = tMemberMapper.insertSelective(member);
            log.debug("注册会员-{}",member);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("注册会员失败-{}",e.getMessage());
            throw new UserException(UserExceptionEnum.USER_SAVE_ERROR);
        }

    }

    @Override
    public UserRespVo getUserByLogin(String loginacct, String password) {
        UserRespVo vo = new UserRespVo();

        TMemberExample example = new TMemberExample();
        example.createCriteria().andLoginacctEqualTo(loginacct);

        List<TMember> list = tMemberMapper.selectByExample(example);

        if(list== null || list.size() == 0){
            throw new UserException(UserExceptionEnum.USER_UNEXISTS);
        }

        TMember member = list.get(0);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(password,member.getUserpswd())){
            throw new UserException(UserExceptionEnum.USER_PASSWORD_ERROR);
        }

        BeanUtils.copyProperties(member,vo);

        String accessToken  = UUID.randomUUID().toString().replaceAll("-","");
        vo.setAccessToken(accessToken);

        stringRedisTemplate.opsForValue().set(accessToken,member.getId().toString());

        return vo;
    }
}
