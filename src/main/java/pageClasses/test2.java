package pageClasses;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.val;

public class test2 {
	@FindBy(xpath="(//*[@id='artist_first_name_sp_py'])")
	private List<WebElement> artistname;
	@FindBy(xpath="(//*[@id='artist_location_sp_py'])")
	private List<WebElement> artistlocation;
	
	@FindBy(xpath="(//*[@class='artist__info-wrap'])")
	private List<WebElement> items;
	
	@FindBy(xpath="(//p[contains(text(), 'Pricing starts at')])")
	private List<WebElement> priceextract;
	
	WebDriver driver;
	Actions actions; 
	JavascriptExecutor js;
	
	
	public test2 (WebDriver driver) {
		this.driver=driver;
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	public void test() throws Exception  {
		
		
//		List<Map<String, String>> artistDetails = new ArrayList<>();
//
//	    for (WebElement item : items) {
//	        Map<String, String> details = new HashMap<>();
//
//	        // Extract artist name
//	        String artistName = item.findElement(By.xpath(".//*[@id='artist_first_name_sp_py']")).getText();
//	        details.put("name", artistName);
//
//	        // Extract artist location
//	        String artistLocation = item.findElement(By.xpath(".//*[@id='artist_location_sp_py']")).getText();
//	        details.put("location", artistLocation);
//
//	        // Extract artist price
//	        String priceText = item.findElement(By.xpath(".//p[contains(text(), 'Pricing starts at')]")).getText();
//	        String price = priceText.replaceAll("[^0-9]", "");
//	        details.put("price", price);
//
//	        artistDetails.add(details);
//	    }
//
//	    System.out.println(artistDetails);
		List<String> names = new ArrayList<>();
	    List<String> locations = new ArrayList<>();
	    List<String> prices = new ArrayList<>();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		
		for(WebElement value:artistname) {
		    wait.until(ExpectedConditions.visibilityOfAllElements(value));
		    
		    String ArtistNameValue = value.getText();

			System.out.println(ArtistNameValue);
			names.add(ArtistNameValue);
		}
		for(WebElement locationvalue:artistlocation) {
		    wait.until(ExpectedConditions.visibilityOfAllElements(locationvalue));
		    
		    String Artistlocation = locationvalue.getText();

			System.out.println(Artistlocation);
			locations.add(Artistlocation);
		}
		
		for(WebElement pricevalue:priceextract) {
			
		    wait.until(ExpectedConditions.visibilityOfAllElements(pricevalue));
		    
		    String Artistpricetext = pricevalue.getText();
		    System.out.println("Original price text: " + Artistpricetext);

		    
		    String price = Artistpricetext.replaceAll("[^0-9]", "");
            System.out.println(price);
            prices.add(price);
		}
		List<Map<String, String>> artistDetails = new ArrayList<>();
	    for (int i = 0; i < names.size(); i++) {
	        Map<String, String> details = new HashMap<>();
	        details.put("name", names.get(i));
	        details.put("location", locations.get(i));
	        details.put("price", prices.get(i));
	        artistDetails.add(details);
	    }
	    System.out.println(artistDetails);

//		
//	    List<Map<String, Object>> dataList = new ArrayList<>();
//
//	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//	    wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
//
//	    // Wait for the items to be present
//	    wait.until(ExpectedConditions.visibilityOfAllElements(items));
//
//	    
//
//	    for (WebElement item : items) {
////            item = wait.until(ExpectedConditions.visibilityOf(item));
//	    	
//            System.out.println("1");
//
//
////            wait.until(ExpectedConditions.visibilityOf(item));
////            System.out.println("2");
////
////	        
////	        js.executeScript("arguments[0].scrollIntoView();", item);
////	        System.out.println("3");
//
//	      
//
//	        try {
//	            String artistname = item.findElement(By.id("artist_first_name_sp_py")).getText();
//	            System.out.println("name text");
//
//	            String artistlocation = item.findElement(By.id("artist_location_sp_py")).getText();
//	            System.out.println("loc text");
//
//	            String priceText = item.findElement(By.xpath(".//p[contains(text(), 'Pricing starts at')]")).getText();
//	            String price = priceText.replaceAll("[^0-9]", "");
//	            System.out.println(price);
//	            Map<String, Object> data = new HashMap<>();
//	            data.put("name", artistname);
//	            data.put("location", artistlocation);
//	            data.put("price", Integer.parseInt(price));
//
//	            dataList.add(data);
//	        } catch (StaleElementReferenceException e) {
//	            
//	            System.out.println("Stale element reference, retrying...");
//	            continue;
//	        }
//	    }
//	    System.out.println(dataList);
	}
    
}
