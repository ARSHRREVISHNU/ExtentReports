import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.lang.reflect.Method;

public class TestClass {

    ExtentReports extent;
    ExtentSparkReporter extentSparkReporter;

    ExtentTest test;
    @BeforeTest
    public void before(){
         extent = new ExtentReports();
         extentSparkReporter = new ExtentSparkReporter("ExtentReporter.html");
            extent.attachReporter(extentSparkReporter);


    }

    @Test
    public void tezt1(){
        //ExtentTest extentTest = extent.createTest("test 1").assignAuthor("Shrrevishnu").assignCategory("UI Test").assignDevice("Chrome");
        test = extent.createTest(getClass().getSimpleName()+" : "+ new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName()+"()");
        test.log(Status.PASS, "User launched website");
        test.pass("User launched");
        Assert.assertEquals("1", "1");
    }
    @Test
    public void tezt2(){
        test = extent.createTest(getClass().getSimpleName()+" : "+ new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName()+"()");
        test.warning("Warning message.");
        Assert.assertEquals("1", "2");
    }
    @Test
    public void tezt3(){
        test = extent.createTest(getClass().getSimpleName()+" : "+ new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName()+"()");
        test.info("Info message.");
       // Assert.assertEquals("2", "2");
        assertThat("2", equalTo("2"));
    }

//    @AfterTest
//    public void after(){
//        extent.flush();
//    }
//
//
    @AfterMethod
    public void after(ITestResult result){

        if(result.getStatus() == ITestResult.SUCCESS){
            test.pass("Test passed");
        }
        else if(result.getStatus() ==ITestResult.FAILURE){
            test.log(Status.FAIL, result.getThrowable().getMessage());
        }
        else {
            test.log(Status.SKIP, result.getThrowable().getMessage());
        }

        extent.flush();
    }


}
