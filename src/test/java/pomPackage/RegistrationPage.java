package pomPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basePackage.BaseClass;
import util.Basic_Utils;

public class RegistrationPage extends BaseClass {
	//locators
	@FindBy(id = "firstName")
	WebElement FirstName;
	
	@FindBy(xpath = "//div[@data-automation='first-name-section']//span")
	WebElement msgfirstname;
	
	@FindBy(id = "lastName")
	WebElement LastName;
	
	@FindBy(xpath = "//div[@data-automation='last-name-section']//span")
	WebElement msglastname;
	
	
	@FindBy(id = "email")
	WebElement Email;
	
	@FindBy(xpath = "//div[@data-automation='email-section']//span")
	WebElement msg_email;
	
	@FindBy(id = "password")
	WebElement Password;
	
	@FindBy(xpath = "//div[@class='password-strength-label']//span")
	WebElement strengthmsgpassword;
	
	@FindBy(xpath = "//div[@data-automation='password-section']//span[@class='css-1lliris erl5ymg4']")
	WebElement msgpassword;
	
	@FindBy(xpath = "//button[@class='css-sz4mv8 erl5ymg1']")
	WebElement eyepassword;
	
	@FindBy(id = "TAndC")
	WebElement Createaccountcheckbox;
	
	@FindBy(xpath = "//button[text()='Create account']")
	WebElement CreateAccountBtn ;
	
	

	// constructor
	public RegistrationPage() {
		PageFactory.initElements(driver, this);
	}

	// page actions
	
	
	
	public String ValidateFirstName(String str) {
		FirstName.sendKeys(str+Keys.ENTER);
		return msgfirstname.getText();
	}
	
	public String ValidateLastName(String str) {
		LastName.sendKeys(str+Keys.ENTER);
		return msglastname.getText();
	}
	
	public String ValidatePasswordStrength(String str) {
		Password.sendKeys(str+Keys.ENTER);
	    String[] stre= strengthmsgpassword.getText().split(":");
	    return stre[1].trim();
	}
	
	
	
}
