# eBay Automation Project

## Introduction

This repository contains an automated testing framework for testing eBay's web application. The framework is designed using Java, Selenium WebDriver, TestNG, and ExtentReports for generating comprehensive test reports.

## Features

- **Selenium WebDriver**: Used for browser automation and interaction with web elements.
- **TestNG**: Used for organizing and executing test cases.
- **ExtentReports**: Used for generating HTML test reports with detailed test execution results.
- **JavaMail API**: Used for sending test reports via email.
- **Log4j**: Used for logging test events and errors.
- **Page Object Model (POM)**: Implemented to enhance test script maintainability and readability.

## Project Structure

- **src/main/java**: Contains the main Java source code for the project.
- **src/test/java**: Contains the test classes for automated testing.
- **src/test/resources**: Contains resources such as test data and configuration files.
- **testng.xml**: TestNG configuration file for defining test suites and parameters.
- **pom.xml**: Maven configuration file for managing project dependencies.
- **README.md**: Documentation file providing an overview of the project.

## Setup Instructions

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/abuzerhaseeb/eBay_Automation.git
    ```

2. Open the project in your preferred IDE.

3. Install the project dependencies using Maven:

    ```bash
    mvn clean install
    ```

4. Ensure that the required libraries and dependencies are downloaded and configured successfully.

## Running Tests

You can run the automated tests using the TestNG test runner. TestNG is configured to execute tests defined in the `testng.xml` file.

To run the tests:

1. Locate the `testng.xml` file in the project's root directory.

2. Right-click on the `testng.xml` file and select "Run As" > "TestNG Suite" in your IDE.

3. Alternatively, you can execute the tests using Maven:

    ```bash
    mvn test
    ```

## Test Cases

The test cases cover various scenarios related to eBay's web application. Each test case is designed to verify specific functionalities and features.

## Reporting

Test execution reports are generated using ExtentReports. After running the tests, you can find the HTML reports in the `reports` directory. These reports provide detailed information about test execution, including test results, logs, and screenshots (if configured).

## Contributors

- [Abuzer Haseeb](https://github.com/abuzerhaseeb)
