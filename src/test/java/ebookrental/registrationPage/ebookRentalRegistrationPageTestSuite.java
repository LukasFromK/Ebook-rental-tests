package ebookrental.registrationPage;

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

public class ebookRentalRegistrationPageTestSuite {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.navigate().to("https://ta-bookrental-fe.onrender.com/register");
    }

    @AfterEach
    public void testDown() {
        driver.close();
    }

    @Test
    public void verificationWhetherLoginButtonOpensLoginWebpage() {
        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));

        WebElement loginButton = driver.findElement(By.id("login-btn"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOf(passwordRepeatField));

        WebElement formTitle = driver.findElement(By.xpath("//*[@class=\"sub-title\"]"));

        assertEquals("LOG IN", formTitle.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredOnTheWebsite() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("testlogin@testlogin.test");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherUserAlreadyInTheWebsitesDatabaseWillBeRegisteredAgainWithTheSameData() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("testlogin@testlogin.test");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        loginField.clear();
        loginField.sendKeys("testlogin@testlogin.test");

        passwordField.clear();
        passwordField.sendKeys("testpassword");

        passwordRepeatField.clear();
        passwordRepeatField.sendKeys("testpassword");

        registerButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("This user already exist!", alertField.getText());
    }

    @Test
    public void verificationWhetherAnotherNewUserWillBeRegisteredWhenOneUserWithDifferentDataAlreadyExistsInTheDatabase() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("testlogin@testlogin.test");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        loginField.clear();
        loginField.sendKeys("newtestlogin@testlogin.test");

        passwordField.clear();
        passwordField.sendKeys("newtestpassword");

        passwordRepeatField.clear();
        passwordRepeatField.sendKeys("newtestpassword");

        registerButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfWhenRegisteringHeEntersUniquePasswordAndLoginThatIsUsedByAnotherUserAlreadyRegisteredInTheDatabase() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("testlogin@testlogin.test");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        loginField.clear();
        loginField.sendKeys("testlogin@testlogin.test");

        passwordField.clear();
        passwordField.sendKeys("mynewtestpassword");

        passwordRepeatField.clear();
        passwordRepeatField.sendKeys("mynewtestpassword");

        registerButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("This user already exist!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfWhenRegisteringHeEntersUniqueLoginAndPasswordThatIsUsedByAnotherUserAlreadyRegisteredInTheDatabase() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("testlogin@testlogin.test");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        loginField.clear();
        loginField.sendKeys("mynewtestlogin@testlogin.test");

        passwordField.clear();
        passwordField.sendKeys("testpassword");

        passwordRepeatField.clear();
        passwordRepeatField.sendKeys("testpassword");

        registerButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }
    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheDataHeProvidesInThePasswordAndRepeatPasswordFieldsAreDifferent() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("uniquetestlogin@testlogin.test");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("completlyoldtestpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("completlynewtestpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("The passwords don't match", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfHeDoesntCompleteTheDataRequiredForRegistration() {
        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfDuringRegistrationHeOnlyCompletesTheLoginFieldCreatingUniqueLogin() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("absolutlyuniquetestlogin@testlogin.test");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfDuringRegistrationHeOnlyCompletesThePasswordFieldCreatingUniquePassword() {
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("absolutlyuniquetestpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfDuringRegistrationHeOnlyCompletesTheRepeatPasswordFieldCreatingUniquePassword() {
        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("absolutlynewanduniquetestpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfDuringRegistrationHeOnlyCompletesThePasswordAndRepeatPasswordFieldsCreatingUniquePassword() {
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("completlynewanduniquetestpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("completlyuniqueandnewtestpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfDuringRegistrationHeOnlyCompletesTheLoginAndPasswordFieldsCreatingUniqueLoginAndUniquePassword() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("amazingtestlogin@testlogin.test");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("amazingtestpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfDuringRegistrationHeOnlyCompletesTheLoginAndRepeatPasswordFieldsCreatingUniqueLoginAndUniquePassword() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("appriopriatetestlogin@testlogin.test");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("appriopriatetestpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfHeOnlyCompletesLoginFieldDuringRegistrationUsingTheLoginOfExistingUserFromDatabase() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("testlogin@testlogin.test");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        loginField.clear();
        loginField.sendKeys("testlogin@testlogin.test");

        passwordField.clear();

        passwordRepeatField.clear();

        registerButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfHeOnlyCompletesThePasswordFieldDuringRegistrationUsingTheLoginOfExistingUserFromDatabase() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("testlogin@testlogin.test");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        loginField.clear();

        passwordField.clear();
        passwordField.sendKeys("testpassword");

        passwordRepeatField.clear();

        registerButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfHeOnlyCompletesTheRepeatPasswordFieldDuringRegistrationUsingTheLoginOfExistingUserFromDatabase() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("testlogin@testlogin.test");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        loginField.clear();

        passwordField.clear();

        passwordRepeatField.clear();
        passwordRepeatField.sendKeys("testpassword");

        registerButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfHeOnlyCompletesThePasswordAndRepeatPasswordFieldsDuringRegistrationUsingTheLoginOfExistingUserFromDatabase() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("testlogin@testlogin.test");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        loginField.clear();

        passwordField.clear();
        passwordField.sendKeys("testpassword");

        passwordRepeatField.clear();
        passwordRepeatField.sendKeys("testpassword");

        registerButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsSingleSpace() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys(" ");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsAWordWithSingleSpaceAtTheEndAndThereIsUserInTheDatabaseWhoseLoginIsTheSameWordButWithSpaceBetweenTheLetters() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("us er");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        loginField.clear();
        loginField.sendKeys("user ");

        passwordField.clear();
        passwordField.sendKeys("userstestpassword");

        passwordRepeatField.clear();
        passwordRepeatField.sendKeys("userstestpassword");

        registerButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("This user already exist!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfThePasswordIsSingleSpace() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("testuser1@testlogin.test");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(" ");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys(" ");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsSingleNumber() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("1");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfThePasswordIsSingleNumber() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("userlogin");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("0");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("0");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsSingleLetterFromEnglishAlphabet() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("z");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfThePasswordIsSingleLetterFromEnglishAlphabet() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("loginoftestuser");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("v");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("v");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsSingleCapitalLetterFromEnglishAlphabet() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("F");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfThePasswordIsSingleCapitalLetterFromEnglishAlphabet() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("user1985");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("F");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("F");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsAWordConsistingOfLowercaseLettersOfTheEnglishAlphabetWhileThereIsAlreadyRegisteredUserInTheDatabaseWhoseLoginIsTheSameWordButWrittenInCapitalLettersOfTheEnglishAlphabet() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("SPARROW");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        loginField.clear();
        loginField.sendKeys("sparrow");

        passwordField.clear();
        passwordField.sendKeys("sparrowtestpassword");

        passwordRepeatField.clear();
        passwordRepeatField.sendKeys("sparrowtestpassword");

        registerButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("This user already exist!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsSingleSpecialSign() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("$");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("testpassword");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfThePasswordIsSingleSpecialSign() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("user2023");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("#");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("#");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginAndPasswordWillHaveDifferentNumberOfCharacters() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("user1873");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("73");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("73");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsLongStringOfCharactersContainingLowercaseAndCapitalLettersOfEnglishAlphabetNumbersSpecialSignsAndSpaces() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("q1! w2@ e3# r4$t5%y6^u7&i8*o9(p0)-+=?pOIlkjhgfdsamnbvcxzasdfghjklqwertyuiopasdfg1234567890!@#$%^& 8(");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("#");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("#");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfThePasswordIsLongStringOfCharactersContainingLowercaseAndCapitalLettersOfEnglishAlphabetNumbersSpecialSignsAndSpaces() {
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys("u");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("q1! w2@ e3# r4$t5%y6^u7&i8*o9(p0)-+=?pOIlkjhgfdsamnbvcxzasdfghjklqwertyuiopasdfg1234567890!@#$%^& 8(");

        WebElement passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys("q1! w2@ e3# r4$t5%y6^u7&i8*o9(p0)-+=?pOIlkjhgfdsamnbvcxzasdfghjklqwertyuiopasdfg1234567890!@#$%^& 8(");

        WebElement registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        WebElement alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }
}
