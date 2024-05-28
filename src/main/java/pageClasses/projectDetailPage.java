package pageClasses;

import java.io.IOException;
import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BaseMethod.readConfigProperty;


public class projectDetailPage {
	
	@FindBy(xpath="(//*[@class='ant-form-item-control-input-content'])")
	private WebElement jobTittle;

	@FindBy(xpath="(//*[@id='category_id']//span[@class='ant-radio-button ant-radio-button-checked'])")
	private WebElement category;
	
	@FindBy (xpath="//*[text()='Window']")
	private WebElement place;
	
	@FindBy (xpath="(//*[@class='ant-input-number-input-wrap'])[1]")
	private WebElement width;
	
	@FindBy (xpath="(//*[@class='ant-input-number-input-wrap'])[2]")
	private WebElement height;
	
	@FindBy (xpath="//*[text()='ft.']")
	private WebElement measureUnit;

	@FindBy(xpath="(//*[@class='ant-input css-6rzz9k'])")
	private WebElement location;
	
	@FindBy(xpath="((//*[@id='property_type']//label)[3])")
	private WebElement propertyType;
	
	@FindBy(xpath="(//button//span[text()='Continue'])")
	private WebElement continueButton;
	
	WebDriver driver;
	Actions actions; 
	JavascriptExecutor js;
	
	public projectDetailPage(WebDriver driver) {
		this.driver=driver;
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	
	public void giveProjectName () throws Exception  {
		String nameValue = readConfigProperty.readDataFromConfig("projectName");
		actions.moveToElement(jobTittle).click().sendKeys(nameValue).build().perform();
	}
	
	public void selectCategory() {
		actions.click(category).perform();
	}
	
	public void selectPlace () {
		actions.click(place).perform();
	}
	
	public void selectSize() throws Exception {
		String widthValue = readConfigProperty.readDataFromConfig("widthinft");
		String heightValue = readConfigProperty.readDataFromConfig("heightinft");
		actions.moveToElement(width).click().sendKeys(widthValue).build().perform();
		actions.moveToElement(height).click().sendKeys(heightValue).build().perform();
		actions.click(measureUnit).perform();		
	}	
	
	public void selectLocation() throws Exception {
		String locationValue = readConfigProperty.readDataFromConfig("Projectlocation");
	    actions.click(location).sendKeys(locationValue).build().perform();
//	    actions.click(location).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).build().perform();
	    
	    WebElement parentElement = driver.findElement(By.xpath("//div[@class='location-dropdown__container']"));
	    Thread.sleep(5000);
        List<WebElement> childElements = parentElement.findElements(By.xpath(".//*"));
	    
        if (childElements.size() >= 2) { 
            WebElement secondChildElement = childElements.get(1);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(secondChildElement));
            actions.click(secondChildElement).perform();
        } else {
            System.out.println("No suggestion found");
        }

//        for (WebElement child : childElements) {
//            System.out.println(child.getText());
//        }	
	}
	
	public void selectPropertyType() {
//        js.executeScript("arguments[0].scrollIntoView();", propertyType);
        actions.click(propertyType).perform();
	}
	
	public void clickOnContinue() {
        js.executeScript("arguments[0].scrollIntoView();", continueButton);
        actions.click(continueButton).perform();
	}
}
