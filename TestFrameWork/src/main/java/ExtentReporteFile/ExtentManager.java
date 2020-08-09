package ExtentReporteFile;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        String fileName = getReportName();
        String directory = System.getProperty("user.dir") + "/Reports/";
        new File(directory).mkdirs();
        String path = directory + fileName;
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
        htmlReporter.config().setEncoding("uft-8");
        htmlReporter.config().setDocumentTitle("CT Automation Suite Results");
        htmlReporter.config().setReportName("CT Test Results");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.setSystemInfo("CleverTap DashBoard","Automation Run Result");
        extent.setSystemInfo("Browser","Chrome");
        extent.setSystemInfo("Run By",System.getProperty("user.name").toUpperCase());
        extent.setSystemInfo("OS Info",System.getProperty("os.name").toUpperCase());
        extent.attachReporter(htmlReporter);

        return extent;


    }

    public static String getReportName(){
        Date d = new Date();
        String fileName = "Automation_Report_" + d.toString().replace(":","_").replace(" ","_") + ".html";
        return fileName;
    }
}
