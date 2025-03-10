package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;




public class BaseClass {
	
	public WebDriver driver;
	public Logger logger;   //Log4j
	public Properties p;
	
	@BeforeClass(groups= {"Sanity", "Regression", "Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException 
	{
		
		
		//loading config.properties file
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();   //create new object
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass()); 
		//create logs of current test class whichever run
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			
			DesiredCapabilities capabilities=  new DesiredCapabilities();
			
			//os
			
			if(os.equalsIgnoreCase("windows")) {
				
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);

			}
			else {
				
				System.out.println("Invalid os Name");
				return;
			}
			
			//browser
			
			switch(br.toLowerCase())
			{
			case "chrome" : capabilities.setBrowserName("chrome");break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge");break;
			
			default : System.out.println("Invalid Browser Name");return;

			}
				
			driver = new RemoteWebDriver(new URL ("http://192.168.1.34:4444/wd/hub"),capabilities);
					
		}
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase())
			{
			case "chrome" : driver=new ChromeDriver();break;
			case "edge" : driver=new EdgeDriver();break;

			default: System.out.println("Invalid Browser name");return;
			
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));   //reading properties from config.properties file
		driver.manage().window().maximize();
		
	}
	
	
	@AfterClass(groups= {"Sanity", "Regression", "Master"})
	public void tearDown() 
	{
		driver.quit();
		
	}
	

	public String randomString() {
		
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
     public String randomNumber() {
		
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	} 
	
     public String randomAlphaNumeric() {
 		
    	 String generatedString = RandomStringUtils.randomAlphabetic(5);
    	 String generatednumber = RandomStringUtils.randomNumeric(3);
    	 return(generatedString+"@"+generatednumber);                   //randomAlphanumeric
    	 
 	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
