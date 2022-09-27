package com.thaddeus.dao.impl;

import com.thaddeus.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    private final QueryRunner queryRunner = new QueryRunner();

    /**
     * 增删改操作
     *
     * @param sql 执行的 sql 语句
     * @return 查到数据则返回数据条数, 否则返回 -1
     */
    public int update(String sql, Object... args) {
        Connection connection = JdbcUtil.getConnection();

        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection);
        }

        return -1;
    }

    /**
     * 查询一条记录
     *
     * @param type 查询对象的类型
     * @param sql  执行的 sql 语句
     * @param args sql 参数
     * @param <T>  对象泛型
     * @return 查询到则返回该记录, 否则返回 null
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection connection = JdbcUtil.getConnection();

        try {
            return queryRunner.query(connection, sql, new BeanHandler<>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection);
        }
        return null;
    }

    /**
     * 查询多条记录
     *
     * @param type 查询对象的类型
     * @param sql  执行的 sql 语句
     * @param args sql 参数
     * @param <T>  对象泛型
     * @return 查询到则返回该记录, 否则返回 null
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection connection = JdbcUtil.getConnection();

        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection);
        }
        return null;
    }

    /**
     * 执行返回一行一列的 sql
     *
     * @param sql  执行的 sql 语句
     * @param args sql 参数
     * @return 查询到则返回记录, 否则返回 null
     */
    public Object queryForSingleValue(String sql, Object... args) {
        Connection connection = JdbcUtil.getConnection();

        try {
            return queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
    }

    /**
     * 存储过程
     */
    public Integer procedure(String sql, Object... args) {
        Connection connection = JdbcUtil.getConnection();
        CallableStatement call = null;

        int res = 0;

        try {
            call = connection.prepareCall(sql);

            for (int i = 0; i < args.length; i++) {
                call.setString(i + 1, String.valueOf(args[i]));
            }

            call.execute();

            ResultSet resultSet = call.getResultSet();
            if (resultSet.next()) {
                Number number = (Number) resultSet.getObject(1);

                if (number != null) {
                    res = number.intValue();
                }

            }

            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            JdbcUtil.close(connection);
        }

        return null;
    }
}
