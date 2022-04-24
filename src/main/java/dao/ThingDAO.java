package dao;

import pojo.Thing;
import pojo.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 18:10
 */
public interface ThingDAO {
    /**
     * 添加事件
     * @author kayai
     * @date 2022/4/23 18:10
     * @param thing: 要添加的事件
     */
    public void addThing(Thing thing) throws SQLException;

    /**
     * 修改事件
     * @author kayai
     * @date 2022/4/23 18:11
     * @param thing: 要修改的内容
     */
    public void updateThing(Thing thing) throws SQLException;

    /**
     * 删除事件
     * @author kayai
     * @date 2022/4/23 18:11
     * @param id: 要删除的事件id
     */
    public void delThing(int id) throws SQLException;

    /**
     * 查找单个事件
     * @author kayai
     * @date 2022/4/23 18:12
     * @param id: 要查找的事件id
     * @return pojo.Thing
     */
    public Thing queryThing(int id) throws SQLException;

    /**
     * 查找用户所有事件
     * @author kayai
     * @date 2022/4/23 18:21
     * @param id: 查找的用户的id
     * @return java.util.List<pojo.Thing>
     */
    public List<Thing> queryThingAll(int id) throws SQLException;
}
