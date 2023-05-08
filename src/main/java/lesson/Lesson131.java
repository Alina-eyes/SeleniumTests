package lesson;

import configuration.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Lesson131 extends BaseClass {
    static private final String mainURL = "https://dnipro.ithillel.ua/";

    static private final By byXpath = By.xpath("//a[contains(@class,\"header-bar-link\") and contains(@class,\"-city\") and contains(@data-city-id,\"kh\")]");

    public static void main(String[] args) throws InterruptedException {
        driver.manage().window().maximize();
        driver.get(mainURL);
        Thread.sleep(3000);
        WebElement searchField = driver.findElement(byXpath);
        System.out.println(searchField.getAttribute("href"));
        System.out.println(searchField.getText());
        System.out.println(searchField.getTagName());
        System.out.println(searchField.isDisplayed());

        printAllcityButtonText("Харків");
        Thread.sleep(3000);
        driver.quit();

    }

    private static void printAllcityButtonText(String city) {
        List<WebElement> webElements =
                driver.findElements(By.xpath("//*[contains(@class,\"header-bar-link\") and contains(@class,\"-city\")]"));
        for (WebElement webElement : webElements) {
            if (webElement.getText().equals(city)) {
                webElement.click();
                break;
            }
        }
    }
}
