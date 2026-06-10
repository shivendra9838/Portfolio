# Calley Teams Full Setup Automation

Selenium WebDriver + TestNG Maven project for automating the Calley Team account setup flow:

- User registration
- Login
- Agent creation
- CSV list upload through Call List > Power Import

## Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager
- Page Object Model

## Project Structure

```text
CalleyTeamsFullSetup
|-- src
|   |-- main
|   |   |-- java
|   |   |   |-- BaseClass.java
|   |   |   `-- pomp_pages
|   |   |       |-- RegistrationPage.java
|   |   |       |-- LoginPage.java
|   |   |       |-- AgentPage.java
|   |   |       |-- CSVUploadPage.java
|   |   |       `-- DashboardPage.java
|   |   `-- resources
|   |       `-- data.properties
|   `-- test
|       |-- java
|       |   |-- RegistrationTest.java
|       |   `-- FullSetupTest.java
|       `-- resources
|           `-- SampleFile.csv
|-- pom.xml
`-- testng.xml
```

## How To Run

1. Install JDK 17+ and Maven.
2. Update `src/main/resources/data.properties` with fresh test data.
3. Run:

```bash
mvn clean test
```

Optional browser override:

```bash
mvn clean test -Dbrowser=edge
```

Supported browser values: `chrome`, `edge`, `firefox`.

## Notes

- Registration emails are made unique automatically by appending a timestamp before the `@`.
- Some Calley pages may change element attributes over time. The page classes use multiple fallback locators to keep the scripts resilient.
- The current live registration page includes Google reCAPTCHA. Selenium automation should not bypass CAPTCHA; use a test/staging environment with CAPTCHA disabled, or complete that step manually during a recorded demo.
