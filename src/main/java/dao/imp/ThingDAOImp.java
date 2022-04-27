package dao.imp;

import dao.ThingDAO;
import dao.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Thing;
import pojo.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 20:30
 */
public class ThingDAOImp implements ThingDAO {
    private Connection connection;

    public ThingDAOImp(){
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加事件
     *
     * @param thing : 要添加的事件
     * @author kayai
     * @date 2022/4/23 18:10
     */
    @Override
    public void addThing(Thing thing) throws SQLException {
        String sql = "INSERT INTO things(title,tag,`describe`,remark,remind_time,reminder_date,done,`user`)" +
                "VALUES(?,?,?,?,?,?,?,?)";
        QueryRunner runner = new QueryRunner();
        runner.update(connection,sql,thing.getTitle(),thing.getTag(),thing.getDescribe(),thing.getRemark(),thing.getRemind_time(),thing.getReminder_date(),thing.getDone(),thing.getUser().get_id());
    }

    /**
     * 修改事件
     *
     * @param thing : 要修改的内容
     * @author kayai
     * @date 2022/4/23 18:11
     */
    @Override
    public void updateThing(Thing thing) throws SQLException {
        String sql = "UPDATE things " +
                "SET title = ? ," +
                "tag = ?," +
                "`describe` = ?," +
                "remark = ? ," +
                "remind_time = ? ," +
                "reminder_date = ?," +
                "done = ?" +
                "WHERE _id = ?";
        QueryRunner runner = new QueryRunner();
        runner.update(connection,sql,thing.getTitle(),thing.getTag(),thing.getDescribe(),thing.getRemark(),thing.getRemind_time(),thing.getReminder_date(),thing.getDone(),thing.get_id());
    }

    /**
     * 删除事件
     *
     * @param id : 要删除的事件id
     * @author kayai
     * @date 2022/4/23 18:11
     */
    @Override
    public void delThing(int id) throws SQLException {
        String sql = "DELETE FROM things WHERE _id = ?";
        QueryRunner runner = new QueryRunner();
        runner.update(connection,sql,id);
    }

    /**
     * 查找单个事件
     *
     * @param id : 要查找的事件id
     * @return pojo.Thing
     * @author kayai
     * @date 2022/4/23 18:12
     */
    @Override
    public Thing queryThing(int id) throws SQLException {
        String sql = "SELECT * FROM things WHERE _id = ?";
        QueryRunner runner = new QueryRunner();
        Object[] query = runner.query(connection, sql, new ArrayHandler(), id);
        String sql1 = "SELECT * FROM `user` WHERE _id = ?";
        User user = runner.query(connection, sql1, new BeanHandler<User>(User.class), query[8]);
        return new Thing((Integer) query[0], (String) query[1], (String) query[2], (String) query[3], (String) query[4], (Time) query[5], (Date) query[6], (Boolean) query[7],user);
    }

    @Override
    public Thing queryThing(String name, int user_id) throws SQLException {
        String sql = "SELECT * FROM things WHERE user = ? AND title = ?";
        QueryRunner runner = new QueryRunner();
        Object[] query = runner.query(connection, sql, new ArrayHandler(), user_id,name);
        for (int i=0;i<query.length;i++){
            System.out.println(query[i]);
        }
        String sql1 = "SELECT * FROM `user` WHERE _id = ?";
        User user = runner.query(connection, sql1, new BeanHandler<User>(User.class), query[8]);
        System.out.println(user);
        return new Thing((Integer) query[0], (String) query[1], (String) query[2], (String) query[3], (String) query[4], (Time) query[5], (Date) query[6], (Boolean) query[7],user);
    }

    /**
     * 查找用户所有事件
     *
     * @param id : 查找的用户的id
     * @return java.util.List<pojo.Thing>
     * @author kayai
     * @date 2022/4/23 18:21
     */
    @Override
    public List<Thing> queryThingAll(int id) throws SQLException {
        String sql = "SELECT * FROM things WHERE `user` = ?";
        List<Thing> thingList = new ArrayList<>();
        QueryRunner runner = new QueryRunner();
        List<Object[]> query = runner.query(connection, sql, new ArrayListHandler(), id);
        String sql1 = "SELECT * FROM `user` WHERE _id = ?";
        for (Object objs[] : query){
            User user = runner.query(connection, sql1, new BeanHandler<>(User.class), objs[8]);
            Thing thing = new Thing((Integer) objs[0], (String) objs[1], (String) objs[2], (String) objs[3], (String) objs[4], (Time) objs[5], (Date) objs[6], (Boolean) objs[7],user);
            thingList.add(thing);
        }
        return thingList;
    }
}
