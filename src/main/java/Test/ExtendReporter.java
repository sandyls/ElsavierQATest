package Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporter {

	static ExtentReports extent;//declaring globally to access in all methods
	public static ExtentReports getReportObject()
	{
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);// ExtentSparkReporter is a helper class that helps to create some configuration and reports to main class ‘ExtentReports’
		reporter.config().setReportName("Web automation results:");
        reporter.config().setDocumentTitle("Test Result:");        
		extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Sandhya");
        return extent;
	}
}
