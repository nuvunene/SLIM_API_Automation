package api.Utillities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcellUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	// public WorkbookFactory wb;
	public Sheet sheet;
	public Row row;
	public Cell cell;
	public CellStyle style;
	String path;

	public ExcellUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		fi = new FileInputStream(path);
		Workbook wb = WorkbookFactory.create(fi);
		sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		wb.close();
		fi.close();
		return rowCount;

	}

	public int getCellCount(String sheetName, int rowNum) throws EncryptedDocumentException, IOException {
		fi = new FileInputStream(path);
		Workbook wb = WorkbookFactory.create(fi);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}
	public String getCellData(String sheetName,int rownum,int column) throws EncryptedDocumentException, IOException
	{
		   fi=new FileInputStream(path);
		   Workbook wb=WorkbookFactory.create(fi);
		   sheet=wb.getSheet(sheetName);
		   row=sheet.getRow(rownum);
		   cell=row.getCell(column);
		   DataFormatter formatter=new DataFormatter();
		   String data;
		   try
		   {
			   data=formatter.formatCellValue(cell);
		   }
		   catch(Exception e)
		   {
			   data="";
		   }
		  finally
		  {
		   wb.close();
		   fi.close();
		  }
		   return data;
		   
	}

}
