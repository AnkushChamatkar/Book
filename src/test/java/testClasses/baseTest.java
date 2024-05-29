package testClasses;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import BaseMethod.readConfigProperty;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageClasses.budgetAndTimeline;
import pageClasses.contactInfoPage;
import pageClasses.designPage;
import pageClasses.projectDetailPage;
import pageClasses.test2;



public class baseTest {
	
	public static WebDriver driver;
	public projectDetailPage pdp;
	public designPage dp;
	public budgetAndTimeline bat;
	public contactInfoPage cp;
	
	public test2 t;
	
	
	@BeforeSuite
	public void initBrowser() throws Exception {
	    WebDriverManager.edgedriver().setup();

	    EdgeOptions options = new EdgeOptions();
	    options.addArguments("--disable-notifications");
//	    options.addArguments("--inprivate");
	    options.addArguments("--start-maximized");


	    driver = new EdgeDriver(options);
		String URL = readConfigProperty.readDataFromConfig("testsiteURL");
	    driver.get(URL);
	    WebElement moreInfoLink = driver.findElement(By.linkText("More information"));
        moreInfoLink.click();
        WebElement continueLink = driver.findElement(By.linkText("Continue to the unsafe site (not recommended)"));
        continueLink.click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}
	
	@AfterSuite
	public void teardown() {
		driver.quit();
	}
	

	@BeforeClass
	public void objectCreation() {
         pdp = new projectDetailPage(driver);
         dp = new designPage(driver);
         bat = new budgetAndTimeline(driver);
         cp = new contactInfoPage(driver);
         t = new test2(driver);
	}
	

}

