package controller;

import pojo.Project;
import pojo.ReturnData;
import pojo.ReturnType;
import pojo.User;
import service.ListService;
import service.ProjectService;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/24 14:22
 */
public class UserController {
    private UserService userService = null;
    private ProjectService projectService = null;
    private ListService listService = null;

    /**
     * 登录
     * @author kayai
     * @date 2022/4/24 14:57
     * @param openid: 用户的openid
     * @param session: 当前的回话
     * @return pojo.ReturnData
     */
    public ReturnData login(String openid, HttpSession session) throws SQLException {
        User user = userService.login(openid);
        if (user != null){
            List<Project> projectList = projectService.getProjectList(user.get_id());
            List<pojo.List> list = listService.getList(user);
            user.setListList(list);
            user.setProjectsList(projectList);
            session.setAttribute("user",user);
        }else{
            user = signUp(openid);
            List<Project> projectList = projectService.getProjectList(user.get_id());
            List<pojo.List> list = listService.getList(user);
            user.setListList(list);
            user.setProjectsList(projectList);
            session.setAttribute("user",user);
        }
        ReturnData data = new ReturnData();
        data.setType(ReturnType.valueOf("RENDER"));
        data.setData("user",user);
        return data;
    }

     private User signUp(String openid) throws SQLException {
        User user = new User("新用户",openid);
        userService.signUp(user);
        return user;
    }

    /**
     * 查询用户列表中的数量
     * @author kayai
     * @date 2022/4/26 17:32
     * @param openid: 查询的openid
     * @return pojo.ReturnData
     */
    public ReturnData getListCount(String openid) throws SQLException {
        int[] count = listService.getCount(openid);
        ReturnData data = new ReturnData();
        data.setType(ReturnType.valueOf("RENDER"));
        data.setData("count",count);
        return data;
    }
}
