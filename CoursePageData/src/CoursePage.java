import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoursePage {
    private WebDriver driver;

    @FindBy(xpath = "//h1[@class=\"course-descriptor_header\"]//strong")
    private WebElement courseHeader;

    @FindBy(className = "course-rating_average")
    private WebElement rating;

    @FindBy(className = "introduction-info_content")
    private WebElement description;

    @FindBy(id = "lazySectionCoaches")
    private WebElement lazySectionCoaches;

    public CoursePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCourseTitle() {
        return this.courseHeader.getText();
    }

    public String getCourseRate() {
        return rating.getText();
    }

    public String getCourseDescription() {
        return this.description.getText();
    }

    public List<String> getListCoachesNames() {
        Actions actions = new Actions(driver);
        actions.moveToElement(this.lazySectionCoaches);
        actions.perform();

        var driverWait = (new WebDriverWait(driver, Duration.ofSeconds(10)));
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".coaches_list")));

        WebElement showMoreButton = driver.findElement(By.id("coachesShowAllButton"));
        showMoreButton.click();

        List<String> names = new ArrayList<>();
        for (WebElement el: driver.findElements(By.className("coach-list_item"))) {
            names.add(el.findElement(By.className("coach-card_name")).getText());
        }

        return names;
    }
}
