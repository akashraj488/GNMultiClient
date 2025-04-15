package externalxpath;

public class xpath 
{
	//login xpath
	public static final String clientLogo="//*[@class=\"d-inline-block align-top gn-logo\"]";
	public static final String GNlogo="//*[@class=\"nav-powered-view\"]";
	public static final String footerclientLogo="//*[@class=\"logo\"]";

	public static final String username="//*[@id=\"email\"]";
	public static final String password="//*[@id=\"password\"]";
	public static final String err="//*[@class='alert alert-danger']";
	public static final String Login="//*[@class=\"btn btn-accent w100\"]";

	//header Title xpath
	public static final String dashHeader="//h1[@id='id_page_title']";
	public static final String profilHeader="(//a[contains(@href, '/m/profile') and contains(@class, 'nav-link')])[1]";
	public static final String profileHeaderOption="//li[contains(@class,'breadcrumb-item active')]";

	//dashboard xpath

	public static final String fundSection="//h3[contains(text(),'Funds')]";
	public static final String fundList="//div[contains(@class, 'row row-fund-overview')]";
	public static final String pendingGrant="//span[contains(text(),'Pending Grants')]";
	public static final String pendinglist="//h4[contains(@class, 'page-subtitle mt-4 mb-2')]";
	public static final String article="//h3[contains(text(),'Articles')]";
	public static final String articleList="//*[@class=\"content-promo promo-box\"]";
	public static final String organizationTitle="(//h3[contains(@class,'page-subtitle uppercase')])[3]";
	public static final String organizationSearch="id_org_name";
	public static final String organizationlist="//*[@class='tt-suggestion tt-selectable']";



	//logout xpath
	public static final String logoutBtn="//*[text()='Log Out']";
	public static final String logoutConfm="//button[text()='Confirm']";


}
