package com.oy.scw.user.service;

import com.oy.scw.user.vo.rep.UserRegistVo;
import com.oy.scw.user.vo.resp.UserRespVo;

/**
 * @Author OY
 * @Date 2021/2/12
 */
public interface MemberService {
    int saveTMenber(UserRegistVo vo);

    UserRespVo getUserByLogin(String loginacct, String password);
}
