package util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import basePackage.BaseClass;



public class BrokenLinks extends BaseClass {

	public static int brokenlinks() throws IOException {
		
		LinkedList<WebElement> links = new LinkedList<WebElement>();
		links.addAll(driver.findElements(By.tagName("a")));
		//number of http links
		System.out.println(links.size());
		links.addAll(driver.findElements(By.tagName("img")));
		//number of images
		System.out.println(links.size());
		int broken =0;
		for(int i=0;i<links.size();i++) {
			//establishing connection to hhtp links
		HttpURLConnection connect = (HttpURLConnection) new URL(links.get(i).getAttribute("href")).openConnection();
		connect.connect();
		int code= connect.getResponseCode();
		if(code!=200) {
			broken++;
		}
		System.out.println("ResposeCode is "+ code +" and message is "+connect.getResponseMessage());
		connect.disconnect();
		}
		
		System.out.println("broken links are"+ broken);
		return broken;

	}

}
