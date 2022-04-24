package dao.imp;

import dao.UserDAO;
import dao.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 20:06
 */
public class UserDAOImp implements UserDAO {
    private static Connection connection;
    public UserDAOImp(){
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 添加用户
     *
     * @param user : 要添加的用户
     * @author kayai
     * @date 2022/4/23 18:05
     */
    @Override
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO `user`(nic_name,open_id) " +
                "VALUES(?,?)";
        QueryRunner runner = new QueryRunner();
        runner.update(connection,sql,user.getNic_name(),user.getOpen_id());
    }

    /**
     * 修改指定User的内容
     *
     * @param user : 更改的内容
     * @author kayai
     * @date 2022/4/23 18:05
     */
    @Override
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE `user` " +
                "SET nic_name = ? " +
                "WHERE _id = ?";
        QueryRunner runner = new QueryRunner();
        runner.update(connection,sql,user.getNic_name(),user.get_id());
    }

    /**
     * 根据ID查找用户
     *
     * @param openid : 要查找的id
     * @return pojo.User
     * @author kayai
     * @date 2022/4/23 18:08
     */
    @Override
    public User queryUser(String openid) throws SQLException {
        String sql = "SELECT * FROM `user` WHERE open_id =?";
        QueryRunner runner = new QueryRunner();
        return runner.query(connection,sql,new BeanHandler<User>(User.class),openid);
    }

    @Override
    public User queryUser(int id) throws SQLException {
        String sql = "SELECT * FROM `user` WHERE _id =?";
        QueryRunner runner = new QueryRunner();
        return runner.query(connection,sql,new BeanHandler<User>(User.class),id);
    }
}
