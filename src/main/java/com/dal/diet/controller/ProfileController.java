package com.dal.diet.controller;

import com.dal.diet.dao.ProfileDao;
import com.dal.diet.entity.HealthDetails;
import com.dal.diet.entity.LoginRequest;
import com.dal.diet.entity.Profile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "Pong";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createProfile(@RequestBody Profile profile) {
        try {
            ProfileDao profileDao = new ProfileDao();
            Profile createdProfile = profileDao.createProfile(profile);
            return getJson(createdProfile);
        } catch (JsonProcessingException throwables) {
            throwables.printStackTrace();
            return "Error while creating profile object";
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            return exception.getMessage();
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody LoginRequest loginRequest) {
        ProfileDao profileDao = null;
        try {
            profileDao = new ProfileDao();
            Profile profile = profileDao.login(loginRequest);
            if(profile == null) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Login unsuccessful due to invalid credentials");
            }
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception.getMessage());
        }
        return "Login successful";
    }

    @RequestMapping(value = "/health", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createHealthDetails(@RequestBody HealthDetails healthDetails) {
        try {
            ProfileDao profileDao = new ProfileDao();
            HealthDetails createdHealthDetails = profileDao.createHealthDetails(healthDetails);
            return getJson(createdHealthDetails);
        } catch (JsonProcessingException throwables) {
            throwables.printStackTrace();
            return "Error while creating health details";
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            return exception.getMessage();
        }
    }

    private String getJson(Profile profile) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(profile);
    }

    private String getJson(HealthDetails healthDetails) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(healthDetails);
    }
}
