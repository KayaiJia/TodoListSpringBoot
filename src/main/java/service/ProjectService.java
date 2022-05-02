package service;

import pojo.Project;
import pojo.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/24 14:28
 */
public interface ProjectService {
    /**
     * 查询特定用户的Project
     * @author kayai
     * @date 2022/4/24 14:30
     * @param id: 用户的_id
     * @return java.util.List<pojo.Project>
     */
    public List<Project> getProjectList(int id) throws SQLException;

    public List<Project> getList(User user) throws SQLException;

    /**
     *
     * @author kayai
     * @date 2022/5/1 14:53
     * @param openid: 用户的openid
     * @return java.util.List<pojo.Project>
     */
    public List<Project> getProjectList(String openid) throws SQLException;

    /**
     * 添加项目
     * @author kayai
     * @date 2022/4/28 14:22
     * @param openid: 用户id
     * @param projectName:项目名称
     */
    public void addProject(String openid,String projectName) throws SQLException;

    public void delProject(String openid,String name) throws SQLException;
}
