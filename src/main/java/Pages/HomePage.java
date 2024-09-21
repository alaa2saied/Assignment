package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){
        super(driver);
    }

    //Locators

    private final By pliersLocator = By.xpath("//div[@class=\"col-md-9\"]/div[1]/a[2]/div[2]/h5");
    private final By addToCartButtonLocator = By.xpath("//div[@class=\"btn-group mt-2\"]/button[1]");
    private final By cartButtonLocator = By.xpath("//div[@id=\"navbarSupportedContent\"]/ul/li[5]/a/fa-icon");
    private final By processToCheckLocator = By.cssSelector("div[class=\"float-end ng-star-inserted\"] button[class=\"btn btn-success\"]");
    private final By registerButtonLocator = By.cssSelector("a[href=\"/auth/register\"]");
    private final By firstNameLocator = By.cssSelector("input[id=\"first_name\"]");
    private final By lastNameLocator = By.cssSelector("input[id=\"last_name\"]");
    private final By dateOfBirthLocator = By.cssSelector("input[id=\"dob\"]");
    private final By addressLocator = By.cssSelector("input[id=\"address\"]");
    private final By postCodeLocator = By.cssSelector("input[id=\"postcode\"]");
    private final By cityLocator = By.cssSelector("input[id=\"city\"]");
    private final By stateLocator = By.cssSelector("input[id=\"state\"]");
    private final By countryLocator = By.cssSelector("select[id=\"country\"]");
    private final By phoneLocator = By.cssSelector("input[id=\"phone\"]");
    private final By emailAddressLocator = By.cssSelector("input[id=\"email\"]");
    private final By passwordLocator = By.cssSelector("input[id=\"password\"]");
    private final By registerButtonLocator2 = By.cssSelector("button[class=\"btnSubmit mb-3\"]");
    private final By emailLocator = By.cssSelector("input[id=\"email\"]");
    private final By passwordLoginLocator = By.cssSelector("input[id=\"password\"]");
    private final By loginButtonLocator = By.cssSelector("input[class=\"btnSubmit\"]");
    private final By cartButtonLoginLocator = By.xpath("//div[@id=\"navbarSupportedContent\"]/ul/li[5]/a/span");
    private final By processToCheckLoginLocator = By.xpath("//div[@class=\"wizard-steps horizontal\"]/aw-wizard-step[1]/app-cart/div/div/button");
    private final By checkOutButtonLocator = By.xpath("//div[@class=\"wizard-steps horizontal\"]/aw-wizard-step[2]/app-login/div/div/div/div/button");
    private final By successMessageLocator = By.xpath("//div[@class=\"wizard-steps horizontal\"]/aw-wizard-step[2]/app-login/div/div/div/p");
    private final By check3Locator = By.xpath("//div[@class=\"wizard-steps horizontal\"]/aw-wizard-step[3]/app-address/div/div/div/div/button");
    private final By billingInformationLocator = By.xpath("//div[@class=\"wizard-steps horizontal\"]/aw-wizard-step[3]/app-address/div/div/div/h3");
    private final By paySelectLocator = By.cssSelector("select[id=\"payment-method\"]");
    private final By confirmLocator = By.xpath("//app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/div/button");
    private final By successMessagePaymentLocator = By.xpath("//app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/form/div[2]/div");


    //Methods

    public void clickOnPliers(){
        clickFeature(pliersLocator);
    }

    public void clickOnAddToCartButton(){
        clickFeature(addToCartButtonLocator);
    }
    public void clickOnCartButton(){
        clickFeature(cartButtonLocator);
    }
    public void clickOnProcessToCheck(){
        clickFeature(processToCheckLocator);
    }
    public void clickOnRegister(){
        clickFeature(registerButtonLocator);
    }
    public void writeName(String firstName, String lastName){
        writeFeature(firstNameLocator,firstName);
        writeFeature(lastNameLocator,lastName);
    }
    public void writeDateOfBirth(String date){
        writeFeature(dateOfBirthLocator,date);

    }
    public void writeAddress(String address,String city,String postCode){
        writeFeature(addressLocator,address);
        writeFeature(postCodeLocator,postCode);
        writeFeature(cityLocator,city);
    }
    public void writeCountry(String state, String country){
        writeFeature(stateLocator,state);
        Select selectCountry = new Select(returnWebElement(countryLocator));
        selectCountry.selectByVisibleText(country);
    }
    public void writePhone(String phone){
        writeFeature(phoneLocator,phone);
    }
    public void writeEmailAndPassword(String email, String password){
        writeFeature(emailAddressLocator,email);
        writeFeature(passwordLocator,password);
        clickFeature(registerButtonLocator2);
    }
    public void enterEmailAndPassword(String email, String password){
        writeFeature(emailLocator,email);
        writeFeature(passwordLoginLocator,password);
        clickFeature(loginButtonLocator);

    }
    public void clickOnCartLogin(){
        clickFeature(cartButtonLoginLocator);
    }
    public void clickOnProcessToCheckLogin(){
        clickFeature(processToCheckLoginLocator);
    }
    public void clickOnCheckOutButton(){
        clickFeature(checkOutButtonLocator);
    }
    public WebElement returnMessage(){
        return returnWebElement(successMessageLocator);
    }
    public void clickOnCheckOut3(){
        clickFeature(check3Locator);
    }
    public WebElement getBillingMessage(){
        return returnWebElement(billingInformationLocator);
    }
    public void selectPayment(String payment){
        Select selectPaymentMethode = new Select(returnWebElement(paySelectLocator));
        selectPaymentMethode.selectByVisibleText(payment);
        clickFeature(confirmLocator);
    }
    public WebElement printSuccessMessage(){
        return returnWebElement(successMessagePaymentLocator);
    }







}
