package service.imp;

import dao.ListDAO;
import dao.UserDAO;
import pojo.List;
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
    private ListDAO listDAO = null;
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
        user=userDAO.queryUser(user.getOpen_id());
        List inbox = new List("收集箱", user.get_id());
        listDAO.addList(inbox);
        List todo = new List("执行清单", user.get_id());
        listDAO.addList(todo);
        List wait = new List("等待清单", user.get_id());
        listDAO.addList(wait);
        List maybe = new List("可能清单", user.get_id());
        listDAO.addList(maybe);
        List done = new List("已完成", user.get_id());
        listDAO.addList(done);
    }
}
