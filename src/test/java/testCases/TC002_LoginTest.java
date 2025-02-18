package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity", "Master"})
	
	public void verifyLogin() 
	{
		logger.info("****StartingTc002 login test");
		
		try {
			
		
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		hp.clickLogin();
		
		
		LoginPage lp=new LoginPage(driver);
		
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clicklogin();
		
		//MyAccount
		
		MyAccountPage macc=new MyAccountPage(driver);
		
		boolean status= macc.isMyAccountPage();
		
		Assert.assertEquals(status, true, "Login Failed");
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		
		logger.info("*****FinishTc002 login Test");
		
	}
	
	

}
