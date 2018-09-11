package me.phoibe.doc.cms.service;

import me.phoibe.doc.cms.domain.po.PhoibeUser;

public interface PhoibeUserService {

    PhoibeUser login(PhoibeUser phoibeUser) throws Exception;
}
