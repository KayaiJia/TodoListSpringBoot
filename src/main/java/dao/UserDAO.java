package dao;

import pojo.User;

import java.sql.SQLException;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 18:03
 */
public interface UserDAO {
    /**
     * 添加用户
     * @author kayai
     * @date 2022/4/23 18:05
     * @param user: 要添加的用户
     */
    public void addUser(User user) throws SQLException;

    /**
     * 修改指定User的内容
     * @author kayai
     * @date 2022/4/23 18:05
     * @param user: 更改的内容
     */
    public void updateUser(User user) throws SQLException;

    /**
     * 根据ID查找用户
     * @author kayai
     * @date 2022/4/23 18:08
     * @param openid: 要查找的id
     * @return pojo.User
     */
    public User queryUser(String openid) throws SQLException;

    public User queryUser(int id) throws SQLException;
}
