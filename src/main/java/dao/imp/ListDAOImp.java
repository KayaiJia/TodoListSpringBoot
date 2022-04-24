package dao.imp;

import dao.ListDAO;
import dao.ThingDAO;
import dao.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.List;
import pojo.Thing;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 23:17
 */
public class ListDAOImp implements ListDAO {
    private Connection connection;
    private ThingDAO thingDAO = null;

    public ListDAOImp(){
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加list
     *
     * @param list : 要添加的list
     * @author kayai
     * @date 2022/4/23 18:26
     */
    @Override
    public void addList(List list) throws SQLException {
        String sql  = "INSERT INTO `list`(list_name,user_id) " +
                "VALUES(?,?)";
        QueryRunner runner = new QueryRunner();
        runner.update(connection,sql,list.getList_name(),list.getUser_id());
    }

    /**
     * 修改list
     *
     * @param list : 要修改的内容
     * @author kayai
     * @date 2022/4/23 18:27
     */
    @Override
    public void updateList(List list) throws SQLException {
        String sql = "UPDATE `list` " +
                "SET list_name = ? " +
                "WHERE _id = ?";
        QueryRunner runner = new QueryRunner();
        runner.update(connection,sql,list.getList_name(),list.get_id());
    }

    /**
     * 删除list
     *
     * @param id : 要删除的id
     * @author kayai
     * @date 2022/4/23 18:27
     */
    @Override
    public void delList(int id) throws SQLException {
        String sql = "DELETE FROM `list` WHERE _id = ?";
        QueryRunner runner = new QueryRunner();
        runner.update(connection,sql,id);
    }

    /**
     * 查找单个list
     *
     * @param id : 要查询的list的id
     * @return pojo.List
     * @author kayai
     * @date 2022/4/23 18:28
     */
    @Override
    public List queryList(int id) throws SQLException {
        String sql = "SELECT * FROM `list` WHERE _id = ?";
        QueryRunner runner = new QueryRunner();
        List query = runner.query(connection, sql, new BeanHandler<>(List.class), id);
        String sql1 = "SELECT things_id FROM things_map WHERE map_id = ? AND classify = ?";
        java.util.List<Object[]> things_id = runner.query(connection, sql1, new ArrayListHandler(), query.get_id(), 0);
        Iterator<Object[]> iterator = things_id.iterator();
        while (iterator.hasNext()){
            String sql2 = "SELECT * FROM things WHERE _id = ?";
            Thing thing = thingDAO.queryThing((Integer) iterator.next()[0]);
            query.setThings(thing);
        }
        return query;
    }

    /**
     * 获取用户全部的list
     *
     * @param id : 要查找的用户的id
     * @return java.util.List<pojo.List>
     * @author kayai
     * @date 2022/4/23 18:29
     */
    @Override
    public java.util.List<List> queryListALl(int id) throws SQLException {
        String sql = "SELECT * FROM `list` WHERE user_id = ?";
        QueryRunner runner = new QueryRunner();
        java.util.List<List> query = runner.query(connection, sql, new BeanListHandler<>(List.class), id);
        String sql1 = "SELECT things_id FROM things_map WHERE map_id = ? AND classify = ?";
        for (int i=0;i<query.size();i++){
            java.util.List<Object[]> objs = runner.query(connection, sql1, new ArrayListHandler(), query.get(i).get_id(), 0);
            Iterator<Object[]> iterator = objs.iterator();
            while (iterator.hasNext()){
                Thing thing = thingDAO.queryThing((Integer) iterator.next()[0]);
                query.get(i).setThings(thing);
            }
        }
        return query;
    }
}
