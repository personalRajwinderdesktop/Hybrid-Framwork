package test_layer;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basePackage.BaseClass;
import pomPackage.RegistrationPage;
import pomPackage.SearchPage;
import util.Basic_Utils;
import util.Excel_Reader;

public class RegsitrationPage_Tests extends BaseClass{
	 SearchPage searchpage;
	 RegistrationPage registration;
	 
	 public RegsitrationPage_Tests(){
			super();
			System.out.println("in RegsitrationPage_Tests");

	}
	 
	 @BeforeMethod
	 public void InitSetup() {
	 	Initiation();
	 	searchpage= new SearchPage();
	 	registration = new RegistrationPage();
	 	accept_cookies();
	 	registration =searchpage.RegistraionpageNavigation();
	 }
	 
	 @Test
	 public void urltest() {
		 String currenturl=driver.getCurrentUrl();
		 Assert.assertEquals(currenturl, prop.get("registrationurl"));
	 }
	 
	 @DataProvider
	 public Object[][] datapasser() {
	 	Object[][] data = Excel_Reader.readwholedata("Negative Cases First Name");
	 	return data;
	 }
	 @Test(dataProvider="datapasser")
	 public void ValidateFirstName_tests(String name, String msg) {
		 String actualmsg=registration.ValidateFirstName(name);
		 Assert.assertEquals(msg,actualmsg);
	 }
	 
	 @DataProvider
	 public Object[][] datapasser2() {
	 	Object[][] data = Excel_Reader.readwholedata("Negative Cases Last Name ");
	 	return data;
	 }
	 
	 @Test(dataProvider="datapasser2")
	 public void ValidateLastName_tests(String name, String msg) {
		 String actualmsg=registration.ValidateLastName(name);
		 Assert.assertEquals(msg,actualmsg);
	 }
	 
	 @DataProvider
	 public Object[][] datapasser3() {
	 	Object[][] data = Excel_Reader.readwholedata("Password Strength");
	 	return data;
	 }
	 
	 @Test(dataProvider="datapasser3")
	 public void ValidatePasswordStrength_tests(String pass, String msg) {
		 String actualmsg=registration.ValidatePasswordStrength(pass.trim());
		 msg=msg.trim();
		 assert msg.equals(actualmsg);
	 }
	 

	 
	 @AfterMethod
	 public void Teardown() {
	 	super.QuitDriver();
	 }
}
