package dao;


import pojo.Project;
import pojo.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 18:14
 */
public interface ProjectDAO {
    /**
     * 添加project
     * @author kayai
     * @date 2022/4/23 18:16
     * @param project: 要添加的Project
     */
    public void addProject(Project project) throws SQLException;

    /**
     * 修改project
     * @author kayai
     * @date 2022/4/23 18:19
     * @param project: 要修改的内容
     */
    public void updateProject(Project project) throws SQLException;

    /**
     * 根据id删除projcet
     * @author kayai
     * @date 2022/4/23 18:19
     * @param id: 删除的id
     */
    public void delProject(int id) throws SQLException;

    /**
     * 查找单个project
     * @author kayai
     * @date 2022/4/23 18:20
     * @param id: 查找的id
     * @return pojo.Project
     */
    public Project queryProject(int id) throws SQLException;

    /**
     *
     * @author kayai
     * @date 2022/4/26 21:55
     * @param name: 项目名字
     * @param id: 用户id
     * @return pojo.Project
     */
    public Project queryProject(String name, int id) throws SQLException;

    /**
     * 根据用户查找所有项目
     * @author kayai
     * @date 2022/4/23 18:22
     * @param id: 要查找的用户id
     * @return java.util.List<pojo.Project>
     */
    public List<Project> queryProjectAll(int id) throws SQLException;


}
