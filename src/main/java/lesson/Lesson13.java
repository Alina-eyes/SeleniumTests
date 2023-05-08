package lesson;

import configuration.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Lesson13 extends BaseClass {
    static private final String mainURL = "https://www.google.com/",
            title = "Google";
    static private final By byXpath=By.xpath("//*[@id=\"APjFqb\"]"),
    byID=By.id("APjFqb"),
    byTagName=By.tagName("textarea"),
    byClass=By.className("gLFyf"),
    byName=By.name("q");
    public static void main(String[] args) throws InterruptedException {
        driver.get(mainURL);
        if(!driver.getTitle().equals(title)){
            System.out.println("Title not equals");
        }
        WebElement searchField =driver.findElement(byTagName);
        searchField.sendKeys("Ukraine");
        searchField.submit();

        Thread.sleep(3000);
        driver.quit();

    }
}
