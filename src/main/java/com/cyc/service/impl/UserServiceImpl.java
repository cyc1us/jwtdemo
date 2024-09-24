package com.cyc.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyc.pojo.User;
import com.cyc.service.UserService;
import com.cyc.mapper.UserMapper;
import com.cyc.utils.JwtHelper;
import com.cyc.utils.MD5Util;
import com.cyc.utils.Result;
import com.cyc.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
* @author chenyc53360
* @description 针对表【news_user】的数据库操作Service实现
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(queryWrapper);
        if (loginUser == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        //密码正确
        if(!StringUtils.isEmpty(user.getUserPwd()) &&
                MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())){
             String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));

            Map data = new HashMap();
            data.put("token", token);

            return Result.ok(data);
        }
        //密码错误
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {
        //检查token是否过期
        boolean expiration = jwtHelper.isExpiration(token);

        //失效 当作未登录
        if(expiration){
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        int userId = jwtHelper.getUserId(token).intValue();
        User user = userMapper.selectById(userId);
        user.setUserPwd("");

        Map data = new HashMap();
        data.put("loginUser", user);

        return Result.ok(data);

    }

    @Override
    public Result checkUserName(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userName);
        Long count = userMapper.selectCount(queryWrapper);

        if (count == 0){
            return Result.ok(null);
        }
        return Result.build(null, ResultCodeEnum.USERNAME_USED);
    }

    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        Long count = userMapper.selectCount(queryWrapper);

        if (count > 0){
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        userMapper.insert(user);


        return Result.ok(null);
    }
}




