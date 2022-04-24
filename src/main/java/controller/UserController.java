package controller;

import pojo.Project;
import pojo.User;
import service.ListService;
import service.ProjectService;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

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
     * @return java.lang.String
     */
    public String login(String openid, HttpSession session) throws SQLException {
        User user = userService.login(openid);
        if (user != null){
            List<Project> projectList = projectService.getProjectList(user.get_id());
            List<pojo.List> list = listService.getList(user);
            user.setListList(list);
            user.setProjectsList(projectList);
            session.setAttribute("user",user);
        }else{
            user = signUp(openid);
            session.setAttribute("user",user);
        }
        return "index";
    }

    public User signUp(String openid) throws SQLException {
        User user = new User("新用户",openid);
        userService.signUp(user);
        return user;
    }
}
