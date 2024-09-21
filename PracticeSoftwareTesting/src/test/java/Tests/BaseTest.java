package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    WebDriver driver;
    protected static ExtentReports report;
    protected static ExtentTest test ;
    @BeforeSuite
    public void setup(){
        report = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(new File("reports/report.html"));
        spark.config().setTheme(Theme.DARK);
        report.attachReporter(spark);
    }
    @BeforeClass
    public void Precondition(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicesoftwaretesting.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
    @AfterSuite
    public void down() throws IOException {
        report.flush();
        Desktop.getDesktop().open(new File("reports/report.html"));
    }

}
