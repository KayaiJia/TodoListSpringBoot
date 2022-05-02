package dao.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/23 20:24
 */
public class JDBCUtils {
    private static DataSource dataSource;
    private static Connection conn;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        Properties properties = new Properties();
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");

        try {
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        conn = threadLocal.get();
        if (conn == null){
            Connection connection = dataSource.getConnection();
            threadLocal.set(connection);
            return connection;
        }else {
            return conn;
        }
    }

    public static void close(){
        try {
            if (conn != null && !conn.isClosed()){
                try {
                    conn.close();
                    conn = null;
                    threadLocal.set(null);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
