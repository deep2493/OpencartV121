package testCases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("****Starting TC001_AccountRegistrationTest****");
		try 
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account Link");

		hp.clickRegister();
		logger.info("Clicked on Register Link");

		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer details...");

		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString() +"@gmail.com");
		regpage.setTelephone(randomNumber());
		String password = randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		logger.info("Validating Expected message");
		String confmsg = regpage.getConfirmationmsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		
		else {
			logger.error("Test Failed...");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}

		//Assert.assertEquals(regpage.getConfirmationmsg(), "Your Account Has Been Created!");
		}
		
		catch(Exception e)
		{
		
		
		Assert.fail();
		}
		
		logger.info("****Finised TC001_AccountRegistrationTest****");

	}
}
		

