package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003LoginDataDrivenTest extends BaseClass{
	
    @Test(dataProvider= "LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")
	
	public void verifyLogin(String email, String pwd, String exp) 
	{
		logger.info("****StartingTc002 login test");
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clicklogin();
		
		//MyAccount
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean status= macc.isMyAccountPage();
		
		if(exp.equalsIgnoreCase("Valid")) {
			
			if(status==true) 
			{
				Assert.assertTrue(true);
				macc.clicklogout();
			}
			else 
			{
				Assert.assertTrue(false);
				
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) {
			
			if(status==true) {
				
				macc.clicklogout();
				Assert.assertTrue(false);
			}
			else 
			{
				Assert.assertTrue(true);
				
			}
			
		}
		
		
		
		
		logger.info("***finished test case");
		
		
		
	}
	
	
	
	

}
