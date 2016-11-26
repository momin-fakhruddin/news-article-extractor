package ca.uwaterloo.article_extractor;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TestParseResultsSelenium
{
    @Test
    public void testGetTopResult()
    {
        System.setProperty("webdriver.chrome.driver", "/home/v2john/tools/selenium/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("EasyJet attracts more passengers in June but still lags Ryanair\n"); // send also a "\n"
        element.submit();

        // wait until the google page shows the result
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));
        System.out.println(findElements.size() + " elements");

        // this are all the links you like to visit
        for (WebElement webElement : findElements)
        {
            System.out.println(webElement.getAttribute("href"));
        }

        driver.quit();
    }
}
