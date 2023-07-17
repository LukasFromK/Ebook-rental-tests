package ebookrental.loginPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ebookRentalLoginPageTestSuite {
    WebDriver driver;
    WebElement loginButton;
    WebElement loginField;
    WebElement passwordField;
    WebElement passwordRepeatField;
    WebElement alertField;
    WebElement titleField;
    String testUserLogin;
    String testUserPassword;
    String secondTestUserLogin;
    String secondTestUserPassword;

    private void registerTestUser(String login, String password){
        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-repeat")));

        loginField = driver.findElement(By.id("login"));
        loginField.sendKeys(login);

        passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys(password);

        WebElement secondRegisterButton = driver.findElement(By.id("register-btn"));
        secondRegisterButton.click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        loginButton = driver.findElement(By.id("login-btn"));
        loginButton.click();
        driverWait.until(ExpectedConditions.invisibilityOf(passwordRepeatField));
    }

    private void logInTestUser(String login, String password){
        loginField = driver.findElement(By.id("login"));
        loginField.sendKeys(login);

        passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("login-btn"));
        loginButton.click();
    }

    @BeforeEach
    public void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.navigate().to("https://ta-bookrental-fe.onrender.com/login");
    }

    @AfterEach
    public void testDown() {
        driver.close();
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemWithCorrectLoginAndCorrectPasswordIfOnlyHeIsRegisteredInTheDatabase() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemWithWrongLoginAndWrongPasswordIfOnlyHeIsRegisteredInTheDatabase() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser("wrongtestlogin", "wrongtestpassword");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemWithCorrectLoginAndCorrectPasswordIfAnotherUserIsAlsoRegisteredInTheDatabase() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);
        secondTestUserLogin = "secondtestlogin@testlogin.test";
        secondTestUserPassword = "secondTestPassword";
        registerTestUser(secondTestUserLogin, secondTestUserPassword);

        logInTestUser(secondTestUserLogin, secondTestUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemWithWrongLoginAndWrongPasswordIfAnotherUserIsAlsoRegisteredInTheDatabase() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);
        secondTestUserLogin = "secondtestlogin@testlogin.test";
        secondTestUserPassword = "secondTestPassword";
        registerTestUser(secondTestUserLogin, secondTestUserPassword);

        logInTestUser("wrongsecondtestlogin", "wrongsecondtestpassword");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemWithWrongLoginAndCorrectPassword() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser("wrongtestlogin", testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemWithCorrectLoginAndWrongPassword() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, "wrongtestpassword");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemWithoutEnteringLoginAndPassword() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        loginButton = driver.findElement(By.id("login-btn"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemEnteringOnlyCorrectLogin() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, "");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemEnteringOnlyWrongLogin() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser("wrongtestlogin@testlogin.test", "");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemEnteringOnlyCorrectPassword() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser("", testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemEnteringOnlyWrongPassword() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser("", "wrongtestpassword");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfTheLoginIsSingleNumber() {
        testUserLogin = "9";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfThePasswordIsSingleNumber() {
        testUserLogin = "greatloginforuser";
        testUserPassword = "0";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfTheLoginIsSingleLowercaseLetterFromEnglishAlphabet() {
        testUserLogin = "k";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfThePasswordIsSingleLowercaseLetterFromEnglishAlphabet() {
        testUserLogin = "simpletestlogin";
        testUserPassword = "f";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfTheLoginIsSingleCapitalLetterFromEnglishAlphabet() {
        testUserLogin = "L";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfThePasswordIsSingleCapitalLetterFromEnglishAlphabet() {
        testUserLogin = "smarttestlogin";
        testUserPassword = "P";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfTheLoginIsSingleSpecialSign() {
        testUserLogin = "&";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfThePasswordIsSingleSpecialSign() {
        testUserLogin = "specialtestlogin";
        testUserPassword = "@";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfTheLoginIsLongStringOfCharactersContainingLowercaseAndCapitalLettersOfTheEnglishAlphabetNumbersSpecialSignsAndSpaces() {
        testUserLogin = "q1! w2@ e3# r4$t5%y6^u7&i8*o9(p0)-+=?pOIlkjhgfdsamnbvcxzasdfghjklqwertyuiopasdfg1234567890!@#$%^& 8(";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfThePasswordIsLongStringOfCharactersContainingLowercaseAndCapitalLettersOfTheEnglishAlphabetNumbersSpecialSignsAndSpaces() {
        testUserLogin = "newlogin@newlogin.login";
        testUserPassword = "q1! w2@ e3# r4$t5%y6^u7&i8*o9(p0)-+=?pOIlkjhgfdsamnbvcxzasdfghjklqwertyuiopasdfg1234567890!@#$%^& 8(";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfTheLoginAndPasswordHaveTheDifferentNumberOfCharacters() {
        testUserLogin = "shortlogin";
        testUserPassword = "longtestpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfTheLoginAndPasswordHaveTheSameNumberOfCharacters() {
        testUserLogin = "thesamenumber";
        testUserPassword = "thesamenumber";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemWithCorrectPasswordAndIncorrectLoginThatDiffersFromTheCorrectOneCharacter() {
        testUserLogin = "abc";
        testUserPassword = "abc";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser("ab", testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemWithCorrectLoginAndIncorrectPasswordThatDiffersFromTheCorrectOneCharacter() {
        testUserLogin = "abc";
        testUserPassword = "abc";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, "ab");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemWithCorrectPasswordAndIncorrectLoginThatDiffersFromTheCorrectOneByOneSpaceAtTheEndOfTheLogin() {
        testUserLogin = "abc";
        testUserPassword = "abc";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser("abc ", testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemWithCorrectLoginAndIncorrectPasswordThatDiffersFromTheCorrectOneByOneSpaceAtTheEndOfThePassword() {
        testUserLogin = "abc";
        testUserPassword = "abc";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, "abc ");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemWithCorrectPasswordAndIncorrectLoginThatDiffersFromTheCorrectOneByOneSpaceInTheMiddleOfTheLogin() {
        testUserLogin = "abcd";
        testUserPassword = "abcd";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser("ab cd", testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemWithCorrectLoginAndIncorrectPasswordThatDiffersFromTheCorrectOneByOneSpaceInTheMiddleOfThePassword() {
        testUserLogin = "abcd";
        testUserPassword = "abcd";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, "ab cd");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfHeEntersSingleSpaceInTheLoginFieldAndEntersCorrectPasswordInThePasswordField() {
        testUserLogin = "abcd";
        testUserPassword = "abcd";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(" ", testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfHeEntersSingleSpaceInThePasswordFieldAndEntersCorrectLoginInTheLoginField() {
        testUserLogin = "abcd";
        testUserPassword = "abcd";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, " ");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfHeEntersSingleSpaceInTheLoginFieldAndInThePasswordField() {
        testUserLogin = "abcd";
        testUserPassword = "abcd";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(" ", " ");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfHeEntersCorrectLoginInThePasswordFieldAndEntersCorrectPasswordInTheLoginField() {
        testUserLogin = "testlogin";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserPassword, testUserLogin);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfHeEntersCorrectPasswordInTheLoginFieldAndLeavingThePasswordFieldEmpty() {
        testUserLogin = "testlogin";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserPassword, "");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfHeEntersCorrectLoginInThePasswordFieldAndLeavingTheLoginFieldEmpty() {
        testUserLogin = "testlogin";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser("", testUserLogin);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfByEnteringTheLoginInLowercaseLettersIfHeEnteredTheLoginInCapitalLettersDuringRegistration() {
        testUserLogin = "CAPITALLETTERS";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser("capitalletters", testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfByEnteringTheLoginInCapitalLettersIfHeEnteredTheLoginInLowercaseLettersDuringRegistration() {
        testUserLogin = "lowercaseletters";
        testUserPassword = "testpassword";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser("LOWERCASELETTERS", testUserPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]")));

        titleField = driver.findElement(By.xpath("//*[@class=\"sub-title flex-grow--1 margin-right--1\"]"));

        assertEquals("TITLES CATALOG", titleField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfByEnteringThePasswordInLowercaseLettersIfHeEnteredThePasswordInCapitalLettersDuringRegistration() {
        testUserLogin = "passwordwithcapitalletters@login.test";
        testUserPassword = "CAPITALLETTERS";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, "capitalletters");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherUserWillBeLoggedIntoTheSystemIfByEnteringThePasswordInCapitalLettersIfHeEnteredThePasswordInLowercaseLettersDuringRegistration() {
        testUserLogin = "passwordwithlowercaseletters@login.test";
        testUserPassword = "lowercaseletters";
        registerTestUser(testUserLogin, testUserPassword);

        logInTestUser(testUserLogin, "LOWERCASELETTERS");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("Login failed", alertField.getText());
    }

    @Test
    public void verificationWhetherSignUpButtonOpensRegisterWebpage() {
        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-repeat")));

        WebElement formTitle = driver.findElement(By.xpath("//*[@class=\"sub-title\"]"));

        assertEquals("SIGN UP", formTitle.getText());
    }
}
