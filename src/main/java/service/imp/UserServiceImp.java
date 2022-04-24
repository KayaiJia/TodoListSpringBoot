package service.imp;

import dao.UserDAO;
import pojo.User;
import service.UserService;

import java.sql.SQLException;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/24 14:12
 */
public class UserServiceImp implements UserService {
    private final UserDAO userDAO = null;
    /**
     * 登录
     *
     * @param openid : 要登录的openid
     * @return pojo.User
     * @author kayai
     * @date 2022/4/24 14:10
     */
    @Override
    public User login(String openid) throws SQLException {
        return userDAO.queryUser(openid);
    }

    /**
     * 注册
     *
     * @param user : 注册的用户信息
     * @author kayai
     * @date 2022/4/24 14:11
     */
    @Override
    public void signUp(User user) throws SQLException {
        userDAO.addUser(user);
    }
}
