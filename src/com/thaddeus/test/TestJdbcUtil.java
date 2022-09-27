package com.thaddeus.test;

import com.thaddeus.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;

public class TestJdbcUtil {

    @Test
    public void testConnection() {

        Connection connection = JdbcUtil.getConnection();

        System.out.println(connection);

        JdbcUtil.close(connection);

    }
}
