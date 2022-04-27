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

        if (time != null){
            String date = time.substring(0, 10);
            addDate = strToDate(date);
            System.out.println(addDate);

            time = time.substring(11);
            addTime = strToTime(time);
            System.out.println(addTime);
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
        }else {
            Project project = projectDAO.queryProject(name, user.get_id());
            id = project.get_id();
        }
        mapDAO.update(thing.get_id(),title,id);
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
