CREATE DATABASE IF NOT EXISTS admix;

CREATE TABLE IF NOT EXISTS Profile (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        email VARCHAR(255) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL,
        status BOOLEAN DEFAULT false,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS HealthDetails(
       id INT AUTO_INCREMENT PRIMARY KEY,
       email VARCHAR(255) NOT NULL UNIQUE,
       age FLOAT,
       weight FLOAT,
       height FLOAT,
       gender VARCHAR(20),
       doYouDrink VARCHAR(20),
       doYouSmoke VARCHAR(20),
       doYouWearWearables BOOLEAN,
       wearableDevice VARCHAR(50),
       healthGoals VARCHAR(50)
);