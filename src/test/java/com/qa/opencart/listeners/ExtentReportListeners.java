package com.qa.opencart.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import static com.qa.opencart.factory.PlaywrightFactory.takeScreenshot;

public class ExtentReportListeners implements ITestListener {

    private static final String OUTPUT_FOLDER = "./build/";
    private static final String FILE_NAME = "TestExecutionReport.html";

    private static ExtentReports extent = init();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ExtentReports init() {
        Path path = Paths.get(OUTPUT_FOLDER);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);

        sparkReporter.config().setReportName("Open Cart Automation Test Results");
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        extentReports.attachReporter(sparkReporter);

        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Author", "Automation QA");
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        return extentReports;
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite started!");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println("Test Suite is ending!");
        extent.flush(); // Save all test results to the report
        test.remove();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        ExtentTest extentTest = extent.createTest(methodName, result.getMethod().getDescription());
        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " passed!");
        test.get().pass("Test passed");
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " failed!");
        try {
            String screenshotBase64 = takeScreenshot();
            test.get().fail(result.getThrowable(), 
                MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64, result.getMethod().getMethodName()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " skipped!");
        try {
            String screenshotBase64 = takeScreenshot();
            test.get().skip(result.getThrowable(), 
                MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64, result.getMethod().getMethodName()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName());
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
