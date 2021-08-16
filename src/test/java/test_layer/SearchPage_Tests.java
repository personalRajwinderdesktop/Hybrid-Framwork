package test_layer;


import pomPackage.SearchPage;
import util.BrokenLinks;
import util.Excel_Reader;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basePackage.BaseClass;

public class SearchPage_Tests extends BaseClass{
     SearchPage searchpage;
	
	public SearchPage_Tests(){
		super();
		System.out.println("in SearchPage_Tests");

}



@BeforeMethod
public void InitSetup() {
	Initiation();
	searchpage= new SearchPage();
}

@Test
public void validateaddtocartmsgtest() {
	String str ="furniture";
	String msg =searchpage.validateaddtocartmsg(str);
Assert.assertEquals(msg,"You have successfully updated your cart.");
}

//this data provider will provide the keywords into the search input
@DataProvider
public Object[] search_input() {
	Object[] keyword = Excel_Reader.Row_Values("Keywords", 0);
	return keyword;
}

@Test(dataProvider="search_input")
public void Validate_Product_Price_On_search(String keyword){
	List<String> price= searchpage.ProductPrice(keyword);
   assert(price.size()==searchpage.Productcount.size());
}

//this test will validate the name is presetn foreach product

@Test(dataProvider="search_input")
public void Validate_Product_name_On_search(String keyword){
	List<String> name= searchpage.Product_names(keyword);
   assert(name.size()==searchpage.Productcount.size());
}


@Test(dataProvider="search_input")
public void Validate_Product_Count_is_60(String keyword) {
	assert(searchpage.Product_Counts(keyword)<=60);
}



//this test will validate that product has add to cart or choose options button
@Test(dataProvider="search_input")
public void validate_add_to_cart_for_each_product(String keyword) {
	searchpage.SearchKeyword(keyword);
	assert(searchpage.addtocartbtn.size()==searchpage.Productcount.size());
	}

// checking pagination on page 1
@Test
public void Pagination_Page1_tests() {
	String keyword="furniture";
	HashSet<String> uniquenames = new HashSet<String>();
	List<String> names= new ArrayList<String>();
	names =searchpage.Product_names(keyword);
	for(int i = 0;i<names.size();i++) {
		uniquenames.add(names.get(i));
	}
	System.out.println(names.size()+"   "+uniquenames.size());	
	Assert.assertEquals(names.size(),uniquenames.size());
}


@Test
public void Pagination_Page2_tests() {
	String keyword="furniture";
	HashSet<String> uniquenames = new HashSet<String>();
	List<String> names= new ArrayList<String>();
	names =searchpage.Page2_Product_names(keyword);
	for(int i = 0;i<names.size();i++) {
		if(uniquenames.add(names.get(i))==false) {
		System.out.println(names.get(i));	
		}
	}
	System.out.println(names.size()+"   "+uniquenames.size());	
	Assert.assertEquals(names.size(),uniquenames.size());
}

@AfterMethod
public void Teardown() {
	super.QuitDriver();
}

}
