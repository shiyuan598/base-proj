package com.base.vm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.base.vm.entity.VUser;

public interface UserService extends IService<VUser> {
    VUser findByUsername(String username);
    VUser registerUser(VUser user);
}