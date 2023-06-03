import configuration.DriverFactory;
import configuration.WEBDRIVERS;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.createDriver(WEBDRIVERS.CHROMECLEAN);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://dnipro.ithillel.ua/courses/front-end-basic");
        CoursePage frontendCourse = new CoursePage(driver);

        System.out.println("The title of the course is: " + frontendCourse.getCourseTitle());
        System.out.println("The rating of this course is: " + frontendCourse.getCourseRate());
        System.out.println("The description of the course is: " + frontendCourse.getCourseDescription());
        System.out.println(
            "The names of coaches are: " + Arrays.toString(
                frontendCourse.getListCoachesNames().toArray()
            )
        );

        driver.quit();
    }
}