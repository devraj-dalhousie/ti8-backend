Steps to build the jar file and execute it:
=============================================
```mvn clean install```

```java -jar target/admix-1.0-SNAPSHOT.jar```

Above two command will start the Spring application. Sample rest api can accessed in 
``` https://ti8-backend.herokuapp.com/profile/ping``` 
Above REST api will return pong which will indicate that server is up and running.

List of REST apis implemented:
* GET https://ti8-backend.herokuapp.com/profile/ping

* POST https://ti8-backend.herokuapp.com/profile/login
  Payload:
  ```
  {
  "email":"admin@gmail.com",
  "password":"admin123"
  }
  ```
  
* POST https://ti8-backend.herokuapp.com/profile
  
  Payload:
  ``` 
  {
    "name":"admin1 singh",
    "email":"admin1@gmail.com",
    "password":"admin1123"
  }
  ```
    
* POST https://ti8-backend.herokuapp.com/profile/health
  
  Payload:
  ```
  {
  "email":"admin@gmail.com",
  "age":20,
  "weight": 30,
  "height": 10,
  "gender":"male",
  "doYouDrink": "yes",
  "doYouSmoke":"yes",
  "doYouWearWearables": true,
  "wearableDevice":"fitbit",
  "healthGoals":"weight-loss-non-veg"
  }```
  
<hr/>
Backend is deployed in Heroku.
<hr/>
Database is hosted in online MySQL cloud database:
sql3.freemysqlhosting.net
<hr/>
  
  