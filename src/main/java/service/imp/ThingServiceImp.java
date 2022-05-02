package service.imp;

import dao.*;
import pojo.*;
import service.ThingService;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/26 21:39
 */
public class ThingServiceImp implements ThingService {
    private ThingDAO thingDAO = null;
    private UserDAO userDAO = null;
    private ListDAO listDAO = null;
    private ProjectDAO projectDAO = null;
    private MapDAO mapDAO = null;
    /**
     * 添加新事件
     *
     * @param title    :
     * @param tag      :
     * @param describe :
     * @param remark   :
     * @param time     :
     * @param done     :
     * @param openid   :
     * @author kayai
     * @date 2022/4/26 21:41
     */
    @Override
    public void addThing(String title, String tag, String describe, String remark, String time, String done, String openid, int type, String name) throws SQLException {
        User user = userDAO.queryUser(openid);
        Date addDate = null;
        Time addTime = null;

        if (time != null && !"".equals(time)){
            String date = time.substring(0, 10);
            addDate = strToDate(date);

            time = time.substring(11);
            addTime = strToTime(time);
        }

        Thing thing = new Thing(title,tag,describe,remark,addTime,addDate,Boolean.parseBoolean(done),user);

        thingDAO.addThing(thing);

        if (type == 0){
            List list = listDAO.queryList(name, user.get_id());
            ThingsMap thingsMap = new ThingsMap(0,list.get_id(),title);
            mapDAO.add(thingsMap);
        }else {
            Project project = projectDAO.queryProject(name, user.get_id());
            ThingsMap thingsMap = new ThingsMap(1,project.get_id(),title);
            mapDAO.add(thingsMap);
        }
    }

    /**
     * 同步thingMap
     * @author kayai
     * @date 2022/4/27 15:57
     * @param openid:
     * @param title:
     */
    @Override
    public void addThingsToThingsMap(String openid,String title,Integer type,String name) throws SQLException {
        User user = userDAO.queryUser(openid);
        Thing thing = thingDAO.queryThing(title, user.get_id());
        int id = 0;
        if (type == 0){
            List list = listDAO.queryList(name, user.get_id());
            id = list.get_id();
//            mapDAO.add(new ThingsMap(0, list.get_id(), title));
        }else {
            Project project = projectDAO.queryProject(name, user.get_id());
            id = project.get_id();
//            mapDAO.add(new ThingsMap(1, project.get_id(), title));
        }
        mapDAO.update(thing.get_id(),title,id);
    }


    @Override
    public Thing queryThingForList(String openid, Integer id, Integer index) throws SQLException {
        User user = userDAO.queryUser(openid);
        java.util.List<List> list = listDAO.queryListALl(user.get_id());
        java.util.List<Thing> things = list.get(id).getThings();
        if (things ==  null)
            return null;
        Thing thing = things.get(index);
        return thing;
    }

    @Override
    public Thing queryThingForProject(String openid, Integer id, Integer index) throws SQLException {
        User user = userDAO.queryUser(openid);
        java.util.List<Project> list = projectDAO.queryProjectAll(user.get_id());
        java.util.List<Thing> things = list.get(id).getThings();
        Thing thing = things.get(index);
        return thing;
    }

    @Override
    public void updateThing(String openid,Thing oldThing,String title, String tag, String describe, String remark, String time, String done, Integer type, String name) throws SQLException {
        User user = userDAO.queryUser(openid);

        Date addDate = null;
        Time addTime = null;

        if (time != null && !"".equals(time)){
            String date = time.substring(0, 10);
            addDate = strToDate(date);

            time = time.substring(11);
            addTime = strToTime(time);
        }

        Thing thing = new Thing(title,tag,describe,remark,addTime,addDate,Boolean.parseBoolean(done),user);
        thing.set_id(oldThing.get_id());
        thingDAO.updateThing(thing);
        int id = 0;
        if (type != null) {
            if (type == 0) {
                List list = listDAO.queryList(name, user.get_id());
                mapDAO.del(oldThing.get_id(), 1);
                ThingsMap thingsMap = new ThingsMap(0, list.get_id(), title);
                thingsMap.setThings_id(oldThing.get_id());
                mapDAO.add(thingsMap);
            } else if (type == 1) {
                Project project = projectDAO.queryProject(name, user.get_id());
                mapDAO.del(oldThing.get_id(), 0);
                ThingsMap thingsMap = new ThingsMap(1, project.get_id(), title);
                thingsMap.setThings_id(oldThing.get_id());
                mapDAO.add(thingsMap);
            }else {
                mapDAO.update(oldThing.get_id(),title);
            }
        }
    }

    @Override
    public void doneThing(Thing thing, Integer listId, String openid) throws SQLException {
        User user = userDAO.queryUser(openid);
        List list = listDAO.queryList("已完成", user.get_id());
        int id = list.get_id();
        ThingsMap map = mapDAO.query((Integer)thing.get_id(),(Integer) 0);
        mapDAO.update(thing.get_id(),map.getMap_id(),id);
    }

    @Override
    public void del(Thing thing) throws SQLException {
        thingDAO.delThing(thing.get_id());
        mapDAO.del(thing.get_id());
    }

    private java.sql.Date strToDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }

    private java.sql.Time strToTime(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Time time = new java.sql.Time(d.getTime());
        return time.valueOf(str);
    }
}
