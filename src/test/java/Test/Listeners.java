package Test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends Base implements ITestListener{

	ExtentTest test;
	ExtentReports extent = ExtendReporter.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
    /*sending all 'test' object in to threadLocal pool & this is responsible to give you 
	the right object when you reporting fail/pass*/
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Tests Passed");			
	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		//Screenshot
		WebDriver driver = null;
		String getMethodName = result.getMethod().getMethodName(); 
		try {
			   driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			}  catch (Exception e) {
			}

		try {
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(getMethodName, driver), result.getMethod().getMethodName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		extent.flush();//this will notify test finished.Add this to end of all tests
		
	}
}
