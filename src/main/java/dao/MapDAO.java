package dao;

import pojo.ThingsMap;

import java.sql.SQLException;
import java.util.List;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 20:02
 */
public interface MapDAO {
    /**
     * 添加数据
     * @author kayai
     * @date 2022/4/23 20:02
     * @param thingsMap: 添加的内容
     */
    public void add(ThingsMap thingsMap) throws SQLException;

    /**
     * 更新
     * @author kayai
     * @date 2022/4/24 01:20
     * @param thingsMap: 要更新的内容
     */
    public void update(ThingsMap thingsMap) throws SQLException;

    public void update(int thing_id,String title,int map_id) throws SQLException;

    public void update(int thing_id,String name) throws SQLException;

    public void update(int thing_id,int map_id,int newID) throws SQLException;

    /**
     * 删除
     * @author kayai
     * @date 2022/4/23 20:03
     * @param id: 删除的id
     */
    public void del(int id) throws SQLException;

    /**
     *
     * @author kayai
     * @date 2022/5/1 15:28
     * @param thing_id:
     * @param classify:
     * @return void
     */
    public void del(Integer thing_id,Integer classify) throws SQLException;

    /**
     * 查询
     * @author kayai
     * @date 2022/4/23 20:04
     * @param id: map_id
     * @param classify:类型
     * @return pojo.ThingsMap
     */
    public List<ThingsMap> query(int id,int classify) throws SQLException;

    /**
     *
     * @author kayai
     * @date 2022/5/1 15:28
     * @param thing_id:
     * @param classify:
     * @return pojo.ThingsMap
     */
    public ThingsMap query(Integer thing_id,Integer classify) throws SQLException;
}
