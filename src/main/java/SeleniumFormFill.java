import configuration.BaseClass;
import configuration.DriverFactory;
import configuration.WEBDRIVERS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class SeleniumFormFill extends BaseClass {

    private static class TestData {
        public String name;
        public String email;
        public String phoneNumber;

        TestData(String name, String email, String phoneNumber) {
            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }
    }

    public static void main(String[] args) throws Exception {
        var listOfNames = Arrays.asList(
            new TestData("Alina", "test@gmail.com", "501234567"),
            new TestData("Albert", "person@yahoo.com", "502341543"),
            new TestData("Alexander", "alal@msn.com", "501234567"),
            new TestData("Alphonse", "alph@me.net", "501234527"),
            new TestData("Liam", "liam@liam.gov", "501314242")
        );

        for (var testData: listOfNames) {
            testTheForm(testData);
        }
    }
    public static void testTheForm(TestData testData) throws Exception {
        driver.manage().window().maximize();
        driver.get("https://ithillel.ua/");

        var driverWait = (new WebDriverWait(driver, Duration.ofSeconds(10)));

        // Simple way of waiting for the button
        //Thread.sleep(3_000);
        //var consultationButton = driver.findElement(By.id("btn-consultation-hero"));

        // Better way of waiting for the button to be clickable
        var consultationButton = driverWait
           .until(ExpectedConditions.elementToBeClickable(By.id("btn-consultation-hero")));

        consultationButton.click();

        // This is the element containing the entire consultation form
        var formElement = driverWait
            .until(ExpectedConditions.elementToBeClickable(By.id("form-consultation")));

        var nameInput = driver.findElement(By.id("input-name-consultation"));
        //testTheError(formElement, nameInput);
        //nameInput.clear();

        // Fill in the name
        nameInput.sendKeys(testData.name);
        // Fill in the email
        driver.findElement(By.id("input-email-consultation")).sendKeys("test@gmail.com");
        // Fill in the phone number
        driver.findElement(By.id("input-tel-consultation")).sendKeys(testData.phoneNumber);

        // Click on the dropdown to show the list of options
        driver.findElement(By.id("listbox-btn-input-course-consultation")).click();

        driverWait
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-entry-id=\"106969\"]")))
            .click();

        formElement
            .findElement(By.className("form_agreement"))
            .findElement(By.className("checkbox_checkmark"))
            .click();

        // Click the submit button
        // This code is commented out to not spam the website with so many requests
        // formElement
        //    .findElement(By.className("btn-submit"))
        //    .click();
    }

    static void testTheError(WebElement formElement, WebElement nameInput) throws Exception {
        nameInput.sendKeys("A");

        // Blur the element to see the error by clicking on the header of the form
        formElement.findElement(By.className("form_title")).click();
        // We now expect the error message to show up
        Thread.sleep(1_000);
        var status = formElement.findElement(By.id("field-name-consultation")).getAttribute("data-status");
        if (!status.equals("invalid")) {
            throw new Exception("Incorrect status, expecting invalid!");
        }
    }
}
