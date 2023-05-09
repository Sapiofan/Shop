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
class IntegratedLoginTest {
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
        if (webElementList.size() >= 2) {
            Actions action = new Actions(driver);
            // add 2 items to the wishlist
            Action seriesOfActions = (Action) action.keyDown(Keys.CONTROL)
                    .click(webElementList.get(0))
                    .click(webElementList.get(1))
                    .build();
            seriesOfActions.perform();
            Thread.sleep(1000);

            // check items were added
            driver.findElement(By.id("header-heart")).click();
            WebDriverWait wait = new WebDriverWait(driver, 20);
            List<WebElement> actualList = wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.className("wishlist-product")));
            Assertions.assertEquals(2, actualList.size());

            // remove the item in the list of devices
            driver.get(baseURL + "phones");
            webElementList = driver.findElements(By.className("empty-heart"));
            seriesOfActions = (Action) action.keyDown(Keys.CONTROL)
                    .click(webElementList.get(0))
                    .build();
            seriesOfActions.perform();
            Thread.sleep(1000);

            // check only one item remained in the wishlist
            driver.findElement(By.id("header-heart")).click();
            actualList = wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.className("wishlist-product")));
            Assertions.assertEquals(1, actualList.size());

            // remove item in the wishlist exactly
            List<WebElement> wishlistItems = driver.findElements(By.className("heart"));
            seriesOfActions = (Action) action.keyDown(Keys.CONTROL)
                    .click(wishlistItems.get(0))
                    .build();
            seriesOfActions.perform();
            Thread.sleep(1000);

            // check the wishlist is empty
            actualList = driver.findElements(By.className("wishlist-product"));
            Assertions.assertEquals(0, actualList.size());

            driver.get(baseURL + "phones");
            webElementList = driver.findElements(By.className("empty-heart"));

            // again add 2 items
            seriesOfActions = (Action) action.keyDown(Keys.CONTROL)
                    .click(webElementList.get(0))
                    .click(webElementList.get(1))
                    .build();
            seriesOfActions.perform();
            Thread.sleep(1000);

            // check items are presented
            driver.findElement(By.id("header-heart")).click();
            actualList = wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.className("wishlist-product")));
            Assertions.assertEquals(2, actualList.size());

            // clean all items
            driver.findElement(By.id("clean-button")).click();
            Thread.sleep(1000);
            actualList = driver.findElements(By.className("wishlist-product"));
            Assertions.assertEquals(0, actualList.size());
        }
    }

    @Test
    @Order(3)
    public void testManipulateItemsInCart() throws InterruptedException {
        signIn();
        removeCartItemsBeforeTest();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseURL + "phones");
        List<WebElement> webElementList = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.className("add-product")));

        if (webElementList.size() >= 2) {
            Actions action = new Actions(driver);
            // add 2 items to the cart
            Action seriesOfActions = (Action) action.keyDown(Keys.CONTROL)
                    .click(webElementList.get(0))
                    .click(webElementList.get(1))
                    .click(driver.findElement(By.className("cart-button")))
                    .build();
            seriesOfActions.perform();
            Thread.sleep(1000);

            // check items were added
            List<WebElement> actualList = wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.className("product")));
            Assertions.assertEquals(2, actualList.size());

            // check subtotal sum
            String subtotal = driver.findElement(By.id("subtotal")).getText();
            int subtotalInt = Integer.parseInt(subtotal.substring(0, subtotal.length() - 1));
            List<WebElement> prices = wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.className("price")));
            int subtotalEl1 = Integer.parseInt(prices.get(0).getText().substring(0, prices.get(0).getText().length() - 1));
            Assertions.assertEquals(subtotalInt,
                    subtotalEl1 +
                            Integer.parseInt(prices.get(1).getText().substring(0, prices.get(1).getText().length() - 1)));

            // add number of the first item
            List<WebElement> plus = wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.className("plus-button")));
            seriesOfActions = (Action) action.keyDown(Keys.CONTROL)
                    .click(plus.get(0))
                    .build();
            seriesOfActions.perform();
            Thread.sleep(1000);

            // reload and check everything remained and calculation was done correctly
            driver.get(baseURL + "phones");
            seriesOfActions = (Action) action.keyDown(Keys.CONTROL)
                    .click(driver.findElement(By.className("cart-button")))
                    .build();
            seriesOfActions.perform();
            Thread.sleep(1000);
            subtotal = driver.findElement(By.id("subtotal")).getText();
            Assertions.assertEquals(subtotalInt + subtotalEl1,
                    Integer.parseInt(subtotal.substring(0, subtotal.length() - 1)));

            // remove items
            List<WebElement> removeItems;
            for (int i = 0; i < 2; i++) {
                removeItems = wait.until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(By.className("remove-button")));
                seriesOfActions = (Action) action.keyDown(Keys.CONTROL)
                        .click(removeItems.get(0))
                        .build();
                seriesOfActions.perform();
                Thread.sleep(1000);
            }

            removeItems = driver.findElements(By.className("remove-button"));
            Assertions.assertEquals(0, removeItems.size());
            subtotal = driver.findElement(By.id("subtotal")).getText();
            Assertions.assertEquals(0, Integer.parseInt(subtotal.substring(0, subtotal.length() - 1)));
        }
    }

    private void removeCartItemsBeforeTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        Actions action = new Actions(driver);
        Action seriesOfActions = (Action) action.keyDown(Keys.CONTROL)
                .click(driver.findElement(By.className("cart-button")))
                .build();
        seriesOfActions.perform();
        Thread.sleep(1000);
        List<WebElement> removeItems = driver.findElements(By.className("remove-button"));
        while (removeItems.size() != 0) {
            seriesOfActions = (Action) action.keyDown(Keys.CONTROL)
                    .click(removeItems.get(0))
                    .build();
            seriesOfActions.perform();
            Thread.sleep(1000);
            removeItems = wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.className("remove-button")));
        }
        driver.get(baseURL);
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
