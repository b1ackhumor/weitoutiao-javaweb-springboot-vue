package com.whj.service;

import com.whj.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.whj.utils.Result;

/**
* @author 王贺杰
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-05-24 20:42:02
*/
public interface UserService extends IService<User> {

    /**
     * 登录业务
     * @param user
     * @return
     */
    Result login(User user);

    /**
     * 根据token获取用户数据
     * @param token
     * @return
     */
    Result getUserInfo(String token);

    /**
     * 检查账号名是否被占用
     * @param username
     * @return
     */

    Result checkUserName(String username);

    /**
     * 用户注册
     * @param user
     * @return
     */
    Result regist(User user);
}
