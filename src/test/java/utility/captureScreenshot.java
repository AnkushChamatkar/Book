package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class captureScreenshot {
	
	public static String getScreenShot(WebDriver driver, String filename) throws IOException {
		
		TakesScreenshot src = (TakesScreenshot)driver;
		File source = src.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"\\screenshot\\"+filename+".png";
		File destination = new File(path);
		FileUtils.copyFile(source,  destination);
	
		return path;
	}
}
