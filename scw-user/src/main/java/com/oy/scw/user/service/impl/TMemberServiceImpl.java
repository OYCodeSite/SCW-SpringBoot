package com.oy.scw.user.service.impl;

import com.oy.scw.user.bean.TMember;
import com.oy.scw.user.bean.TMemberAddress;
import com.oy.scw.user.bean.TMemberAddressExample;
import com.oy.scw.user.mapper.TMemberAddressMapper;
import com.oy.scw.user.mapper.TMemberMapper;
import com.oy.scw.user.service.TMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author OY
 * @Date 2021/2/18
 */
@Service
public class TMemberServiceImpl implements TMemberService {
    @Autowired
    TMemberMapper memberMapper;

    @Autowired
    TMemberAddressMapper memberAddressMapper;

    @Override
    public TMember getMemberById(Integer id) {
        TMember member = memberMapper.selectByPrimaryKey(id);
        member.setUserpswd(null);
        return member;
    }

    @Override
    public List<TMemberAddress> listAddress(Integer memberId) {
        TMemberAddressExample example = new TMemberAddressExample();
        example.createCriteria().andMemberidEqualTo(memberId);
        List<TMemberAddress> list = memberAddressMapper.selectByExample(example);
        return list;
    }
}
