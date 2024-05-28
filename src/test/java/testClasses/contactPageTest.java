package testClasses;

import org.testng.annotations.Test;

import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;
import utility.listnerClass;

public class contactPageTest extends listnerClass {
	
	@Test (priority = 1)
	public void representation () {
		cp.representing();
	}
	@Test(priority = 2)
	public void countrySelection () {
		cp.chooseCountry();
	}
	@Test (priority = 3)
	public void giveMobNo () throws Exception {
		cp.insertMobileNo();
	}
	
	@Test (priority = 4)
	public void hereFrom () {
		cp.hereUsFrom();
	}
	
	@Test (priority = 5)
	public void givePersonalCredentials () throws Exception {
		cp.insertPersonalDetails();
	}
	
	@Test (priority = 6)
	public void termsAgrement () {
		cp.termsAndPolicy();
	}
	
	@Test (priority = 7)
	public void fillingcaptcha () {
		cp.captcha();
	}
	
//	@Test (priority = 8)
//	public void saveAndSubmitButton () {
//		cp.saveAndSubmit();
//	}
}
