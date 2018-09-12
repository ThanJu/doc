package me.phoibe.doc.cms.service;

import me.phoibe.doc.cms.domain.dto.UserInfo;
import me.phoibe.doc.cms.domain.po.PhoibeRole;
import me.phoibe.doc.cms.domain.po.PhoibeUser;

public interface PhoibeUserService {

    PhoibeUser login(PhoibeUser phoibeUser);

    PhoibeRole fetchUserRoleByUserId(Long userId);

    UserInfo fetchUserInfoByUserId(Long userId);
}
