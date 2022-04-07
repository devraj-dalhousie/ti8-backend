package com.dal.diet.helper;

import java.sql.*;

public class DBHelper {
    private static Connection connection;
    private static Statement statement;
    private static DBHelper dbHelper;

    // Local database details:
    /*private final String DB_HOST = "localhost";
    private final String DEFAULT_MYSQL_USERNAME = "root";
    private final String DEFAULT_MYSQL_PASSWORD = "mysql@789";
    private final String DEFAULT_MYSQL_DATABASE = "admix";*/

    private final String DB_HOST = "sql3.freemysqlhosting.net";
    private final String DEFAULT_MYSQL_USERNAME = "sql3484246";
    private final String DEFAULT_MYSQL_PASSWORD = "DjUd6czVjl";
    private final String DEFAULT_MYSQL_DATABASE = "sql3484246";

    String database;
    String user;
    String password;
    String connUrl;
    String url = "jdbc:mysql://%s:3306/%s?useSSL=false&allowPublicKeyRetrieval=true";

    private DBHelper() {
        this.database = DEFAULT_MYSQL_DATABASE;
        this.user = DEFAULT_MYSQL_USERNAME;
        this.password = DEFAULT_MYSQL_PASSWORD;
        connUrl = String.format(url, DB_HOST, this.database);
    }

    public static DBHelper getInstance() throws SQLException, ClassNotFoundException {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
            dbHelper.initialize();
        }
        return dbHelper;
    }

    private void initialize() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connUrl, user, password);
        }
        if (statement == null) {
            statement = connection.createStatement();
        }
    }

    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public void executeCreateOrUpdateQuery(String query) throws SQLException {
        if (connection == null) {
            throw new RuntimeException("Please call initialize method in DBHelper before calling this method.");
        }
        statement.executeUpdate(query);
    }

    public ResultSet executeSelectQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DBHelper dbHelper = DBHelper.getInstance();
    }
}
