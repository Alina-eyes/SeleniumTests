import configuration.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FindTeacherName extends BaseClass {
    public static void main(String[] args) throws Exception {
        driver.manage().window().maximize();
        driver.get("https://ithillel.ua/");

        var driverWait = (new WebDriverWait(driver, Duration.ofSeconds(3)));

        var coursesMenuButton = driverWait
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-dropdown-trigger=\"coursesMenu\"]")));
        coursesMenuButton.click();

        var programmingButton = driverWait
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-category=\"programming\"]")));
        programmingButton.click();

        var allCourseElements = driver.findElements(By.className("course-list_item"));
        for (var el: allCourseElements) {
            if (el.getText().contains("Front-end Basic")) {
                el.findElement(By.tagName("a")).click();
                break;
            }
        }

        var coachesSection = driver.findElement(By.id("lazySectionCoaches"));
        var actions = new Actions(driver);
        actions.moveToElement(coachesSection);
        actions.perform();

        driverWait
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".coaches_list")));
        var allCoachNames = driver.findElements(By.className("coach-card_name"));
        for (var coachName: allCoachNames) {
            var name = coachName.getText();
            if (!name.equals("")) {
                System.out.println(name);
            }
        }

        driver.quit();
    }
}
