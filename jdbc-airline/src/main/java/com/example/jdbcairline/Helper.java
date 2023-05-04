package com.example.jdbcairline;
import java.sql.*;

public abstract class Helper {
        private Connection connection;
        private PreparedStatement preparedStatement;
        private ResultSet resultSet;
        // MySQL 서버와 연결을 위한 메소드
        public Connection connectToMySQL(String host, String username, String password, String dbName) {
            Connection conn = null;
            try {
                String url = "jdbc:mysql://" + host + "/" + dbName;
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("MySQL Connection Successful");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return conn;
        }
        // SQL 문장 실행을 위한 메소드
        public ResultSet executeQuery(String sqlStatement, Object... params) {
            try {
                preparedStatement = connection.prepareStatement(sqlStatement);
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return resultSet;
        }

        // SQL 문장 실행을 위한 메소드 (INSERT, UPDATE, DELETE)
        public int executeUpdate(String sqlStatement, Object... params) {
            int result = -1;
            try {
                preparedStatement = connection.prepareStatement(sqlStatement);
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
                result = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return result;
        }

        // 결과 집합(ResultSet)을 닫는 메소드
        public void closeResultSet() {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // SQL 문장을 닫는 메소드
        public void closePreparedStatement() {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 데이터베이스 연결을 닫는 메소드
        public void closeConnection() {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }
}
