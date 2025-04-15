package com.testcases.GN;

import static utility.Report.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import core.Hook;


public class Login extends Hook
{
	@Test(priority = 1)
	public void testPageLoad()  
	{
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);
		Assert.assertTrue(pageTitle.contains("GiftingNetwork"), "Page did not load correctly!");
	}

	@Test(priority = 2)
	public void testUIElements() 
	{
		WebElement usernameField=username;
		WebElement passwordField = password;
		WebElement loginButton = Login;
		//Thread.sleep(4000);
		Assert.assertTrue(usernameField.isDisplayed(), "Username field is missing!");
		Assert.assertTrue(passwordField.isDisplayed(), "Password field is missing!");
		Assert.assertTrue(loginButton.isDisplayed(), "Login Button is missing!");
	}

	@Test(priority = 3)
	public void testLoginButton()
	{
		WebElement userButton=username;
		WebElement loginButton=Login;
		loginButton.click();
		String requiredAttribute = userButton.getAttribute("required");
		System.out.println(requiredAttribute);
		Assert.assertNotNull("Required attribute is missing!", requiredAttribute);
		Assert.assertTrue(requiredAttribute.isEmpty() || requiredAttribute.equals("true") || requiredAttribute.equals("required"));
		System.out.println("PASS: Please fill out this field.");
	}
	@Test(priority = 4)
	public void testRequiredUserfield()
	{
		WebElement usernameField=username;
		usernameField.click();
		Login.click();
		String requiredAttribute = usernameField.getAttribute("required");
		System.out.println(requiredAttribute);
		Assert.assertNotNull("Required attribute is missing!", requiredAttribute);
		Assert.assertTrue(requiredAttribute.isEmpty() || requiredAttribute.equals("true") || requiredAttribute.equals("required"));
		System.out.println("PASS: Please fill out this field.");
	}
	@Test(priority = 5)
	public void testPasswordbutton()
	{
		username.sendKeys("abc@gmail.com");
		WebElement passButton=password;
		Login.click();
		String requiredAttribute =passButton.getAttribute("required");
		System.out.println(requiredAttribute);
		Assert.assertNotNull("Required attribute is missing!", requiredAttribute);
		Assert.assertTrue(requiredAttribute.isEmpty() || requiredAttribute.equals("true") || requiredAttribute.equals("required"));
		System.out.println("PASS: Please fill out this field.");
		driver.navigate().refresh();

	}


	@Test(priority = 6)
	public void testInvalidLogin()  
	{
		username.clear();
		username.sendKeys("testuser@123");
		password.sendKeys("Password@123");
		Login.click();

		String errorMsg = err.getText();	
		System.out.println(errorMsg);
		test.log(Status.FAIL, MarkupHelper.createLabel("These credentials do not match our records." ,ExtentColor.RED));
		Assert.assertTrue(err.getText().contains("These credentials do not match our records."), "Error message missing!");

	}

	@Test(priority = 7)
	public void testValidLogin() 
	{
		username.clear();
		username.sendKeys(pr.getProperty("email"));
		password.sendKeys(pr.getProperty("emailpassword"));
		Login.click();
		//System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().contains("m/home"), "Login failed!");
	}

	@Test(priority = 8)
	public void testRedirectToLogin() 
	{
		driver.get("https://play.sageite.com/m/login");
		String currentUrl = driver.getCurrentUrl();
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(currentUrl.contains("/m/login"), "Unauthenticated users are not redirected to login!");
	}

	@Test(priority = 9)
	public void testForgotPasswordLink() 
	{
		driver.findElement(By.xpath("(//*[@class=\"font-small\"])[1]")).click();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("password/forgot?email=testuser@donor"), "Forgot Password page did not open!");
	}



}



