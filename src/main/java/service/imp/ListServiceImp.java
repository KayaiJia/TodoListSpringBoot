package service.imp;

import dao.ListDAO;
import dao.MapDAO;
import dao.UserDAO;
import pojo.List;
import pojo.ThingsMap;
import pojo.User;
import service.ListService;

import java.sql.SQLException;
import java.util.Iterator;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/24 14:37
 */
public class ListServiceImp implements ListService {
    //Todo 修改为null
    private ListDAO listDAO = null;
    private UserDAO userDAO = null;
    private MapDAO mapDAO = null;
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

    /**
     * 添加List
     *
     * @param list : 要添加的list
     * @return void
     * @author kayai
     * @date 2022/4/26 15:53
     */
    @Override
    public void addList(List list) throws SQLException {
        listDAO.addList(list);
    }

    /**
     * 获取用户列表里事件的个数
     *
     * @param user_id : 要查询的用户
     * @return int
     * @author kayai
     * @date 2022/4/26 17:00
     */
    @Override
    public int[] getCount(String user_id) throws SQLException {
        User user = userDAO.queryUser(user_id);
        int [] count = new int[5];
        int i = 0;
        java.util.List<List> lists = listDAO.queryListALl(user.get_id());
        Iterator<List> iterator = lists.iterator();
        while (iterator.hasNext()){
            java.util.List<ThingsMap> query = mapDAO.query(iterator.next().get_id(), 0);
            count[i++] = query.size();
        }
        return count;
    }
}
