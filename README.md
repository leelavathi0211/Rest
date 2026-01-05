# Rest

REST API automation framework built using Java, Rest Assured, and TestNG.
Automates POST and GET request with assertions and validations with data drivern approach.


# Technologies

-Java
-Rest Assured
-Selenium
-TestNG
-Maven
-Apache POI (for data-driven testing)
-JSON (for response validation)


# Installation and setup details

1. Java JDK
2. Eclipse
3. Selenium
4. Github Desktop

Run Code--> Right-click → Run as TestNG Test


# Project Structure

src
├── test
│ ├── java
│ │ ├── Endpoints
│ │ │ ├── APIPost.java
│ │ ├── Request.Payload
│ │ │ └── Payload.java
│ │ ├── Tests
│ │ │ └── Testcases.java
│ │ ├── Utils
│ │ │ └── Excel_Testdata.java
│ └── resources
│ │ ├── config.properties
│ │ └── Testdata.xlsx


# File Details

APIPost-- For API Connection
Payload-- POJO classes for request bodies(Getters and setters)
TestCases-- for Assertions and validation
Utils-- for common reusable logic(Fetch data from Excel)
Config.properties-- Endpoints
Testdata-- for data-driven testing
pom.xml-- dependency and build management
TestNG XML-- for execution control


# Framework Covers:

Automated POST and GET request using Restassured library using datadriver approach. Executed total 4 Testcases 2 for each method. Validated status code, response body and headers.
