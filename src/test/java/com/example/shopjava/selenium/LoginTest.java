package com.example.shopjava.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginTest {
    @LocalServerPort
    private int port;

    private String baseURL;

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseURL = "http://localhost:" + port + "/";
        driver.get(baseURL);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Order(1)
    public void testLogin() {
        signIn();

        driver.get(baseURL);
        Assertions.assertTrue(driver.findElement(By.className("dropdown-user")).isDisplayed());

        WebElement wishlist = driver.findElement(By.id("header-heart"));
        wishlist.click();

        Assertions.assertTrue(driver.findElement(By.id("likes")).isDisplayed());
    }

    @Test
    @Order(2)
    public void testManipulateItemsInWishlist() throws InterruptedException {
        signIn();
        driver.get(baseURL + "phones");
        List<WebElement> webElementList = driver.findElements(By.className("empty-heart"));
        if(webElementList.size() >= 2) {
            Actions action=new Actions(driver);
            Action seriesOfActions = (Action) action.keyDown(Keys.CONTROL)
                    .click(webElementList.get(0))
                    .click(webElementList.get(1))
                    .build();
            seriesOfActions.perform();
            Thread.sleep(3000);
            driver.findElement(By.id("header-heart")).click();
            WebDriverWait wait = new WebDriverWait(driver, 20);
            List<WebElement> actualList = wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.className("wishlist-product")));
            Assertions.assertEquals(2, actualList.size());
            driver.findElement(By.id("likes")).click();
            seriesOfActions = (Action) action.keyDown(Keys.CONTROL)
                    .click(webElementList.get(0))
                    .build();
            seriesOfActions.perform();
            Thread.sleep(3000);
            driver.findElement(By.id("header-heart")).click();
            actualList = wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.className("wishlist-product")));
            Assertions.assertEquals(1, actualList.size());
        }
    }

    private void signIn() {
        driver.findElement(By.id("login-button")).click();
        Assertions.assertTrue(driver.findElement(By.id("login")).isDisplayed());

        WebElement loginField = driver.findElement(By.id("profileEmail"));
        WebElement passwordField = driver.findElement(By.id("profilePassword"));
        WebElement submitButton = driver.findElement(By.id("submitLogin"));

        loginField.sendKeys("somemail@gmail.com");
        passwordField.sendKeys("somePassword");

        submitButton.click();
    }
}
