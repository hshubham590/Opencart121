package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path= ".\\testData\\Opencart_LoginData.xslx";  //take up excel from testdata
		
		Excelutility xlutil= new Excelutility(path);    //created object of excelutil
		
		int totalrows= xlutil.getRowCount("Sheet1");
		int totalcols= xlutil.getCellCount("Sheet 1", 1);
		
		String loginData[][]= new String [totalrows][totalcols];
		
		for(int r=1; r<=totalrows; r++) {
			
			for(int c=0; c<totalcols; c++) 
			{
				loginData[r-1][c]= xlutil.getCellData("Sheet1", r, c);
				
			}
			
			
			
		}
		return loginData;
		
		
		
		
		
	}

}
