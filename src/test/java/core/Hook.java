package core;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Hook extends Page
{
	//mapping of login xpath

	//mapping of login xpath
	@FindBy(xpath=externalxpath.xpath.clientLogo)
	public static WebElement clientLogo;

	@FindBy(xpath=externalxpath.xpath.GNlogo)
	public static WebElement GNlogo;

	@FindBy(xpath=externalxpath.xpath.footerclientLogo)
	public static WebElement footerclientlogo;

	@FindBy(xpath=externalxpath.xpath.username)
	public static WebElement username;

	@FindBy(xpath=externalxpath.xpath.password)
	public static WebElement password;

	@FindBy(xpath=externalxpath.xpath.err)
	public static WebElement err;

	@FindBy(xpath=externalxpath.xpath.Login)
	public static WebElement Login;


	//mapping of header xpath
	@FindBy(xpath=externalxpath.xpath.dashHeader)
	public static WebElement dashHeader;

	@FindBy(xpath=externalxpath.xpath.profilHeader)
	public static WebElement profilHeader;

	@FindBy(xpath=externalxpath.xpath.profileHeaderOption)
	public static WebElement profileHeaderOption;



	//mapping of dashbooard page
	@FindBy(xpath=externalxpath.xpath.fundSection)
	public static WebElement fundSection;

	@FindBy(xpath=externalxpath.xpath.fundList)
	public static List<WebElement> fundList;

	@FindBy(xpath=externalxpath.xpath.pendingGrant)
	public static WebElement pendingGrant;

	@FindBy(xpath=externalxpath.xpath.pendinglist)
	public static List<WebElement> pendinglist;

	@FindBy(xpath=externalxpath.xpath.article)
	public static WebElement article;

	@FindBy(xpath=externalxpath.xpath.articleList)
	public static List<WebElement> articleList;

	@FindBy(xpath=externalxpath.xpath.organizationTitle)
	public static WebElement organizationtitle;

	@FindBy(id=externalxpath.xpath.organizationSearch)
	public static WebElement organizationSearch;

	@FindBy(xpath=externalxpath.xpath.organizationlist)
	public static List<WebElement> organizationlist;

	
	//mapping of logout xpath
	@FindBy(xpath=externalxpath.xpath.logoutBtn)
	public static WebElement logoutBtn;

	@FindBy(xpath=externalxpath.xpath.logoutConfm)
	public static WebElement logoutConfm;


}
