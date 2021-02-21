package com.oy.scw.user.service;

import com.oy.scw.user.bean.TMember;
import com.oy.scw.user.bean.TMemberAddress;

import java.util.List;

public interface TMemberService {
    TMember getMemberById(Integer id);

    List<TMemberAddress> listAddress(Integer memberId);
}
