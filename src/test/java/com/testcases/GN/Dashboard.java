package com.testcases.GN;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import core.Hook;
import utility.ScreenShot.*;

public class Dashboard extends Hook
{
	@Test(priority=1)
	public void dashboardload() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		username.sendKeys(pr.getProperty("email"));
		password.sendKeys(pr.getProperty("emailpassword"));
		Login.click();
		String expectedTitle = "GiftingNetwork"; 
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Dashboard page title mismatch!");
		// Verify header
		WebElement header =dashHeader;
		System.out.println(header.getText());
		Assert.assertTrue(header.isDisplayed(), "Dashboard header is not visible!");
	}

	@Test(priority=2)
	public void verifyUserProfileDropdown() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement userProfile =profilHeader;
		userProfile.click();
		WebElement profileOption =profileHeaderOption;
		System.out.println(profileOption.getText());
		Assert.assertTrue(profileOption.isDisplayed(), "Profile option not visible");
		driver.navigate().back();

	}

	@Test(priority=3)
	public void navItems() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Wait for page to load 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (js.executeScript("return document.readyState").equals("complete")) {
			System.out.println("Page loaded successfully.");
		} else {
			System.out.println("Page still loading...");
		}

		String a="https://play.sageite.com/m/";
		String[] b= {
				"contribute",
				"make-a-grant/create",
				"transactions",
				"fund-advisors",
				"my-documents",
				"my-cart",
				"catalog",
				"catalog/orgs/matches",
				"catalog/programs/matches",
				"donor/pool-performance?id=4001",
				"profile",
				"profile/interests",
				"donor/investments",
				"password/change",
		};
		String[] menulinks = new String[b.length];
		for (int i = 0; i < b.length; i++) {
			menulinks[i] = a + b[i];
		}
		for (String url : menulinks) {
			System.out.println(url);
		}
		for (String menuItem : menulinks) {
			try {
				String xpath = "//a[@href='" + menuItem + "']";
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement menuElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				Assert.assertTrue(menuElement.isDisplayed(), menuItem + " is not displayed in sidebar");
				System.out.println("Clicking on menu item: " + menuItem);
				menuElement.click();
				// Wait for page to load
				wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

				// Go back and wait for sidebar to reappear
				driver.navigate().back();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			} catch (org.openqa.selenium.TimeoutException e) {
				Assert.fail(menuItem + " was not found in the sidebar");
			} catch (Exception e) {
				System.out.println("Error with menu item: " + menuItem);
				e.printStackTrace();
			}
		}  
	}

	@Test(priority=4)
	public void fund() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement fundsSection = fundSection;
		Assert.assertTrue(fundsSection.isDisplayed(), "Funds section is not visible!");

		List<WebElement> funds = fundList;
		for(int i=0;i<funds.size();i++)
		{
			System.out.println(funds.get(i).getText());
		}

		Assert.assertTrue(funds.size() > 0, "No funds found!");
	}

	@Test(priority=5)
	public void pending() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement pendingSection =pendingGrant;
		Assert.assertTrue(pendingSection.isDisplayed(), "Funds section is not visible!");
		List<WebElement> pendingList = pendinglist;
		for(int i=0;i<pendingList.size();i++)
		{
			System.out.println(pendingList.get(i).getText());
		}
		Assert.assertTrue(pendingList.size() > 0, "No pending grants found!");
	}

	@Test(priority=6)
	public void article() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement arti=article;
		System.out.println(arti.getText());
		List<WebElement>artiNo=articleList;
		for(int i=0;i<artiNo.size();i++)
		{
			System.out.println(artiNo.get(i).getText());
		}

		for(int i=0;i<artiNo.size();i++)
		{
			artiNo.get(i).click();
			driver.navigate().back();
		}

		Assert.assertTrue(artiNo.size() > 0, "No article present found!");
	}

	@Test(priority=7)
	public void org() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement organization =organizationtitle;
		Assert.assertTrue(organization.isDisplayed(), "Organization section not visible");
		System.out.println("Organization section: " + organization.getText());
		organizationSearch.sendKeys("a");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tt-selectable")));
		boolean orgClicked = false;
		// Try up to 3 times in case of stale element
		for (int attempts = 0; attempts < 3; attempts++) {
			try {
				List<WebElement> orgNameSuggestions =organizationlist;
				for (WebElement suggestion : orgNameSuggestions) {
					String text = suggestion.getText();
					System.out.println("Suggestion: " + text);

					if (text.contains("AMERICAN")) {
						suggestion.click();
						orgClicked = true;
						break;
					}
				}
				break;
			} catch (StaleElementReferenceException e) {
				System.out.println("StaleElementReferenceException encountered. Retrying...");
				Thread.sleep(1000);
			}
		}

		Assert.assertTrue(orgClicked, "No matching organization found or clicked.");
		driver.navigate().back();
	} 

	@Test(priority = 8)
	public void verifyLogoutFunctionality() throws Exception 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement logoutIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector(".nav-icon.fas.fa-sign-out-alt")));
		logoutIcon.click();

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement confirmButton = wait1.until(ExpectedConditions.elementToBeClickable(logoutConfm));
		confirmButton.click();

		String expectedURL = "https://play.sageite.com/m/login";
		String actualURL = driver.getCurrentUrl();

		Assert.assertEquals(actualURL, expectedURL, "Logout did not redirect to login page");
	}
}
