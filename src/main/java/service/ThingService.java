package service;

import pojo.Thing;

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
}
