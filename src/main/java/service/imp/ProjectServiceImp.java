package service.imp;

import dao.ProjectDAO;
import dao.UserDAO;
import pojo.Project;
import pojo.User;
import service.ProjectService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/24 14:43
 */
public class ProjectServiceImp implements ProjectService {
    private ProjectDAO projectDAO = null;
    private UserDAO userDAO = null;

    /**
     * 查询特定用户的Project
     *
     * @param id : 用户的_id
     * @return java.util.List<pojo.Project>
     * @author kayai
     * @date 2022/4/24 14:30
     */
    @Override
    public List<Project> getProjectList(int id) throws SQLException {
        return projectDAO.queryProjectAll(id);
    }

    @Override
    public List<Project> getList(User user) throws SQLException {
        return projectDAO.queryProjectAll(user.get_id());
    }

    /**
     * @param openid : 用户的openid
     * @return java.util.List<pojo.Project>
     * @author kayai
     * @date 2022/5/1 14:53
     */
    @Override
    public List<Project> getProjectList(String openid) throws SQLException {
        User user = userDAO.queryUser(openid);
        return projectDAO.queryProjectAll(user.get_id());
    }


    /**
     * 添加项目
     *
     * @param openid      : 用户id
     * @param projectName :项目名称
     * @author kayai
     * @date 2022/4/28 14:22
     */
    @Override
    public void addProject(String openid, String projectName) throws SQLException {
        User user = userDAO.queryUser(openid);
        projectDAO.addProject(new Project(projectName,user.get_id()));
    }

    @Override
    public void delProject(String openid, String name) throws SQLException {
        User user = userDAO.queryUser(openid);
        Project project = projectDAO.queryProject(name, user.get_id());
        projectDAO.delProject(project.get_id());
    }


}
