/*
 * 修改记录:
 * 修改日期     修改人员    修改说明
 * ========    =======  ============================================
 * 2024/6/27  chenyc      新增
 * ========    =======  ============================================
 */
package com.cyc.controller;

import com.alibaba.druid.util.StringUtils;
import com.cyc.pojo.User;
import com.cyc.service.UserService;
import com.cyc.utils.JwtHelper;
import com.cyc.utils.Result;
import com.cyc.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        Result result = userService.login(user);
        return result;
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader String token) {
        Result result = userService.getUserInfo(token);
        return result;
    }

    @PostMapping("/checkUserName")
    public Result checkUserName(String userName) {
        Result result = userService.checkUserName(userName);
        return result;
    }

    @PostMapping("/regist")
    public Result regist(@RequestBody User user) {
        Result result = userService.regist(user);
        return result;
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)){
            //没有传或者过期 未登录
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        return Result.ok(null);
    }

}
