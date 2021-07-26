package pl.mateusztester.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.mateusztester.utils.SeleniumHelperGmail;

import java.util.List;


public class GmailSearchMassagePage {

    @FindBy(xpath = "//*[@id=\"L2AGLb\"]/div")
    private WebElement acceptCookies;

    @FindBy(name = "q")
    private WebElement findSreacher;

    @FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[2]/div[2]/div[2]/center/input[1]")
    private WebElement enterSreacher;

    @FindBy(xpath = "//h3[@class='LC20lb DKV0Md' and text()='Poczta - Najlepsza Poczta, największe załączniki - WP']")
    private WebElement wpPoczta;

    @FindBy(xpath = "//label[text()='Adres e-mail']")
    private WebElement login;

    @FindBy(id = "login")
    private WebElement enterLogin;

    @FindBy(xpath = "//label[text()='Hasło']")
    private WebElement password;

    @FindBy(id = "password")
    private WebElement enterPassword;

    @FindBy(xpath = "//div[@class='Checkbox']")
    private WebElement selectAllCheckboxs;





    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;
    @FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
    private WebElement searchHotelInput;
    @FindBy(name = "checkin")
    private WebElement checkInInput;
    @FindBy(name = "checkout")
    private WebElement checkoutInput;
    @FindBy(id = "travellersInput")
    private WebElement travellersInput;
    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;
    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;
    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;
    @FindBy(xpath = "//*[@id='li_myaccount']")
    private List<WebElement> myAccountLink;
    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> signUpLink;


    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger();

    public GmailSearchMassagePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setAcceptCookies(){
        acceptCookies.click();
    }

    public void clickAndSandKeys(String findGoogle){
        findSreacher.click();
        findSreacher.sendKeys(findGoogle);
        findSreacher.sendKeys(Keys.ENTER);
        wpPoczta.click();

    }

    public void loginAndPassword(String emailAdres, String emailPassword){
        login.click();
        enterLogin.sendKeys(emailAdres);
        password.click();
        enterPassword.sendKeys(emailPassword, Keys.ENTER);
    }

    public void selectAllCheckbox(){
        selectAllCheckboxs.click();
    }








    public void setCity(String cityName) {
        logger.info("setting city" + cityName);
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        SeleniumHelperGmail.waitForElementToExist(driver, By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
        logger.info("setting city done");
    }
    public void setDates(String checkin, String checkout) {
logger.info("Setting dates checkin: " + checkin + "checkout: " + checkout);
        checkInInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
        logger.info("checing dates done");
    }
    public void setTravellers(int adultsToAdd, int childToAdd) {
        logger.info("addinf adults: " + adultsToAdd + "and kids: " + childToAdd);
        travellersInput.click();
        addTraveler(adultPlusBtn, adultsToAdd);
        addTraveler(childPlusBtn, childToAdd);
        logger.info("adding travelers done");
    }
    private void addTraveler(WebElement travelerBtn, int numberOfTravelers) {
        for (int i = 0; i < numberOfTravelers; i++) {
            SeleniumHelperGmail.waitForElementToBeVisible(driver, travelerBtn);
            travelerBtn.click();
        }
    }
    public void performSearch() {
        logger.info("performing search");
        searchButton.click();
        logger.info("performing search done");
    }
    public void openSignUpFrom() {
        myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        signUpLink.get(1).click();
    }
}
