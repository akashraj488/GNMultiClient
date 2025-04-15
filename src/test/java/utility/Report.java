package utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Report implements ITestListener {
    private static ExtentSparkReporter sparkReporter;
    private static ExtentReports extent;
    public static ExtentTest test = null;

    @Override
    public void onStart(ITestContext context) {
        // Get parameter from testng.xml
        String reportname = context.getCurrentXmlTest().getParameter("reportname");
        if (reportname == null || reportname.isEmpty()) {
            reportname = "default-report";
        }

        String repName = reportname + ".html";
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/src/test/java/reports/" + repName);

        sparkReporter.config().setDocumentTitle("GN Automation Report");
        sparkReporter.config().setReportName("GN Smoke Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getTestClass().getName());
        test.log(Status.PASS, result.getName() + " got passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getTestClass().getName());
        test.log(Status.FAIL, result.getName() + " got failed");

        String imgPath;
        try {
            imgPath = new ScreenShot().takeScreenShot(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getTestClass().getName());
        test.log(Status.SKIP, result.getName() + " got skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush(); // report is written properly
        }
    }

    public static ExtentReports getExtent() {
        return extent;
    }
}
