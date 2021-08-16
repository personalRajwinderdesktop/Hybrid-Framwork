package basePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
public static WebDriver driver= null;
public static Properties prop = new Properties();
public static JavascriptExecutor js;
public static Actions action;

public BaseClass(){
	try {
	FileInputStream file = new FileInputStream("C:\\Users\\rajwi\\eclipse-workspace\\Walmart\\src\\test\\java\\environment\\config.properties");
	prop.load(file);
	}catch(FileNotFoundException e) {
		e.printStackTrace();
	}catch(IOException a) {
		a.printStackTrace();
	}
}


public static void Initiation() {
	
	String browser= prop.getProperty("browser");
	if (driver == null) {
	if(browser.equals("Chrome")) {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		driver = new ChromeDriver();
	}else if(browser.equals("Firefox")) {
		System.setProperty("webdriver.gecko.driver","geckodriver.exe");
		driver = new FirefoxDriver();
}
	}
	driver.get(prop.getProperty("url"));
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	driver.manage().deleteAllCookies();
	
	 js = ((JavascriptExecutor) driver);
	 action = new Actions(driver);
}
   
public static void scrolling(int x, int y) {
	js.executeScript("window.scrollTo(" + x + "," + y + ")");

}



	public static void ScrollToView(WebElement ele)  {
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		//(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(ele));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//action actions

	
	public static void HoverToElement(WebElement ele) {
		action.moveToElement(ele).build().perform();
	}
	
	public static void HoverToElement(WebElement ele1,WebElement ele2) {
		action.moveToElement(ele1).moveToElement(ele2).build().perform();
	}
	
	public static void HoverToElement(WebElement ele1,WebElement ele2,WebElement ele3) {
		action.moveToElement(ele1).moveToElement(ele2).moveToElement(ele3).build().perform();
	}
	public static void accept_cookies() {
		driver.findElement(By.xpath("//*[@id='accept-privacy-policies']")).click();
	}


public static void QuitDriver() {
	driver.quit();
	System.out.println("quitting the driver");
	driver = null;
}

public static void CloseDriver() {
	driver.close();
	System.out.println("Closing the driver");
	driver = null;
}




























}
