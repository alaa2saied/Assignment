package Tests;

import Pages.HomePage;
import com.github.javafaker.Faker;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.time.Duration;

public class HomeTest extends BaseTest{

    HomePage page ;
    SoftAssert softAssert = new SoftAssert();
    WebDriverWait wait;
    Faker faker = new Faker();
    String email;
    String password;
    FileOutputStream fileOutputStream ;
    FileInputStream fileInputStream;
    XSSFWorkbook workbook;
    XSSFWorkbook workbookOut;
    XSSFSheet sheet;
    XSSFSheet sheetOut;
    XSSFRow row2;
    XSSFRow row1;
    XSSFRow row ;
    XSSFCell cell;



    @Test
    public void verifyThatCheckOutIsWorking() throws IOException {
        fileOutputStream = new FileOutputStream(new File("TestData/TestDataDriven.xlsx"));
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Data");
        row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("Email");
        row1.createCell(1).setCellValue("Password");
        row1.createCell(2).setCellValue("FirstName");
        row1.createCell(3).setCellValue("Lastname");
        row1.createCell(4).setCellValue("DateOfBirth");
        row1.createCell(5).setCellValue("FullAddress");
        row1.createCell(6).setCellValue("City");
        row1.createCell(7).setCellValue("ZipCode");
        row1.createCell(8).setCellValue("State");
        row1.createCell(9).setCellValue("Country");
        row1.createCell(10).setCellValue("PhoneNumber");


        row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue(faker.internet().safeEmailAddress());
        row2.createCell(1).setCellValue(faker.internet().password(8,15,true,true,true));
        row2.createCell(2).setCellValue(faker.name().firstName());
        row2.createCell(3).setCellValue(faker.name().lastName());
        row2.createCell(4).setCellValue("01/26/1999");
        row2.createCell(5).setCellValue(String.valueOf(faker.address()));
        row2.createCell(6).setCellValue(faker.address().city());
        row2.createCell(7).setCellValue(faker.address().zipCode());
        row2.createCell(8).setCellValue(faker.address().state());
        row2.createCell(9).setCellValue(faker.country().name());
        row2.createCell(10).setCellValue("878743678");
        workbook.write(fileOutputStream);


        fileInputStream = new FileInputStream("TestData/TestDataDriven.xlsx");
        workbookOut = new XSSFWorkbook(fileInputStream);
        sheetOut = workbookOut.getSheet("Data");

        test = report.createTest("report for assertion");
        //set save email address
        email = sheet.getRow(1).getCell(0).toString();
        //set password include special character , uppercase and digits
        password =sheet.getRow(1).getCell(1).toString();

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
        page.writeName(sheet.getRow(1).getCell(2).toString(),sheet.getRow(1).getCell(3).toString());
        //enter date of birth
        page.writeDateOfBirth(sheet.getRow(1).getCell(4).toString());
        //enter city , full address and zip code
        page.writeAddress(sheet.getRow(1).getCell(5).toString(),sheet.getRow(1).getCell(6).toString(),sheet.getRow(1).getCell(7).toString());
        //choose country and enter state
        page.writeCountry(sheet.getRow(1).getCell(8).toString(),sheet.getRow(1).getCell(9).toString());
        //enter faker phone
        page.writePhone(sheet.getRow(1).getCell(10).toString());
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
