package controller;

import dao.UserDAO;
import dao.imp.UserDAOImp;
import pojo.*;
import service.ListService;
import service.imp.ListServiceImp;

import java.sql.SQLException;
import java.util.Iterator;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/30 13:41
 */
public class ListController {
    private ListService listService = null;
    private UserDAO userDAO = null;

    public ReturnData getListThings(String openid,Integer listId) throws SQLException {
        User user = userDAO.queryUser(openid);
        java.util.List<List> list = listService.getList(user);
        java.util.List<Thing> things = list.get(listId).getThings();
        ReturnData returnData = new ReturnData();
        returnData.setType(ReturnType.RENDER);
        returnData.setData("things",things);
        return returnData;
    }
}
