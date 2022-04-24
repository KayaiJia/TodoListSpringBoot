package dao;

import pojo.List;

import java.sql.SQLException;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 18:26
 */
public interface ListDAO {
    /**
     * 添加list
     * @author kayai
     * @date 2022/4/23 18:26
     * @param list: 要添加的list
     */
    public void addList(List list) throws SQLException;

    /**
     * 修改list
     * @author kayai
     * @date 2022/4/23 18:27
     * @param list: 要修改的内容
     */
    public void updateList(List list) throws SQLException;

    /**
     * 删除list
     * @author kayai
     * @date 2022/4/23 18:27
     * @param id: 要删除的id
     */
    public void delList(int id) throws SQLException;

    /**
     * 查找单个list
     * @author kayai
     * @date 2022/4/23 18:28
     * @param id: 要查询的list的id
     * @return pojo.List
     */
    public List queryList(int id) throws SQLException;

    /**
     * 获取用户全部的list
     * @author kayai
     * @date 2022/4/23 18:29
     * @param id: 要查找的用户的id
     * @return java.util.List<pojo.List>
     */
    public java.util.List<List> queryListALl(int id) throws SQLException;
}
