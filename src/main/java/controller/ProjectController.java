package controller;

import dao.UserDAO;
import pojo.*;
import service.ProjectService;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/28 14:19
 */
public class ProjectController {
    ProjectService projectService = null;
    UserDAO userDAO = null;

    public ReturnData getListThings(String openid,Integer listId) throws SQLException {
        User user = userDAO.queryUser(openid);
        java.util.List<Project> list = projectService.getList(user);
        java.util.List<Thing> things = list.get(listId).getThings();
        ReturnData returnData = new ReturnData();
        returnData.setType(ReturnType.RENDER);
        returnData.setData("things",things);
        return returnData;
    }
    /**
     *
     * @author kayai
     * @date 2022/5/1 14:50
     * @param openid: 用户id
     * @param projectName: 项目名字
     * @return pojo.ReturnData
     */
    public ReturnData addProject(String openid,String projectName) throws URISyntaxException, SQLException {
        projectName = new java.net.URI(projectName).getPath();
        projectService.addProject(openid,projectName);
        ReturnData returnData = new ReturnData();
        returnData.setType(ReturnType.RENDER);
        returnData.setData("status","ok");
        return returnData;
    }

    public ReturnData getProject(String openid) throws SQLException {
        List<Project> projectList = projectService.getProjectList(openid);
        ReturnData returnData = new ReturnData();
        returnData.setType(ReturnType.RENDER);
        returnData.setData("project",projectList);
        return returnData;
    }

    public ReturnData delProject(String openid,String name) throws SQLException {
        projectService.delProject(openid,name);

        ReturnData returnData = new ReturnData();
        returnData.setType(ReturnType.RENDER);
        returnData.setData("status", "OK");

        return returnData;
    }
}
