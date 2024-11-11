package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven") //getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException
	{
		logger.info("****Starting TC003_LoginDDT****");
		try
		{
		HomePage hp = new HomePage(driver);
		logger.info("Clicked on My Account Link");
		hp.clickMyAccount();
		logger.info("Clicked on Login Link");
		hp.clickLogin();
		
		logger.info("Providing Login details...");
		LoginPage lp = new LoginPage(driver);
		lp.setEmailAddress(email);
		lp.setPassword(pwd);
		lp.clickbtnLogin();
		
		MyAccountPage ap = new MyAccountPage(driver);
		boolean targetPage = ap.isMyAccountPageExists();	
		
		/* Data is valid - login success -test pass - Logout
		                 - login failed-test fail
		        
		   Data is invalid - login success - test fail -Logout
		                   - login failed - test pass
		                   
	   */
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				ap.clickLogout();
				Assert.assertTrue(true);
				
				
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				ap.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
	}
		catch(Exception e)
		{
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("****Finished TC003_LoginDDT****");
}
}