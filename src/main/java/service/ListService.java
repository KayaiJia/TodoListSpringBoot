package service;

import pojo.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/24 14:31
 */
public interface ListService {
    /**
     * 获取用户列表
     * @author kayai
     * @date 2022/4/24 14:31
     * @param user: 用户类
     * @return java.util.List<pojo.List>
     */
    public List<pojo.List> getList(User user) throws SQLException;
}
