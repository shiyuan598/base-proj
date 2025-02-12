package com.base.vm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.vm.entity.VUser;
import com.base.vm.mapper.UserMapper;
import com.base.vm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, VUser>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public VUser findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public VUser registerUser(VUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 保存用户信息到数据库
        userMapper.insert(user);
        return user;
    }

    @Override
    public boolean forgetPassword(String username, String telephone, String newPassword) {
        LambdaQueryWrapper<VUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VUser::getUsername, username).eq(VUser::getTelephone, telephone);
        VUser user = getOne(wrapper);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return updateById(user);
        }
        return false;
    }
}
