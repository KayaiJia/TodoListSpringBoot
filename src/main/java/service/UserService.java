package service;

import pojo.User;

import java.sql.SQLException;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/24 14:08
 */
public interface UserService {
    /**
     * 登录
     * @author kayai
     * @date 2022/4/24 14:10
     * @param openid: 要登录的openid
     * @return pojo.User
     */
    public User login(String openid) throws SQLException;

    /**
     * 注册
     * @author kayai
     * @date 2022/4/24 14:11
     * @param user: 注册的用户信息
     */
    public void signUp(User user) throws SQLException;
}
