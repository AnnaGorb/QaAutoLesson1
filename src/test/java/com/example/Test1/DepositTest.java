package com.example.Test1;

/**
 * Created by Shelty on 10.08.2016.
 */

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

    public class DepositTest {
        private WebDriver driver;
        private String baseUrl;
        private boolean acceptNextAlert = true;
        private StringBuffer verificationErrors = new StringBuffer();

        @BeforeClass(alwaysRun = true)
        public void setUp() throws Exception {
            driver = new FirefoxDriver();
            baseUrl = "http://finance.liga.net/";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void testDepositNew() throws Exception {
            driver.get(baseUrl + "/finmarket/calculators.htm");
            driver.findElement(By.id("txtSumOfDep")).clear();
            driver.findElement(By.id("txtSumOfDep")).sendKeys("500000");
            driver.findElement(By.id("count_months")).clear();
            driver.findElement(By.id("count_months")).sendKeys("12");
            driver.findElement(By.id("percent_dep")).clear();
            driver.findElement(By.id("percent_dep")).sendKeys("25");
            new Select(driver.findElement(By.id("ddl2"))).selectByVisibleText("Выплата процентов в конце срока");
            driver.findElement(By.cssSelector("input.btn")).click();
            assertEquals(driver.findElement(By.cssSelector("div.resultSum")).getText(), "125000.00");
        }

        @AfterClass(alwaysRun = true)
        public void tearDown() throws Exception {
            driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }

        private boolean isElementPresent(By by) {
            try {
                driver.findElement(by);
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        private boolean isAlertPresent() {
            try {
                driver.switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

        private String closeAlertAndGetItsText() {
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                if (acceptNextAlert) {
                    alert.accept();
                } else {
                    alert.dismiss();
                }
                return alertText;
            } finally {
                acceptNextAlert = true;
            }
        }
    }

