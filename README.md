# Kata API Testing in Java

API Testing and Java Exercise: Setting up a Basic API Test Automation Framework.

## Objective
The objective of this exercise is to evaluate your knowledge on API testing and Java by setting up a basic API Test Automation framework using Rest-Assured and Cucumber. You will need to create a test suite that executes a few tests against one endpoint of a hotel booking website and evaluates their responses.

## Background
The application under test is a simple hotel booking website where you can book a room and also send a form with a request.

The website can be accessed at https://automationintesting.online/.

The Swagger documentation for the two endpoints you will be testing can be found at:

Booking endpoint: https://automationintesting.online/booking/swagger-ui/index.html  
Optionally, you also have the Authentican endpoint: https://automationintesting.online/auth/swagger-ui/index.html

### Swagger
This website is an external application which is not in our control.  
We noticed that the Swagger documentation is sometimes not available on the mentioned URL above.  
As a backup, you can find the Swagger documentation in this repository at [src/test/resources/spec/booking.yaml](src/test/resources/spec/booking.yaml)

The Open API Spec file is only supported in the Ultimate version of IntelliJ IDEA. But you can copy the content of the file and paste it in an online Swagger editor like https://editor.swagger.io/ to visualize the API documentation.

### Authentication
In order to authenticate yourself, the required credentials are:
* Username: `admin`
* Password: `password`

## Task
You are provided with an extremely basic API test project.

Please clone the project and create a new branch with your name. At the end, please push your branch to this project.

The project to start from, can be found here: https://github.com/freddyschoeters/API_Testing_kata

Your task is to set up an API Test Automation framework from this project using Java, Rest-Assured, and Cucumber (feel free to add more dependencies if required).

It is up to you to define the test cases. You don’t need to have a full coverage, but you need to show enough variation on the types of tests that you would need to write and execute, and what to check in the response.

This kata has the purpose to evaluate both your technical skills as well as your testing skills.

`For this task, you will use the booking endpoint.`


## Requirements
* Use Java as the programming language
* Use Rest-Assured as the API testing library
* Use Cucumber as the BDD framework
* Design your codebase using a proper Java design pattern
* Write good tests with correct checks
* Use Git for version control and push your codebase to an open GitHub repository
* Make regular commits to demonstrate your progress


## Deliverables
* Your branch pushed in the provided project.
* A comprehensive test suite covering the scenarios mentioned above
* A well-structured codebase with proper design patterns and comments
* Regular commits demonstrating your progress

## Evaluation Criteria
* Being able to successfully run the tests
* Correctness and completeness of the test suite
* Quality of the codebase (design patterns, structure, code quality, …)
* Use of Rest-Assured and Cucumber features
* Commit history and progress demonstration


## API Specification Deviations & Observations

### Happy-path coverage
Happy-path scenarios validate that the API behaves as expected when valid input
and authentication are provided (e.g. successful booking creation, retrieval,
update and deletion).

### Negative and validation scenarios
Negative scenarios focus on:
- missing or invalid authentication
- invalid input values
- missing required fields
- non-existing resources

These tests verify that the API fails safely and returns meaningful HTTP status codes.

### Required field validation
For booking creation, required-field validation is tested explicitly by sending
requests with missing mandatory fields. This ensures that server-side validation
is enforced and not solely relied upon by client-side checks.


The following deviations were identified during automated API testing.
Expected behaviors are based on the provided OpenAPI specification 

GET /booking/{id}

Invalid id format (e.g. /booking/abc, /booking/-1, /booking/1.5)

Expected: 400 Bad Request (invalid path parameter format)

Actual: 401 

Note: This behavior is not explicitly documented in the spec.

POST /booking

Checkout before checkin

Expected: 400 Bad Request (validation error)

Actual: 409 Conflict

Note: The API returns a conflict instead of a validation error for an invalid date range.

DELETE /booking/{id}

Delete non-existing booking

Expected: 404 Not Found (resource does not exist)

Actual: 500 Internal Server Error

Note: A non-existing resource should not cause a server error.

PATCH /booking/{id}

Partially update booking with valid patch payload

Expected: 200 OK (booking partially updated)

Actual: 405 Method Not Allowed

Note: The endpoint/method appears not implemented or disabled on the server.

Partially update booking without authentication

Expected: 401 Unauthorized (missing/invalid token)

Actual: 405 Method Not Allowed

Note: Since the method returns 405 even without authentication, the request is likely rejected before auth is evaluated.
