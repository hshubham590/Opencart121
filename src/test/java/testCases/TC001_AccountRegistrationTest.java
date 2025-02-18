package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	
	@AfterClass
	public void tearDown() 
	{
		driver.close();
	}
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration () 
	{
		
		
		logger.info("*****Starting TC001_AccountRegistrationTest *****");
		
		HomePage hp= new HomePage(driver);
		
		hp.clickMyAccount();
        logger.info("Clicked on MyAccount link");
		
		hp.clickRegister();
		logger.info("Clicked on Register link");
		
		AccountRegistrationPage regpage= new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details...");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastname(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		//Alphanumeric
		
		String password=randomAlphaNumeric();
		
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		

		logger.info("Validating expected message");
		
		String msg = regpage.getConfirmationMsg();
		
		//validation
		Assert.assertEquals(msg ,"Your Account Has Been Created!");
		
		
	}
	 
	
	
	
}
