package me.phoibe.doc.cms.service.impl;

import me.phoibe.doc.cms.dao.PhoibeUserMapper;
import me.phoibe.doc.cms.domain.po.PhoibeUser;
import me.phoibe.doc.cms.exception.BusinessException;
import me.phoibe.doc.cms.service.PhoibeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PhoibeUserServiceImpl implements PhoibeUserService {

    @Autowired
    private PhoibeUserMapper phoibeUserMapper;

    @Override
    public PhoibeUser login(PhoibeUser phoibeUser) {
        if(StringUtils.isEmpty(phoibeUser.getUserName())){
            throw new BusinessException("用户名不能为空");
        }
        if(StringUtils.isEmpty(phoibeUser.getPassword())){
            throw new BusinessException("密码不能为空");
        }
        phoibeUser.setStatus(Short.valueOf("1"));
        PhoibeUser phoibeUser1 = phoibeUserMapper.selectByParam(phoibeUser);
        return phoibeUser1;
    }
}
