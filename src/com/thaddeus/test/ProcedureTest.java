package com.thaddeus.test;

import com.thaddeus.util.JdbcUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.*;

public class ProcedureTest {
    @Test
    public void testProcedure() {
        Connection connection = JdbcUtil.getConnection();
        String sql = "call getIn(?)";
        try {
            CallableStatement call = connection.prepareCall(sql);
            call.setString(1, "1");

            call.execute();

            ResultSet resultSet = call.getResultSet();

            if (resultSet.next()) {
                BigDecimal object = (BigDecimal) resultSet.getObject(1);

                System.out.println(object);
            }

            call.close();
            JdbcUtil.close(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
