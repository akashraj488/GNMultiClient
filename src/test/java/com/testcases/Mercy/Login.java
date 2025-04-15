package com.testcases.Mercy;

import static utility.Report.test;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.github.javafaker.File;

import core.Hook;

public class Login extends Hook
{
	@Test(priority= 1)
	public void mercyLogo()
	{
		clientLogo.click();
	}

	@Test(priority=2)
	public void gnLogo()
	{
		GNlogo.click();
	}

	@Test(priority=3)
	public void footerLogo()
	{
		footerclientlogo.click();
	}

	@Test(priority = 4)
	public void testPageLoad()  
	{
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);
		Assert.assertTrue(pageTitle.contains("Mercy Health"), "Page did not load correctly!");
	}

	@Test(priority = 5)
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

	@Test(priority = 6)
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

	@Test(priority = 7)
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

	@Test(priority = 8)
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


	@Test(priority = 9)
	public void testInvalidLogin()  
	{
		//driver.get("https://qa-mercy.sageite.com/m/login");
		username.clear();
		username.sendKeys("testuser@123");
		password.sendKeys("Password@123");
		Login.click();

		String errorMsg = err.getText();	
		System.out.println(errorMsg);
		test.log(Status.FAIL, MarkupHelper.createLabel("These credentials do not match our records." ,ExtentColor.RED));
		Assert.assertTrue(err.getText().contains("These credentials do not match our records."), "Error message missing!");

	}

	@Test(priority = 10)
	public void testValidLogin() 
	{
		username.clear();
		username.sendKeys(pr.getProperty("mercyemail"));
		password.sendKeys(pr.getProperty("mercyemailpassword"));
		Login.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("/m/home"));

		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentUrl);
		Assert.assertTrue(currentUrl.contains("/m/home"), "Login failed! URL: " + currentUrl);

	}

	@Test(priority = 11)
	public void testRedirectToLogin() 
	{
		driver.get("https://qa-mercy.sageite.com/m/login");
		String currentUrl = driver.getCurrentUrl();
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(currentUrl.contains("/m/home"), "Unauthenticated users are not redirected to login!");
	}

	@Test(priority=12)
	public void logout() throws TimeoutException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement logoutIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector(".nav-icon.fas.fa-sign-out-alt")));
		logoutIcon.click();

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement confirmButton = wait1.until(ExpectedConditions.elementToBeClickable(logoutConfm));
		confirmButton.click();

		String expectedURL = "https://qa-mercy.sageite.com/m/login";
		String actualURL = driver.getCurrentUrl();

		Assert.assertEquals(actualURL, expectedURL, "Logout did not redirect to login page");
	    }

		

	

	@Test(priority = 13)
	public void testForgotPasswordLink() 
	{
		driver.findElement(By.xpath("(//*[@class=\"font-small\"])[1]")).click();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("password/forgot?email=testuser@donor"), "Forgot Password page did not open!");
	}

}
