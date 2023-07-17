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
    WebElement loginField;
    WebElement passwordField;
    WebElement passwordRepeatField;
    WebElement registerButton;
    WebElement alertField;
    String testUserLogin;
    String testUserPassword;
    String testUserRepeatPassword;
    String secondTestUserLogin;
    String secondTestUserPassword;
    String secondTestUserRepeatPassword;

    private void registerTestUser(String login, String password, String repeatPassword){
        loginField = driver.findElement(By.id("login"));
        loginField.sendKeys(login);

        passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.sendKeys(repeatPassword);

        registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));
    }

    private void clearAllFields(){
        loginField = driver.findElement(By.id("login"));
        loginField.clear();
        passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordRepeatField = driver.findElement(By.id("password-repeat"));
        passwordRepeatField.clear();
    }

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
    public void verificationWhetherNewUserWillBeRegisteredOnTheWebsite() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        testUserRepeatPassword = "testpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherUserAlreadyInTheWebsitesDatabaseWillBeRegisteredAgainWithTheSameData() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        testUserRepeatPassword = "testpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);
        clearAllFields();
        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("This user already exist!", alertField.getText());
    }

    @Test
    public void verificationWhetherAnotherNewUserWillBeRegisteredWhenOneUserWithDifferentDataAlreadyExistsInTheDatabase() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        testUserRepeatPassword = "testpassword";

        secondTestUserLogin = "newtestlogin@testlogin.test";
        secondTestUserPassword = "newtestpassword";
        secondTestUserRepeatPassword = "newtestpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);
        clearAllFields();
        registerTestUser(secondTestUserLogin, secondTestUserPassword, secondTestUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfWhenRegisteringHeEntersUniquePasswordAndLoginThatIsUsedByAnotherUserAlreadyRegisteredInTheDatabase() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        testUserRepeatPassword = "testpassword";

        secondTestUserLogin = "testlogin@testlogin.test";
        secondTestUserPassword = "mynewtestpassword";
        secondTestUserRepeatPassword = "mynewtestpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);
        clearAllFields();
        registerTestUser(secondTestUserLogin, secondTestUserPassword, secondTestUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("This user already exist!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfWhenRegisteringHeEntersUniqueLoginAndPasswordThatIsUsedByAnotherUserAlreadyRegisteredInTheDatabase() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        testUserRepeatPassword = "testpassword";

        secondTestUserLogin = "mynewtestlogin@testlogin.test";
        secondTestUserPassword = "testpassword";
        secondTestUserRepeatPassword = "testpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);
        clearAllFields();
        registerTestUser(secondTestUserLogin, secondTestUserPassword, secondTestUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }
    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheDataHeProvidesInThePasswordAndRepeatPasswordFieldsAreDifferent() {
        testUserLogin = "uniquetestlogin@testlogin.test";
        testUserPassword = "completlyoldtestpassword";
        testUserRepeatPassword = "completlynewtestpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("The passwords don't match", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfHeDoesntCompleteTheDataRequiredForRegistration() {
        registerButton = driver.findElement(By.id("register-btn"));
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert__content")));

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfDuringRegistrationHeOnlyCompletesTheLoginFieldCreatingUniqueLogin() {
        testUserLogin = "absolutlyuniquetestlogin@testlogin.test";
        testUserPassword = "";
        testUserRepeatPassword = "";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfDuringRegistrationHeOnlyCompletesThePasswordFieldCreatingUniquePassword() {
        testUserLogin = "";
        testUserPassword = "absolutlyuniquetestpassword";
        testUserRepeatPassword = "";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfDuringRegistrationHeOnlyCompletesTheRepeatPasswordFieldCreatingUniquePassword() {
        testUserLogin = "";
        testUserPassword = "";
        testUserRepeatPassword = "absolutlynewanduniquetestpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfDuringRegistrationHeOnlyCompletesThePasswordAndRepeatPasswordFieldsCreatingUniquePassword() {
        testUserLogin = "";
        testUserPassword = "completlynewanduniquetestpassword";
        testUserRepeatPassword = "completlynewanduniquetestpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfDuringRegistrationHeOnlyCompletesTheLoginAndPasswordFieldsCreatingUniqueLoginAndUniquePassword() {
        testUserLogin = "amazingtestlogin@testlogin.test";
        testUserPassword = "amazingtestpassword";
        testUserRepeatPassword = "";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfDuringRegistrationHeOnlyCompletesTheLoginAndRepeatPasswordFieldsCreatingUniqueLoginAndUniquePassword() {
        testUserLogin = "appriopriatetestlogin@testlogin.test";
        testUserPassword = "";
        testUserRepeatPassword = "appriopriatetestpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfHeOnlyCompletesLoginFieldDuringRegistrationUsingTheLoginOfExistingUserFromDatabase() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        testUserRepeatPassword = "testpassword";

        secondTestUserPassword = "";
        secondTestUserRepeatPassword = "";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);
        clearAllFields();
        registerTestUser(testUserLogin, secondTestUserPassword, secondTestUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfHeOnlyCompletesThePasswordFieldDuringRegistrationUsingThePasswordOfExistingUserFromDatabase() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        testUserRepeatPassword = "testpassword";

        secondTestUserLogin = "";
        secondTestUserRepeatPassword = "";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);
        clearAllFields();
        registerTestUser(secondTestUserLogin, testUserPassword, secondTestUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfHeOnlyCompletesTheRepeatPasswordFieldDuringRegistrationUsingThePasswordOfExistingUserFromDatabase() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        testUserRepeatPassword = "testpassword";

        secondTestUserLogin = "";
        secondTestUserPassword = "";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);
        clearAllFields();
        registerTestUser(secondTestUserLogin, secondTestUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfHeOnlyCompletesThePasswordAndRepeatPasswordFieldsDuringRegistrationUsingThePasswordOfExistingUserFromDatabase() {
        testUserLogin = "testlogin@testlogin.test";
        testUserPassword = "testpassword";
        testUserRepeatPassword = "testpassword";

        secondTestUserLogin = "";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);
        clearAllFields();
        registerTestUser(secondTestUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsSingleSpace() {
        testUserLogin = " ";
        testUserPassword = "testpassword";
        testUserRepeatPassword = "testpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You can't leave fields empty", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsAWordWithSingleSpaceAtTheEndAndThereIsUserInTheDatabaseWhoseLoginIsTheSameWordButWithSpaceBetweenTheLetters() {
        testUserLogin = "us er";
        testUserPassword ="testpassword";
        testUserRepeatPassword = "testpassword";

        secondTestUserLogin = "user ";
        secondTestUserPassword = "userstestpassword";
        secondTestUserRepeatPassword = "userstestpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);
        clearAllFields();
        registerTestUser(secondTestUserLogin, secondTestUserPassword, secondTestUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("This user already exist!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfThePasswordIsSingleSpace() {
        testUserLogin = "testuser11@testlogin.test";
        testUserPassword = " ";
        testUserRepeatPassword = " ";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsSingleNumber() {
        testUserLogin = "1";
        testUserPassword = "testpassword";
        testUserRepeatPassword = "testpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfThePasswordIsSingleNumber() {
        testUserLogin = "userlogin";
        testUserPassword = "0";
        testUserRepeatPassword = "0";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsSingleLowercaseLetterFromEnglishAlphabet() {
        testUserLogin = "z";
        testUserPassword = "testpassword";
        testUserRepeatPassword = "testpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfThePasswordIsSingleLowercaseLetterFromEnglishAlphabet() {
        testUserLogin = "loginoftestuser";
        testUserPassword = "v";
        testUserRepeatPassword = "v";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsSingleCapitalLetterFromEnglishAlphabet() {
        testUserLogin = "F";
        testUserPassword = "testpassword";
        testUserRepeatPassword = "testpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfThePasswordIsSingleCapitalLetterFromEnglishAlphabet() {
        testUserLogin = "user1985";
        testUserPassword = "F";
        testUserRepeatPassword = "F";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsAWordConsistingOfLowercaseLettersOfTheEnglishAlphabetWhileThereIsAlreadyRegisteredUserInTheDatabaseWhoseLoginIsTheSameWordButWrittenInCapitalLettersOfTheEnglishAlphabet() {
        testUserLogin = "SPARROW";
        testUserPassword = "testpassword";
        testUserRepeatPassword= "testpassword";

        secondTestUserLogin = "sparrow";
        secondTestUserPassword = "sparrowtestpassword";
        secondTestUserRepeatPassword = "sparrowtestpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);
        clearAllFields();
        registerTestUser(secondTestUserLogin, secondTestUserPassword, secondTestUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("This user already exist!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsAWordConsistingOfCapitalLettersOfTheEnglishAlphabetWhileThereIsAlreadyRegisteredUserInTheDatabaseWhoseLoginIsTheSameWordButWrittenInLowercaseLettersOfTheEnglishAlphabet() {
        testUserLogin = "garlic";
        testUserPassword = "garlictestpassword";
        testUserRepeatPassword = "garlictestpassword";

        secondTestUserLogin = "GARLIC";
        secondTestUserPassword = "anothergarlicsparrowtestpassword";
        secondTestUserRepeatPassword = "anothergarlicsparrowtestpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);
        clearAllFields();
        registerTestUser(secondTestUserLogin, secondTestUserPassword, secondTestUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("This user already exist!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsSingleSpecialSign() {
        testUserLogin = "$";
        testUserPassword = "testpassword";
        testUserRepeatPassword = "testpassword";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfThePasswordIsSingleSpecialSign() {
        testUserLogin = "user2023";
        testUserPassword = "#";
        testUserRepeatPassword = "#";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginAndPasswordWillHaveDifferentNumberOfCharacters() {
        testUserLogin = "user1873";
        testUserPassword = "73";
        testUserRepeatPassword = "73";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginAndPasswordWillHaveTheSameNumberOfCharacters() {
        testUserLogin = "thesame@test";
        testUserPassword = "test@thesame";
        testUserRepeatPassword = "test@thesame";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfTheLoginIsLongStringOfCharactersContainingLowercaseAndCapitalLettersOfEnglishAlphabetNumbersSpecialSignsAndSpaces() {
        testUserLogin = "q1! w2@ e3# r4$t5%y6^u7&i8*o9(p0)-+=?pOIlkjhgfdsamnbvcxzasdfghjklqwertyuiopasdfg1234567890!@#$%^& 8(";
        testUserPassword = "#";
        testUserRepeatPassword = "#";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
    }

    @Test
    public void verificationWhetherNewUserWillBeRegisteredInTheDatabaseIfThePasswordIsLongStringOfCharactersContainingLowercaseAndCapitalLettersOfEnglishAlphabetNumbersSpecialSignsAndSpaces() {
        testUserLogin = "u";
        testUserPassword = "q1! w2@ e3# r4$t5%y6^u7&i8*o9(p0)-+=?pOIlkjhgfdsamnbvcxzasdfghjklqwertyuiopasdfg1234567890!@#$%^& 8(";
        testUserRepeatPassword = "q1! w2@ e3# r4$t5%y6^u7&i8*o9(p0)-+=?pOIlkjhgfdsamnbvcxzasdfghjklqwertyuiopasdfg1234567890!@#$%^& 8(";

        registerTestUser(testUserLogin, testUserPassword, testUserRepeatPassword);

        alertField = driver.findElement(By.className("alert__content"));

        assertEquals("You have been successfully registered!", alertField.getText());
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
}
