package com.cyc.service;

import com.cyc.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyc.utils.Result;

/**
* @author chenyc53360
* @description 针对表【news_user】的数据库操作Service
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String userName);

    Result regist(User user);
}
