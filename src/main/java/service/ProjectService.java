package service;

import pojo.Project;

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
}
