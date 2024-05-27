package com.whj.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whj.pojo.User;
import com.whj.service.UserService;
import com.whj.mapper.UserMapper;
import com.whj.utils.JwtHelper;
import com.whj.utils.MD5Util;
import com.whj.utils.Result;
import com.whj.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author 王贺杰
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-05-24 20:42:02
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 登录业务
     * 1. 根据账号查询用户
     * 2. 如果账号为空，查询失败，返回501账号错误
     * 3. 对比密码，如果失败，返回503密码错误
     * 4. 如果成功，根据用户Id生成一个token，将token装入result并返回
     * @param user
     * @return
     */
    @Override
    public Result login(User user) {

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);
        if(loginUser == null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        if(!StringUtils.isEmpty(user.getUserPwd())
                && MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())){
            //登录成功

            //根据用户id生成token
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            //将token封装到result并返回
            Map data = new HashMap();
            data.put("token",token);
            return Result.ok(data);
        }
        //密码错误
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    /**
     * 根据token获取用户数据
     *
     * 1. 校验token是否在有效期，不在有效期返回504
     * 2. 根据token解析出用户ID
     * 3. 根据用户ID返回用户数据
     * 4. 去掉密码，封装result结果返回即可
     *
     *
     * @param token
     * @return
     */
    @Override
    public Result getUserInfo(String token) {

        //1. 校验token是否在有效期，不在有效期返回504
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        //2. 根据token解析出用户ID
        int userId = jwtHelper.getUserId(token).intValue();

        User user = userMapper.selectById(userId);
        user.setUserPwd("");

        Map data = new HashMap<>();
        data.put("loginUser",user);

        return Result.ok(data);
    }

    /**
     * 检查账号名是否被占用
     *
     * 1. 根据账号名查询数据库
     * 2. 如果账号名被占用，则不可用，如果查询结果为空则可用
     * @param username
     * @return
     */
    @Override
    public Result checkUserName(String username) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        Long count = userMapper.selectCount(queryWrapper);

        if (count == 0) {
            return Result.ok(null);
        }

        return Result.build(null, ResultCodeEnum.USERNAME_USED);
    }

    /**
     * 用户注册
     * 1. 检查用户名是否被占用
     * 2. 密码加密处理
     * 3. 将账号数据保存
     * 4. 返回结果
     * @param user
     * @return
     */
    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        Long count = userMapper.selectCount(queryWrapper);

        if (count > 0) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        userMapper.insert(user);
        return Result.ok(null);
    }
}




