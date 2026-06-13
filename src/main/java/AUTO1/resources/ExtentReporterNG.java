package AUTO1.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports getReportObj() {
		String path = System.getProperty("user.dir") + "\\reports\\iindex.html";
		ExtentSparkReporter exSpark = new ExtentSparkReporter(path);
		exSpark.config().setReportName("ReportName-AAA");
		exSpark.config().setDocumentTitle("BBB Title");
		ExtentReports extentRep = new ExtentReports();
		extentRep.attachReporter(exSpark);
		extentRep.setSystemInfo("Test Run By", "Roopa");
		return extentRep;
	}
}
