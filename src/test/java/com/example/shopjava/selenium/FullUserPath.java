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

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FullUserPath {
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
    public void testAnonymousUser() throws InterruptedException {
        WebElement search = driver.findElement(By.id("search"));
        search.sendKeys("apple", Keys.RETURN);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        List<WebElement> webElementList = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.className("product-link")));

        Assertions.assertTrue(webElementList.size() >= 1);

        Actions action = new Actions(driver);
        Action seriesOfActions = (Action) action.keyDown(Keys.CONTROL)
                .click(webElementList.get(0))
                .build();
        seriesOfActions.perform();
        Thread.sleep(1000);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Assertions.assertTrue(driver.findElement(By.id("carousel")).isDisplayed());

        WebElement buyButton = driver.findElement(By.className("add-to-cart"));
        Action buyButtonAction = action.keyDown(Keys.CONTROL).click(buyButton).build();
        buyButtonAction.perform();
        Thread.sleep(1000);
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));

        Assertions.assertTrue(driver.findElement(By.id("checkout-form")).isDisplayed());

        WebElement name = driver.findElement(By.name("name"));

        List<WebElement> phone = new ArrayList<>();
        phone.add(driver.findElement(By.name("phone1")));
        phone.add(driver.findElement(By.name("phone2")));
        phone.add(driver.findElement(By.name("phone3")));

        WebElement email = driver.findElement(By.name("email"));

        WebElement city = driver.findElement(By.name("city"));

        name.sendKeys("Name");
        name.sendKeys("Name");
        phone.get(0).sendKeys("050");
        phone.get(1).sendKeys("555");
        phone.get(2).sendKeys("5555");
        email.sendKeys("somemail@gmail.com");
        city.sendKeys("Lviv");

        WebElement nextBut = driver.findElement(By.id("nextBut"));
        nextBut.click();
        Thread.sleep(1000);

        List<WebElement> cardNumber = new ArrayList<>();
        cardNumber.add(driver.findElement(By.name("part1")));
        cardNumber.add(driver.findElement(By.name("part2")));
        cardNumber.add(driver.findElement(By.name("part3")));
        cardNumber.add(driver.findElement(By.name("part4")));

        List<WebElement> cardDate = new ArrayList<>();
        cardDate.add(driver.findElement(By.name("prefix")));
        cardDate.add(driver.findElement(By.name("suffix")));

        WebElement cvv = driver.findElement(By.name("cvv"));

        for (WebElement webElement : cardNumber) {
            webElement.sendKeys("1111");
        }

        cardDate.get(0).sendKeys("20");
        cardDate.get(1).sendKeys("2050");

        cvv.sendKeys("111");
        driver.findElement(By.id("submitBut")).click();
        Thread.sleep(3000);

        Assertions.assertEquals("somemail@gmail.com", driver.findElement(By.xpath("/html/body/main/div/div[2]/div[2]/p[2]")).getText());
    }
}
