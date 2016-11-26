package ca.uwaterloo.article_extractor.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ArticleSearchHelper
{
    private static Logger log = LogManager.getLogger(ArticleSearchHelper.class);
    private static final String RESULTS_XPATH = "//*[@id='rso']//h3/a";
    private static final String AYNC_ELEMENT = "resultStats";
    private static final String WEB_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_PATH = "/home/v2john/tools/selenium/chromedriver";
    private static final String SEARCH_ENGINE = "http://www.google.com";
    private static final String SEARCH_BOX_ELEMENT = "q";
    private static final String RESULT_URL_ELEMENT = "href";
    private static final String LINE_BREAK = "\n";
    private static final Integer SEARCH_TIMEOUT_SECONDS = 10;

    private WebDriver driver = null;
    private WebElement element = null;

    public ArticleSearchHelper()
    {
        System.setProperty(WEB_DRIVER_PROPERTY, CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
    }

    public void quitDriver()
    {
        driver.quit();
    }

    public List<URL> getURLForArticleHeadline(String articleHeadline)
        throws InterruptedException
    {
        List<URL> articleURLs = new ArrayList<>();

        try
        {
            driver.get(SEARCH_ENGINE);
            element = driver.findElement(By.name(SEARCH_BOX_ELEMENT));
            element.clear();
            element.sendKeys(articleHeadline + LINE_BREAK);
            element.submit();

            WebElement myDynamicElement =
                (new WebDriverWait(driver, SEARCH_TIMEOUT_SECONDS))
                    .until(ExpectedConditions
                    .presenceOfElementLocated(By.id(AYNC_ELEMENT)));

            List<WebElement> findElements = driver.findElements(By.xpath(RESULTS_XPATH));

            for (WebElement webElement : findElements)
            {
                try
                {
                    articleURLs.add(new URL(webElement.getAttribute(RESULT_URL_ELEMENT)));
                }
                catch (MalformedURLException e)
                {
                    log.error("MalformedURLException while parsing results for " + articleHeadline);
                }
            }
        }
        catch (Exception e)
        {
            log.error("Exception while processing URL extractor for article " + articleHeadline);
            log.error(e);
        }

        return articleURLs;
    }
}
