package service;

import pojo.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/24 14:31
 */
public interface ListService {
    /**
     * 获取用户列表
     * @author kayai
     * @date 2022/4/24 14:31
     * @param user: 用户类
     * @return java.util.List<pojo.List>
     */
    public List<pojo.List> getList(User user) throws SQLException;

    /**
     * 添加List
     * @author kayai
     * @date 2022/4/26 15:53
     * @param list: 要添加的list
     * @return void
     */
    public void addList(pojo.List list) throws SQLException;

    /**
     * 获取用户列表里事件的个数
     * @author kayai
     * @date 2022/4/26 17:00
     * @param user_id: 要查询的用户
     * @return int
     */
    public int[] getCount(String user_id) throws SQLException;
}
