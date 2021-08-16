package pomPackage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basePackage.BaseClass;


public class SearchPage extends BaseClass {
//locators
	@FindBy(xpath = "//*[@id='search_a']//ancestor::button//preceding-sibling::input")
	WebElement SearchInput;

	@FindBy(xpath = "//*[@id='search_a']//ancestor::button")
	WebElement SearchButton;

	@FindBy(xpath = "//*[@id='product-results']/div")
	public static List<WebElement> Productcount;

	@FindBy(xpath = "//*[@id='product-results']/div//p[@class='css-1p4va6y eudvd6x0']")
	List<WebElement> Productnames;

	@FindBy(xpath = "//*[@id='product-results']/div//div[@class='css-8frhg8 e175iya65']")
	List<WebElement> Productprice;

	@FindBy(xpath = "//*[@id='product-results']/div//button[@class='css-1baqlt1 edzik9p0' or @class='css-hvmdzz edzik9p0']")
	public static List<WebElement> addtocartbtn;

	@FindBy(xpath = "//span[@class='css-ijjviy ed60zyg11'][text()='2']")
	WebElement Page2Btn;

	@FindBy(xpath = "//*[@id='img_anch_CNTejueXtPICFd6BpgQdphEPaA']/img")
	WebElement add;

	@FindBy(xpath = "//span[text()='My account']")
	WebElement MyAccountBtn;

	@FindBy(xpath = "//a[text()='Join now']")
	WebElement JoinnowBtn;
	
	@FindBy(xpath = "//*[@id='atc-root']//div[@class='css-1kkxwdu elsmchb10']")
	WebElement cartaddedmsg;
	
	@FindBy(xpath = "//*[@id='atc-root']//button[@data-automation='checkout']")
	WebElement CheckoutBtn;

	// constructor
	public SearchPage() {
		PageFactory.initElements(driver, this);
	}

	// page actions

	public void SearchKeyword(String str) {

		SearchInput.sendKeys(str);
		SearchButton.click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		super.ScrollToView(Page2Btn);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<String> Product_names(String str) {
		List<String> names = new ArrayList<String>();
		SearchKeyword(str);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		for (int i = 0; i < Productnames.size(); i++) {
			names.add(Productnames.get(i).getText());
		}

		return names;

	}

	public List<String> Page2_Product_names(String str) {
		List<String> names = new ArrayList<String>();
		SearchKeyword(str);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		for (int i = 0; i < Productnames.size(); i++) {
			names.add(Productnames.get(i).getText());
		}

		Page2Btn.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.ScrollToView(Page2Btn);

		for (int i = 0; i < Productnames.size(); i++) {
			names.add(Productnames.get(i).getText());
		}
		return names;

	}

	public int Product_Counts(String str) {
		SearchKeyword(str);
		return Productcount.size();

	}

	public List<String> ProductPrice(String str) {
		List<String> price = new ArrayList<String>();
		SearchKeyword(str);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		for (int i = 0; i < Productprice.size(); i++) {
			price.add(Productprice.get(i).getText());
		}
		System.out.println(Productprice.size());
		return price;
	}

	public void adding_to_cart(String Keyword) {
		SearchKeyword(Keyword);
		if (addtocartbtn.get(2).getText().equals("Add to cart")) {
			addtocartbtn.get(2).click();
		}
		
	
		
	}
	
	public String validateaddtocartmsg(String Keyword) {
		adding_to_cart(Keyword);
		String str =cartaddedmsg.getText();
		return str;
	}
	
	
	public  ShoppingCart NavigateShoppingcartpage(String str) {
		adding_to_cart(str);
		CheckoutBtn.click();
		return new  ShoppingCart();
	}

	public RegistrationPage RegistraionpageNavigation() {
		super.HoverToElement(MyAccountBtn);
		JoinnowBtn.click();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		return new RegistrationPage();
	}

	
}