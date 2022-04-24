package dao.imp;

import dao.MapDAO;
import dao.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.ThingsMap;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/24 01:15
 */
public class MapDAOImp implements MapDAO {
    private Connection connection;

    public MapDAOImp(){
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加数据
     *
     * @param thingsMap : 添加的内容
     * @author kayai
     * @date 2022/4/23 20:02
     */
    @Override
    public void add(ThingsMap thingsMap) throws SQLException {
        String sql = "INSERT INTO things_map(things_id,classify,map_id) " +
                "VALUES(?,?,?)";
        QueryRunner runner = new QueryRunner();
        runner.update(connection,sql,thingsMap.getThings_id(),thingsMap.getClassify(),thingsMap.getMap_id());
    }

    /**
     * 更新
     *
     * @param thingsMap : 要更新的内容
     * @author kayai
     * @date 2022/4/24 01:20
     */
    @Override
    public void update(ThingsMap thingsMap) throws SQLException {
        String sql = "UPDATE things_map " +
                "SET classify = ?," +
                "map_id = ?" +
                "WHERE _id = ?";
        QueryRunner runner = new QueryRunner();
        runner.update(connection,sql,thingsMap.getClassify(),thingsMap.getMap_id(),thingsMap.get_id());
    }

    /**
     * 删除
     *
     * @param id : 删除的id
     * @author kayai
     * @date 2022/4/23 20:03
     */
    @Override
    public void del(int id) throws SQLException {
        String sql = "DELETE FROM things_map WHERE _id = ?";
        QueryRunner runner = new QueryRunner();
        runner.update(connection,sql,id);
    }

    /**
     * 查询
     *
     * @param id : 要查询的map_id
     * @param classify:类型
     * @return pojo.ThingsMap
     * @author kayai
     * @date 2022/4/23 20:04
     */
    @Override
    public List<ThingsMap> query(int id,int classify) throws SQLException {
        String sql = "SELECT * FROM things_map WHERE map_id = ? AND classify = ?";
        QueryRunner runner = new QueryRunner();
        return runner.query(connection,sql,new BeanListHandler<ThingsMap>(ThingsMap.class),id,classify);
    }
}
