package service.imp;

import dao.ListDAO;
import pojo.List;
import pojo.User;
import service.ListService;

import java.sql.SQLException;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/24 14:37
 */
public class ListServiceImp implements ListService {
    private ListDAO listDAO = null;
    /**
     * 获取用户列表
     *
     * @param user : 用户的类
     * @return java.util.List<pojo.List>
     * @author kayai
     * @date 2022/4/24 14:31
     */
    @Override
    public java.util.List<List> getList(User user) throws SQLException {
        return listDAO.queryListALl(user.get_id());
    }
}
