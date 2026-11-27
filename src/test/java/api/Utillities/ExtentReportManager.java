package api.Utillities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	@Override
	public void onStart(ITestContext context)
	{
		//String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		//repName="Test-Report-"+timeStamp+".html";
		repName="Test-Report-"+".html";
		sparkReporter=new ExtentSparkReporter(".\\Reports\\"+repName);
		
		sparkReporter.config().setDocumentTitle("NAC_LOAN_PROJECT");
		sparkReporter.config().setReportName("Pet_store_Report");
		sparkReporter.config().setTheme(Theme.DARK);
		
		   extent=new ExtentReports();
		   extent.attachReporter(sparkReporter);
		   extent.setSystemInfo("Application","pet store user API");
		   extent.setSystemInfo("operating System", System.getProperty("os.name"));
		   extent.setSystemInfo("User Name", System.getProperty("user.name"));
		   extent.setSystemInfo("Environment", "QA");
		   
	}
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS,"Test passed");
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL,result.getThrowable().getMessage());
		
	}
	@Override
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test skipped");
		test.log(Status.SKIP,result.getThrowable().getMessage());
	}
	 @Override
	 public void onFinish(ITestContext context)
	 {
		 extent.flush();
	 }
	

}
