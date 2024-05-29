package pageClasses;

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

public class test2 {
	@FindBy(xpath="(//*[@id='artist_first_name_sp_py'])")
	private WebElement name;
	@FindBy(xpath="(//*[@id='artist_location_sp_py'])")
	private WebElement location;
	
	@FindBy(xpath="(//*[@class='artist__info-wrap'])")
	private List<WebElement> items;
	
	WebDriver driver;
	Actions actions; 
	JavascriptExecutor js;
	
	public test2 (WebDriver driver) {
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
	        
	            String artistname = name.getText();
	            String artistlocation = location.getText();
	            String priceText = item.findElement(By.xpath(".//p[contains(text(), 'Pricing starts at')]")).getText();
	            String price = priceText.replaceAll("[^0-9]", "");
	            Map<String, Object> data = new HashMap<>();
	            data.put("name", artistname);
	            data.put("location", artistlocation);
	            data.put("price", Integer.parseInt(price));

	            dataList.add(data);
	        
	    }
	    System.out.println(dataList);
	}
    
}
