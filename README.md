**This application is created React App with Spring Boot.**

This application is interact with mongoDB rest api database microservice application to the data.
```
localhost:8082/database/currencies
```
Both microservice applications are developed with proper test coverage.

**Spring Boot Application is running on http://localhost:8080**

Currency URL's provided below
```
http://localhost:8080/api/getAll
http://localhost:8080/api/btcCurrencies
http://localhost:8080/api/etcCurrencies
http://localhost:8080/api/ltcCurrencies
```
Frontend React Application is running on 
```
http://localhost:3000
$ cd frontend
$ npm start
```
The forntend code is on src/App.js

**Deploy the application in AWS using the provifed steps :- https://aws.amazon.com/getting-started/projects/deploy-nodejs-web-app/**

Result's are provided below.
```
http://localhost:8080/api/btcCurrencies
{
    "minimumPriceTime": "0915",
    "maximumPriceTime": "1230",
    "minimumPriceValue": 34.98,
    "maximumPriceValue": 37.01,
    "profit": 2.03
}

http://localhost:8080/api/etcCurrencies
{
    "minimumPriceTime": "0900",
    "maximumPriceTime": "1700",
    "minimumPriceValue": 1.45,
    "maximumPriceValue": 2.15,
    "profit": 0.7
}

http://localhost:8080/api/ltcCurrencies
{
    "minimumPriceTime": "0930",
    "maximumPriceTime": "1245",
    "minimumPriceValue": 14.32,
    "maximumPriceValue": 15.03,
    "profit": 0.71
}
```


Future Implementation :-
Exception handling required proper design and implementation( Ex Http Error codes 400, 401, 404, 408, 500). 
At present, if there is any issue with the microservices, then it will throw 500 errors.
