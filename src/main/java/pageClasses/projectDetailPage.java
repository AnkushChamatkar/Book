package pageClasses;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@FindBy(xpath="(//*[@id='artist_first_name_sp_py'])")
	private WebElement name;
	@FindBy(xpath="(//*[@id='artist_location_sp_py'])")
	private WebElement location;
	
	@FindBy(xpath="(//*[@class='artist__info-wrap'])")
	private List<WebElement> items;

//	@FindBy(xpath="(//*[@id='category_id']//span[@class='ant-radio-button ant-radio-button-checked'])")
//	private WebElement category;
//	
//	@FindBy (xpath="//*[text()='Window']")
//	private WebElement place;
//	
//	@FindBy (xpath="(//*[@class='ant-input-number-input-wrap'])[1]")
//	private WebElement width;
//	
//	@FindBy (xpath="(//*[@class='ant-input-number-input-wrap'])[2]")
//	private WebElement height;
//	
//	@FindBy (xpath="//*[text()='ft.']")
//	private WebElement measureUnit;
//
//	@FindBy(xpath="(//*[@class='ant-input css-6rzz9k'])")
//	private WebElement location;
//	
//	@FindBy(xpath="((//*[@id='property_type']//label)[3])")
//	private WebElement propertyType;
//	
//	@FindBy(xpath="(//button//span[text()='Continue'])")
//	private WebElement continueButton;
//	
	WebDriver driver;
	Actions actions; 
	JavascriptExecutor js;
	
	public projectDetailPage(WebDriver driver) {
		this.driver=driver;
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	
	public void test() {
	    List<Map<String, Object>> dataList = new ArrayList<>();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    for (WebElement item : items) {
	        wait.until(ExpectedConditions.visibilityOf(item)); // Wait for the element to be visible
	        js.executeScript("arguments[0].scrollIntoView();", item);

	        // Retry the action in case of StaleElementReferenceException
	        try {
	            String name = item.findElement(By.id("artist_first_name_sp_py")).getText();
	            String location = item.findElement(By.id("artist_location_sp_py")).getText();
	            String priceText = item.findElement(By.xpath(".//p[contains(text(), 'Pricing starts at')]")).getText();
	            String price = priceText.replaceAll("[^0-9]", "");
	            Map<String, Object> data = new HashMap<>();
	            data.put("name", name);
	            data.put("location", location);
	            data.put("price", Integer.parseInt(price));

	            dataList.add(data);
	        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
	            // Element became stale, retry the action
	            System.out.println("Stale element reference, retrying...");
	            continue; // Continue with the next iteration of the loop
	        }
	    }
	    System.out.println(dataList);
	}

//	
//	public void giveProjectName () throws Exception  {
//		String nameValue = readConfigProperty.readDataFromConfig("projectName");
//		actions.moveToElement(jobTittle).click().sendKeys(nameValue).build().perform();
//	}
//	
//	public void selectCategory() {
//		actions.click(category).perform();
//	}
//	
//	public void selectPlace () {
//		actions.click(place).perform();
//	}
//	
//	public void selectSize() throws Exception {
//		String widthValue = readConfigProperty.readDataFromConfig("widthinft");
//		String heightValue = readConfigProperty.readDataFromConfig("heightinft");
//		actions.moveToElement(width).click().sendKeys(widthValue).build().perform();
//		actions.moveToElement(height).click().sendKeys(heightValue).build().perform();
//		actions.click(measureUnit).perform();		
//	}	
//	
//	public void selectLocation() throws Exception {
//		String locationValue = readConfigProperty.readDataFromConfig("Projectlocation");
//	    actions.click(location).sendKeys(locationValue).build().perform();
////	    actions.click(location).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).build().perform();
//	    
//	    WebElement parentElement = driver.findElement(By.xpath("//div[@class='location-dropdown__container']"));
//	    Thread.sleep(5000);
//        List<WebElement> childElements = parentElement.findElements(By.xpath(".//*"));
//	    
//        if (childElements.size() >= 2) { 
//            WebElement secondChildElement = childElements.get(1);
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            wait.until(ExpectedConditions.visibilityOf(secondChildElement));
//            actions.click(secondChildElement).perform();
//        } else {
//            System.out.println("No suggestion found");
//        }
//
////        for (WebElement child : childElements) {
////            System.out.println(child.getText());
////        }	
//	}
//	
//	public void selectPropertyType() {
////        js.executeScript("arguments[0].scrollIntoView();", propertyType);
//        actions.click(propertyType).perform();
//	}
//	
//	public void clickOnContinue() {
//        js.executeScript("arguments[0].scrollIntoView();", continueButton);
//        actions.click(continueButton).perform();
//	}
}
