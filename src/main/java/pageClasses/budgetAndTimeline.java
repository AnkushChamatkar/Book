package pageClasses;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseMethod.readConfigProperty;

public class budgetAndTimeline {
	@FindBy(xpath="(//*[@id='budget'])")
	private WebElement budget;
	
	@FindBy(xpath="((//*[@id='deadline_type']//div[@class='ant-col ant-col-xs-12 ant-col-sm-12 ant-col-md-8 css-6rzz9k'])[1])")
	private WebElement ondate;
	
	@FindBy(xpath="((//*[@id='deadline_type']//div[@class='ant-col ant-col-xs-12 ant-col-sm-12 ant-col-md-8 css-6rzz9k'])[2])")
	private WebElement beforedate;
	
	@FindBy(xpath="(//button//span[text()='Continue'])")
	private WebElement continueButton;
	
	
	WebDriver driver;
	Actions actions; 
	JavascriptExecutor js;
	
	public budgetAndTimeline(WebDriver driver) {
		this.driver=driver;
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setBudget () throws Exception {
		String priceValue = readConfigProperty.readDataFromConfig("Price");
		actions.click(budget).sendKeys(priceValue).build().perform();
	}
	
	public void setDeadLine() {
		 actions.click(ondate).perform();
		
		 LocalDate currentDate = LocalDate.now();
	        
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	     String formattedDate = currentDate.format(formatter);
	     System.out.println(formattedDate);
	     WebElement todaysDate = driver.findElement(By.xpath("//*[@title='"+formattedDate+"']"));
	     actions.click(todaysDate).perform();

//	     actions.click(beforedate).perform();
//	     LocalDate dateAfterTwoDays = currentDate.plusDays(2);
//	     String formattedDate2 = dateAfterTwoDays.format(formatter);
//	     System.out.println(formattedDate2);
//	     WebElement afterDate = driver.findElement(By.xpath("//*[@title='"+formattedDate2+"']"));
//	     actions.click(afterDate).perform();

	}
	
	public void clickOnContinue() {
        js.executeScript("arguments[0].scrollIntoView();", continueButton);
        actions.click(continueButton).perform();
	}
	
}
