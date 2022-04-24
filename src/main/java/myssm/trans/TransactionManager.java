package myssm.trans;

import dao.util.JDBCUtils;
import java.sql.SQLException;

/**
 * 事务管理
 * @author kayai
 * @version 1.0
 * @date: 2022/4/22 09:35
 */
public class TransactionManager {

    /**
     * 开启事务
     * @author kayai
     * @date 2022/4/22 09:36
     */
    public static void beginTrans() throws SQLException {
        JDBCUtils.getConnection().setAutoCommit(false);
    }

    /**
     * 提交更改
     * @author kayai
     * @date 2022/4/22 09:37
     */
    public static void commit() throws SQLException {
        JDBCUtils.getConnection().commit();
        JDBCUtils.close();
    }

    /**
     * 回滚事务
     * @author kayai
     * @date 2022/4/22 09:37
     */
    public static void rollback() throws SQLException {
        JDBCUtils.getConnection().rollback();
        JDBCUtils.close();
    }
}
