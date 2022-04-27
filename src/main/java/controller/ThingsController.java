package controller;

import pojo.ReturnData;
import pojo.ReturnType;
import service.ThingService;

import java.io.UnsupportedEncodingException;
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
     *
     * @author kayai
     * @date 2022/4/26 22:04
     * @param title: 标题
     * @param tag: 标签
     * @param describe: 描述
     * @param remark: 备注
     * @param time: 提醒时间
     * @param done: 是否完成
     * @param openid: 用户唯一识别码
     * @param type: 列表或项目
     * @param name: 列表或项目名称
     */
    public ReturnData addThing(String title, String tag, String describe, String remark, String time, String done, String openid, Integer type, String name) throws SQLException, ParseException, UnsupportedEncodingException {
        thingService.addThing(title,tag,describe,remark,time,done,openid,type,name);
        ReturnData returnData = new ReturnData();
        returnData.setType(ReturnType.REDIRECT);
        returnData.setData("destination","/thingsController.do?operate=addThingsToThingsMap&title=" + URLEncoder.encode(title,"UTF-8") + "&type=" + type + "&name=" + URLEncoder.encode(name,"UTF-8"));
        return returnData;
    }

    public ReturnData addThingsToThingsMap(String openid, String title, Integer type,String name) throws SQLException {
        thingService.addThingsToThingsMap(openid,title,type,name);
        ReturnData returnData = new ReturnData();
        returnData.setType(ReturnType.RENDER);
        returnData.setData("data","OK");
        return returnData;
    }

}
