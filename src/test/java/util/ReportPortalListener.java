package util;

import com.epam.learn.util.driver.DriverSingleton;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.TestNGService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

public class ReportPortalListener extends BaseTestNGListener {
    private static final Logger logger = LoggerFactory.getLogger(ReportPortalListener.class);

    public ReportPortalListener() {
        super(new TestNGService(ReportPortal.builder().build()));
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        logger.error("Test '{}' failed: {}", testResult.getName(), testResult.getThrowable().getMessage());
        DriverSingleton.scrollPageForScreenshot();
        DriverSingleton.takeScreenshot();
        DriverSingleton.attachScreenshots();
        super.onTestFailure(testResult);
    }

    @Override
    public void onConfigurationFailure(ITestResult testResult) {
        logger.error("Configuration failure: {}", testResult.getThrowable().getMessage());
        DriverSingleton.scrollPageForScreenshot();
        DriverSingleton.takeScreenshot();
        DriverSingleton.attachScreenshots();
        super.onConfigurationFailure(testResult);
    }
}