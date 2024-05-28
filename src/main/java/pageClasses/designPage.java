package pageClasses;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseMethod.readConfigProperty;

public class designPage {
	
	@FindBy(xpath="(//*[text()='No idea'])")
	private WebElement designPerocess;
	
	@FindBy(xpath="(//*[@id='job_description'])")
	private WebElement description;
	
	@FindBy(xpath="(//button[@class='ant-btn css-6rzz9k ant-btn-default custom-btn__no-style']//span)")
	private WebElement moreButton;
	
	@FindBy(xpath="(//*[@id='keywords']//span[text()='mural'])")
	private WebElement tag;
	
	@FindBy(xpath="(//button[@title='Attach file'])")
	private WebElement attachButton;
	
	@FindBy(xpath="(//button//span[text()='Continue'])")
	private WebElement continueButton;
	
	WebDriver driver;
	Actions actions; 
	JavascriptExecutor js;
	
	public designPage(WebDriver driver) {
		this.driver=driver;
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectDesignProcess () {
		actions.click(designPerocess).perform();
	}
	
	public void provideDescription() throws IOException {
		String descriptionValue = readConfigProperty.readDataFromConfig("ProjectDescription");
		actions.click(description).sendKeys(descriptionValue).build().perform();
	}
	
	public void selectTag() {
		actions.click(moreButton).perform();
		
		actions.click(tag).perform();
	}
	
	public void attactFile() {
        js.executeScript("arguments[0].scrollIntoView();", attachButton);
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\Resorses\\file-sample.pdf";
		actions.sendKeys(attachButton, filePath);
	}
	
	public void clickOnContinue() {
        js.executeScript("arguments[0].scrollIntoView();", continueButton);
        actions.click(continueButton).perform();
	}
	
	

}
