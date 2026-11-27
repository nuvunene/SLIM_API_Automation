package api.Utillities;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name ="Data")
	public String [][] getAllData() throws EncryptedDocumentException, IOException
	{
		String path=System.getProperty("user.dir")+"//TestData//UserData.xlsx";
		ExcellUtility el=new ExcellUtility(path);
		int rowNum=el.getRowCount("User Test Data");
		int colCount=el.getCellCount("User Test Data",1);
		
		String apiData[][]=new String[rowNum-1][colCount];
		for(int i=1;i<rowNum;i++)
		{
			for(int j=0;j<colCount;j++)
			{
				  apiData[i-1][j]=el.getCellData("User Test Data",i, j);
			}
		}
	     return apiData;
	}
	
	@DataProvider(name="userName")
	public String[] getUserName() throws EncryptedDocumentException, IOException
	{
		String path=System.getProperty("user.dir")+"//TestData//UserData.xlsx";
		ExcellUtility el=new ExcellUtility(path);
		int rowCount=el.getRowCount("User Test Data");
		String [] apiData=new String[rowCount-1];
		
		for(int i=1;i<rowCount;i++)
		{
		   apiData[i-1]=el.getCellData("User Test Data",i,1);
		}
		return apiData;
	}

}
