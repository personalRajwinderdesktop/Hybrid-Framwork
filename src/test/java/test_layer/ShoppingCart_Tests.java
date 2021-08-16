package test_layer;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import basePackage.BaseClass;
import pomPackage.RegistrationPage;
import pomPackage.SearchPage;
import pomPackage.ShoppingCart;

public class ShoppingCart_Tests extends BaseClass {
	SearchPage searchpage;
	ShoppingCart shoppingcart;

	public ShoppingCart_Tests() {
		super();
		System.out.println("in ShoppingCart_Tests");

	}

	@BeforeMethod
	public void InitSetup() {
		Initiation();
		searchpage = new SearchPage();
		shoppingcart = new ShoppingCart();
		accept_cookies();

	}

	@Test(groups = "sanity")
	public void urltest() {
		shoppingcart = searchpage.NavigateShoppingcartpage("furniture");
		String currenturl = driver.getCurrentUrl();
		Assert.assertEquals(currenturl, prop.get("carturl"));
	}

	@AfterMethod
	public void Teardown() {
		super.QuitDriver();
	}

}
