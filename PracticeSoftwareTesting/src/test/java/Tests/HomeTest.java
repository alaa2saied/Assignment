package Tests;

import Pages.HomePage;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class HomeTest extends BaseTest{

    HomePage page ;
    SoftAssert softAssert = new SoftAssert();
    WebDriverWait wait;
    Faker faker = new Faker();
    String email;
    String password;

    @Test
    public void verifyThatCheckOutIsWorking(){
        test = report.createTest("report for assertion");
        //set save email address
        email = faker.internet().safeEmailAddress();
        //set password include special character , uppercase and digits
        password = faker.internet().password(8,15,true,true,true);
        //create object from home page
        page =new HomePage(driver);
        //assert website open correctly
        softAssert.assertEquals(driver.getCurrentUrl(),"https://practicesoftwaretesting.com");
        test.pass("Website is opened successfully");
        //create wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //open product
        page.clickOnPliers();
        //add this product to cart
        page.clickOnAddToCartButton();
        //wait until invisibility of overlap of alert
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("css-m16exc")));
        //click on cart button
        page.clickOnCartButton();
        //continue process to check out
        page.clickOnProcessToCheck();
        //register as anew user
        page.clickOnRegister();
        //enter first and last name
        page.writeName(faker.name().firstName(),faker.name().lastName());
        //enter date of birth
        page.writeDateOfBirth("01/26/1999");
        //enter city , full address and zip code
        page.writeAddress(String.valueOf(faker.address()),faker.address().city(),faker.address().zipCode());
        //choose country and enter state
        page.writeCountry(faker.address().state(),faker.country().name());
        //enter faker phone
        page.writePhone("878743678");
        //enter email and password
        page.writeEmailAndPassword(email,password);
        //wait until invisibility of overlap
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("css-m16exc")));
        //enter in login the same email and password
        page.enterEmailAndPassword(email,password);
        //wait until invisibility of overlap
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("css-m16exc")));
        //click on cart to continue checkout
        page.clickOnCartLogin();
        page.clickOnProcessToCheckLogin();
        //assert checkout page is open successfully
        softAssert.assertTrue(page.returnMessage().getText().toLowerCase().contains("checkout."));
        test.pass("Page of checkout is opened successfully");
        page.clickOnCheckOutButton();
        //assert billing page is open successfully
        softAssert.assertTrue(page.getBillingMessage().getText().toLowerCase().contains("billing"));
        test.pass("Billing page is opened successfully");
        page.clickOnCheckOut3();
        //choose the payment
        page.selectPayment("Cash on Delivery");
        //print success message
        System.out.println(page.printSuccessMessage().getText());
    }
}
