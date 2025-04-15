package com.testcases.Mercy;

import org.testng.Assert;
import org.testng.annotations.Test;

import static utility.Report.test;
import static utility.Report.*;

import core.Page;


public class URLMercy extends Page
{

	@Test(priority = 1)
	public void testURLAccessibility() 
	{
		//driver.get("https://qa-mercy.sageite.com/m/login");
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, "https://qa-mercy.sageite.com/m/login", "URL is not accessible!");
	}

	@Test(priority = 2)
	public void testRedirection() 
	{
		//driver.get("https://qa-mercy.sageite.com/m/login");
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.startsWith("https://"), "Redirection to HTTPS failed!");
	}

	@Test(priority = 3)
	public void test404Page() 
	{
		//driver.get("https://qa-mercy.sageite.com");
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);
		Assert.assertTrue(pageTitle.contains("404") || pageTitle.contains("Not Found"), "404 Page is not handled correctly!");


		/*  int statusCode = driver.getTitle().contains("404") ? 404 : 200;
        Assert.assertEquals(statusCode, 404, "404 Page is not handled correctly!");
        boolean isErrorDisplayed = driver.getPageSource().contains("Page Not Found") || 
                driver.findElement(By.xpath("//*[text()='404']")).isDisplayed();
        Assert.assertTrue(isErrorDisplayed, "404 Page is not handled correctly!");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("error-page"), "404 Page does not redirect correctly!");
		 */




	}

	@Test(priority = 4)
	public void testLoginRedirection() 
	{
		//driver.get("https://qa-mercy.sageite.com/m/login"); // Restricted page
		String currentURL = driver.getCurrentUrl();
		System.out.println(currentURL);
		Assert.assertTrue(currentURL.contains("dashboard"), "Unauthenticated users are not redirected to login!");
	}


}

