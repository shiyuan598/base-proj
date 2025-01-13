package com.base.vm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.vm.entity.VUser;
import com.base.vm.mapper.UserMapper;
import com.base.vm.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, VUser>
        implements UserService {

}
