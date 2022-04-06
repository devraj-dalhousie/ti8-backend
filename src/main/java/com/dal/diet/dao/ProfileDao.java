package com.dal.diet.dao;

import com.dal.diet.entity.HealthDetails;
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

    public HealthDetails createHealthDetails(HealthDetails healthDetails) throws SQLException, ClassNotFoundException {
        DBHelper dbHelper = DBHelper.getInstance();
        String sqlQuery = "insert into HealthDetails(email, age,weight,height, gender,doYouDrink,doYouSmoke,doYouWearWearables,wearableDevice,healthGoals) values('%s',%f,%f,%f, '%s','%s','%s',%s,'%s','%s')";
        String formattedQuery = String.format(sqlQuery,
                healthDetails.getEmail(),
                healthDetails.getAge(),
                healthDetails.getWeight(),
                healthDetails.getHeight(),
                healthDetails.getGender(),
                healthDetails.getDoYouDrink(),
                healthDetails.getDoYouSmoke(),
                String.valueOf(healthDetails.isDoYouWearWearables()).toUpperCase(),
                healthDetails.getWearableDevice(),
                healthDetails.getHealthGoals()
        );
        System.out.println("Debug" + formattedQuery);
        dbHelper.executeCreateOrUpdateQuery(formattedQuery);
        String getSelectQuery = String.format("select email, age,weight,gender,doYouDrink,doYouSmoke,doYouWearWearables,wearableDevice,healthGoals from HealthDetails where email='%s'", healthDetails.getEmail());
        ResultSet resultSet = dbHelper.executeSelectQuery(getSelectQuery);
        if (resultSet.next()) {
            String email = healthDetails.getEmail();
            double age = healthDetails.getAge();
            double weight = healthDetails.getWeight();
            double height = healthDetails.getHeight();
            String gender = healthDetails.getGender();
            String doYouDrink = healthDetails.getDoYouDrink();
            String doYouSmoke = healthDetails.getDoYouSmoke();
            boolean doYouWearWearables = healthDetails.isDoYouWearWearables();
            String wearableDevice = healthDetails.getWearableDevice();
            String healthGoals = healthDetails.getHealthGoals();
            return new HealthDetails(email, age, weight, height, gender, doYouDrink, doYouSmoke, doYouWearWearables, wearableDevice, healthGoals);
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
