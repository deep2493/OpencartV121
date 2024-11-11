package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		logger.info("****Starting TC002_LoginTest****");
		try
		{
		HomePage hp = new HomePage(driver);
		logger.info("Clicked on My Account Link");
		hp.clickMyAccount();
		logger.info("Clicked on Login Link");
		hp.clickLogin();
		
		logger.info("Providing Login details...");
		LoginPage lp = new LoginPage(driver);
		lp.setEmailAddress(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickbtnLogin();
		
		logger.info("Validating Account Page");
		MyAccountPage ap = new MyAccountPage(driver);
	    if(ap.isMyAccountPageExists()==true)
	    {
		Assert.assertTrue(true);
	    }
	    else
	    {
	    	logger.error("Test Failed...");
			logger.debug("Debug logs...");
	    	Assert.assertTrue(false);
	    }
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
	}
	
}
