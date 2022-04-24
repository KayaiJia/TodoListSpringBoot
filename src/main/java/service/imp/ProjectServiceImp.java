package service.imp;

import dao.ProjectDAO;
import pojo.Project;
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
}
