package com.dal.diet.dao;

import com.dal.diet.entity.LoginRequest;
import com.dal.diet.entity.Profile;
import com.dal.diet.helper.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDao {

    public Profile createProfile(Profile profile) throws SQLException, ClassNotFoundException {
        DBHelper dbHelper = DBHelper.getInstance();
        String sqlQuery = "insert into Profile(name, email, password, status) values('%s', '%s', '%s', true)";
        String formattedQuery = String.format(sqlQuery, profile.getName(), profile.getEmail(), profile.getPassword());
        dbHelper.executeCreateOrUpdateQuery(formattedQuery);
        String getSqlQuery = String.format("select id, name, email, password, status from Profile where email='%s'", profile.getEmail());
        ResultSet resultSet = dbHelper.executeSelectQuery(getSqlQuery);
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            boolean status = resultSet.getBoolean("status");

            return new Profile(id, name, email, password, status);
        }
        throw new RuntimeException("Unknown error");

    }

    public Profile login(LoginRequest loginRequest) throws SQLException, ClassNotFoundException {
        String userName = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        String query = String.format("select * from Profile where email='%s' and password='%s'",
                userName, password);
        System.out.println(query);
        DBHelper dbHelper = DBHelper.getInstance();
        ResultSet resultSet = dbHelper.executeSelectQuery(query);
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            boolean status = resultSet.getBoolean("status");
            String _password = resultSet.getString("password");
            return new Profile(id, name, email, _password, status);
        }
        return null;
    }

}
