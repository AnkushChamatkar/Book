package pageClasses;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.sl.draw.geom.GuideIf.Op;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import BaseMethod.readConfigProperty;

public class contactInfoPage {
	
	@FindBy(xpath="((//*[@id='user_as']//label)[2])")
	private WebElement indivudial;
	
	@FindBy(xpath="(//*[@class='PhoneInputCountrySelect'])")
	private WebElement country;
	
	@FindBy(xpath="(//input[@id='mobile'])")
	private WebElement mobileNumber;
	
	@FindBy(xpath="(//*[@id='hear_about_baa'])")
	private WebElement hearFrom;
	
	@FindBy(xpath="(//*[@class='ant-select-item ant-select-item-option'])")
	private List<WebElement> mediaOptions;
	
	@FindBy(xpath="(//*[@id='first_name'])")
	private WebElement firstName;
	
	@FindBy(xpath="(//*[@id='last_name'])")
	private WebElement lastName;
	
	@FindBy(xpath="(//*[@id='email'])")
	private WebElement email;
	
	@FindBy(xpath="(//*[@id='password'])")
	private WebElement password;
	
	@FindBy(xpath="(//*[@id='terms_privacy'])")
	private WebElement agreeCheckbox;
	
	@FindBy(xpath="(//*[@id='recaptcha-anchor'])")
	private WebElement captchaCheckbox;
	
	@FindBy(xpath="(//button[@type='submit'])")
	private WebElement submitButton;
	
	WebDriver driver;
	Actions actions; 
	JavascriptExecutor js;
	
	public contactInfoPage (WebDriver driver) {
		this.driver=driver;
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	
	public void representing () {
		actions.click(indivudial).perform();
	}
	
	public void chooseCountry () {
		Select sel = new Select(country);
		sel.selectByVisibleText("India");
	}
	
	public void insertMobileNo () throws Exception {
		String mnoValue = readConfigProperty.readDataFromConfig("MNO");
		actions.moveToElement(mobileNumber).click().sendKeys(mnoValue).build().perform();
//		actions.click(mobileNumber).sendKeys("9325183920").build().perform();
	}
	
	public void hereUsFrom () {
		actions.click(hearFrom).perform();
		
		for (WebElement suggestedoption : mediaOptions) {
			
			String title = suggestedoption.getAttribute("title");
			
			System.out.println(title);
			
			if(title.contains("Social media (Instagram, Facebook, YouTube, Linkedln, etc.)")) {
				actions.click(suggestedoption).perform();
			}
		}
	}
	
	public void insertPersonalDetails () throws Exception {
		String firstNameValue = readConfigProperty.readDataFromConfig("fName");
		
		actions.click(firstName).sendKeys(firstNameValue).build().perform();
		
		String lastNameValue = readConfigProperty.readDataFromConfig("LName");
		
		actions.click(lastName).sendKeys(lastNameValue).build().perform();
		
        js.executeScript("arguments[0].scrollIntoView();", email);
        
		String gmailValue = readConfigProperty.readDataFromConfig("Gmail");

		actions.click(email).sendKeys(gmailValue).build().perform();
		
		String passwordValue = readConfigProperty.readDataFromConfig("Pass");
		
		actions.click(password).sendKeys(passwordValue).build().perform();
	}
	
	public void termsAndPolicy() {
        js.executeScript("arguments[0].scrollIntoView();", agreeCheckbox);
		actions.click(agreeCheckbox).perform();
	}
	
	public void captcha() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(captchaCheckbox));
        js.executeScript("arguments[0].scrollIntoView();", captchaCheckbox);
		actions.click(captchaCheckbox).perform();
	}
	
	public void saveAndSubmit () {
        js.executeScript("arguments[0].scrollIntoView();", submitButton);
		actions.click(submitButton).perform();
	}
	
}
