package service;

import pojo.Thing;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.SQLException;
import java.util.List;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/24 14:25
 */
public interface ThingService {
    /**
     * 添加新事件
     * @author kayai
     * @date 2022/4/26 21:41
     * @param title:
     * @param tag:
     * @param describe:
     * @param remark:
     * @param time:
     * @param done:
     * @param openid:
     */
    public void addThing(String title, String tag, String describe, String remark, String time, String done, String openid, int type, String name) throws SQLException;

    /**
     *
     * @author kayai
     * @date 2022/4/27 15:59
     * @param openid:
     * @param title:
     */
    public void addThingsToThingsMap(String openid,String title,Integer type,String name) throws SQLException;


    /**
     *
     * @author kayai
     * @date 2022/5/1 15:13
     * @param openid:
     * @param id:
     * @param index:
     * @return pojo.Thing
     */
    public Thing queryThingForList(String openid,Integer id,Integer index) throws SQLException;

    Thing queryThingForProject(String openid, Integer id, Integer index) throws SQLException;

    public void updateThing(String openid, Thing oldThing, String title, String tag, String describe, String remark, String time, String done, Integer type, String name) throws SQLException;

    public void doneThing(Thing thing,Integer listId,String openid) throws SQLException;

    public void del(Thing thing) throws SQLException;

}
