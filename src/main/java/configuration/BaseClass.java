package configuration;

import org.openqa.selenium.WebDriver;

public class BaseClass {
    public static WebDriver driver=DriverFactory.createDriver(WEBDRIVERS.CHROMECLEAN);
}
