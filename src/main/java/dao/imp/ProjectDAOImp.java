package dao.imp;

import dao.ProjectDAO;
import dao.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Project;
import pojo.Thing;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 20:54
 */
public class ProjectDAOImp implements ProjectDAO {
    private Connection connection;
    private ThingDAOImp thingDAOImp = null;
    public ProjectDAOImp(){
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 添加project
     *
     * @param project : 要添加的Project
     * @author kayai
     * @date 2022/4/23 18:16
     */
    @Override
    public void addProject(Project project) throws SQLException {
        String sql = "INSERT INTO project(project_name,user_id)" +
                "VALUES(?,?)";
        QueryRunner runner = new QueryRunner();
        runner.update(connection,sql,project.getProject_name(),project.getUser_id());
    }

    /**
     * 修改project
     *
     * @param project : 要修改的内容
     * @author kayai
     * @date 2022/4/23 18:19
     */
    @Override
    public void updateProject(Project project) throws SQLException {
        String sql = "UPDATE project " +
                "SET project_name = ?" +
                "WHERE _id = ?";
        QueryRunner runner =new QueryRunner();
        runner.update(connection,sql,project.getProject_name(),project.get_id());
    }

    /**
     * 根据id删除projcet
     *
     * @param id : 删除的id
     * @author kayai
     * @date 2022/4/23 18:19
     */
    @Override
    public void delProject(int id) throws SQLException {
        String sql = "DELETE FROM project WHERE _id = ?";
        QueryRunner runner = new QueryRunner();
        runner.update(connection,sql,id);
    }

    /**
     * 查找单个project
     *
     * @param id : 查找的id
     * @return pojo.Project
     * @author kayai
     * @date 2022/4/23 18:20
     */
    @Override
    public Project queryProject(int id) throws SQLException {
        String sql = "SELECT * FROM project WHERE _id = ?";
        QueryRunner runner = new QueryRunner();
        Project query = runner.query(connection, sql, new BeanHandler<>(Project.class), id);
        String sql1 = "SELECT things_id FROM things_map WHERE map_id = ? AND classify = ?";
        List<Object[]> things_id = runner.query(connection, sql1, new ArrayListHandler(), query.get_id(), 1);
        Iterator<Object[]> iterator = things_id.iterator();
        while (iterator.hasNext()){
            String sql2 = "SELECT * FROM things WHERE _id = ?";
            Thing thing = thingDAOImp.queryThing((Integer) iterator.next()[0]);
            query.setThings(thing);
        }
        return query;
    }

    /**
     * @param name : 项目名字
     * @param id   : 用户id
     * @return pojo.Project
     * @author kayai
     * @date 2022/4/26 21:55
     */
    @Override
    public Project queryProject(String name, int id) throws SQLException {
        String sql = "SELECT * FROM project WHERE user_id = ? AND project_name = ?";
        QueryRunner runner = new QueryRunner();
        Project query = runner.query(connection, sql, new BeanHandler<>(Project.class), id,name);
        String sql1 = "SELECT things_id FROM things_map WHERE map_id = ? AND classify = ?";
        List<Object[]> things_id = runner.query(connection, sql1, new ArrayListHandler(), query.get_id(), 1);
        Iterator<Object[]> iterator = things_id.iterator();
        while (iterator.hasNext()){
            String sql2 = "SELECT * FROM things WHERE _id = ?";
            Thing thing = thingDAOImp.queryThing((Integer) iterator.next()[0]);
            query.setThings(thing);
        }
        return query;
    }


    /**
     * 根据用户查找所有项目
     *
     * @param id : 要查找的用户id
     * @return java.util.List<pojo.Project>
     * @author kayai
     * @date 2022/4/23 18:22
     */
    @Override
    public List<Project> queryProjectAll(int id) throws SQLException {
        String sql = "SELECT * FROM project WHERE user_id = ?";
        QueryRunner runner = new QueryRunner();
        List<Project> query = runner.query(connection, sql, new BeanListHandler<>(Project.class), id);
        String sql1 = "SELECT things_id FROM things_map WHERE map_id = ? AND classify = ?";
        for (int i=0;i<query.size();i++){
            List<Object[]> objs = runner.query(connection, sql1, new ArrayListHandler(), query.get(i).get_id(), 1);
            Iterator<Object[]> iterator = objs.iterator();
            while (iterator.hasNext()){
                Thing thing = thingDAOImp.queryThing((Integer) iterator.next()[0]);
                query.get(i).setThings(thing);
            }
        }
        return query;
    }

}
