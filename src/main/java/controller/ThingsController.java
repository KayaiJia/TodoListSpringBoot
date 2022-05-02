package controller;

import pojo.ReturnData;
import pojo.ReturnType;
import pojo.Thing;
import service.ThingService;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/26 20:52
 */
public class ThingsController {
    private ThingService thingService = null;

    /**
     * @param title:    标题
     * @param tag:      标签
     * @param describe: 描述
     * @param remark:   备注
     * @param time:     提醒时间
     * @param done:     是否完成
     * @param openid:   用户唯一识别码
     * @param type:     列表或项目
     * @param name:     列表或项目名称
     * @author kayai
     * @date 2022/4/26 22:04
     */
    public ReturnData addThing(String title, String tag, String describe, String remark, String time, String done, String openid, Integer type, String name) throws SQLException, ParseException, UnsupportedEncodingException {
        thingService.addThing(title, tag, describe, remark, time, done, openid, type, name);
        ReturnData returnData = new ReturnData();
        returnData.setType(ReturnType.RENDER);
        returnData.setData("title", URLEncoder.encode(title, "UTF-8"));
        returnData.setData("operate", "addThingsToThingsMap");
        returnData.setData("type", type);
        returnData.setData("name", URLEncoder.encode(name, "UTF-8"));
        return returnData;
    }

    public ReturnData addThingsToThingsMap(String openid, String title, Integer type, String name) throws SQLException, URISyntaxException {
        title = new java.net.URI(title).getPath();
        name = new java.net.URI(name).getPath();
        thingService.addThingsToThingsMap(openid, title, type, name);
        ReturnData returnData = new ReturnData();
        returnData.setType(ReturnType.RENDER);
        returnData.setData("data", "OK");
        return returnData;
    }

    /**
     * @param openid:
     * @param listId:
     * @param index:
     * @return pojo.ReturnData
     * @author kayai
     * @date 2022/5/1 15:14
     */
    public ReturnData queryThing(String openid, Integer listId, Integer index) {
        return null;
    }

    /**
     * @param openid:
     * @param listId:
     * @param index:
     * @param title:
     * @param tag:
     * @param describe:
     * @param remark:
     * @param time:
     * @param done:
     * @param type:
     * @param name:
     * @return pojo.ReturnData
     * @author kayai
     * @date 2022/5/1 15:48
     */
    public ReturnData updateThing(String openid, Integer listId, Integer index, String title, String tag, String describe, String remark, String time, String done, Integer type, String name) throws SQLException {
        Thing thing = thingService.queryThingForList(openid, listId, index);
        if (thing == null)
            thing = thingService.queryThingForProject(openid, listId, index);

        thingService.updateThing(openid, thing, title, tag, describe, remark, time, done, type, name);
        ReturnData returnData = new ReturnData();
        returnData.setType(ReturnType.RENDER);
        returnData.setData("status", "OK");

        return returnData;
    }

    public ReturnData doneThing(String openid, Integer listId, Integer index) throws SQLException {
        Thing thing = thingService.queryThingForList(openid, listId, index);
        thingService.doneThing(thing, listId, openid);

        ReturnData returnData = new ReturnData();
        returnData.setType(ReturnType.RENDER);
        returnData.setData("status", "OK");

        return returnData;
    }

    public ReturnData delete(String openid, Integer listId, Integer index) throws SQLException {
        Thing thing = thingService.queryThingForList(openid, listId, index);
        if (thing == null)
            thing = thingService.queryThingForProject(openid, listId, index);
        thingService.del(thing);

        ReturnData returnData = new ReturnData();
        returnData.setType(ReturnType.RENDER);
        returnData.setData("status", "OK");

        return returnData;
    }
}
