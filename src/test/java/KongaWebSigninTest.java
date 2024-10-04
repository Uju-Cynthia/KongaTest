import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


// KongaWebSigninTest
public class KongaWebSigninTest {

    private WebDriver driver;

    // Method to clear username and password fields
    private void clearField(){
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("password")).clear();
    }

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.konga.com/");


        // Verify if the user is on the correct webpage
        if (driver.getCurrentUrl().contains("https://www.konga.com/"))
            System.out.println("Correct Webpage");
        else
            System.out.println("Wrong webpage");
    }

    //Method to click the Login/SignUp button
    @Test
    public void clickSigninButton() {
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
    }

    @Test (priority = 0)
    public void invalidSignIn() throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys("test@");
        driver.findElement(By.id("password")).sendKeys("a122");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
    }

    @Test (priority = 1)
    public void blankSignIn() throws InterruptedException {
        clearField();
        driver.findElement(By.id("username")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(1000);
    }

    @Test (priority = 2)
    public void validSignIn() throws InterruptedException {
        clearField();
        driver.findElement(By.id("username")).sendKeys("cynthialucky101@gmail.com");
        driver.findElement(By.id("password")).sendKeys("ujunwa");
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(1000);
    }

    @Test (priority = 3)
    public void addToCart() throws InterruptedException {
        //select categories
        WebElement categoryMenu = driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(categoryMenu).perform();
        //click on Computers and Accessories
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();
        Thread.sleep(5000);
        //click on laptop
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[4]/a")).click();
        //click on macbook
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li/a/ul/li[1]/a/label")).click();
        //click Add To Cart
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[2]/div/div/div[3]/form/div[2]/button")).click();
        Thread.sleep(5000);

    }

    @Test (priority = 4)
    public void checkOut() throws InterruptedException {
        //click My Cart
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]")).click();
        Thread.sleep(5000);
        //click CheckOut
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();
        Thread.sleep(10000);

    }

    @Test (priority = 6)
    public void addAddress() throws InterruptedException {
        //Add address
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[1]/div/a")).click();
        driver.findElement(By.id("firstname")).sendKeys("Jane");
        driver.findElement(By.id("lastname")).sendKeys("Cynthia");
        driver.findElement(By.id("telephone")).sendKeys("08168397273");
        driver.findElement(By.id("street")).sendKeys("johnson");
        driver.findElement(By.id("city")).sendKeys("Lagos");
        driver.findElement(By.name("regionId")).sendKeys("Lagos");
        driver.findElement(By.name("areaId")).sendKeys("Ikeja");
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/form/div[8]/div[1]/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div/form/button")).click();
    }

    @Test (priority = 7)
    public void paymentOptions() throws InterruptedException {
        driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[1]/div[2]/section[1]/main[1]/div[1]/form[1]/div[1]/div[1]/section[2]/div[1]/div[2]/div[1]/div[1]/span[1]/input[1]")).click();
        Thread.sleep(8000);
        driver.findElement(By.name("placeOrder")).click();
        Thread.sleep(10000);
    }

    @Test (priority = 8)
    public void selectPaymentMethod() throws InterruptedException {
        WebElement payment = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame("kpg-frame-component");
        Thread.sleep(8000);
        driver.findElement(By.className("Card")).click();
        Thread.sleep(8000);
    }

    @Test (priority = 9)
    public void inputCardDetails() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"card-number\"]")).sendKeys("4960091812582855");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"expiry\"]")).sendKeys("0530");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"cvv\"]")).sendKeys("231");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"validateCardForm\"]")).click();
        Thread.sleep(3000);
    }

    @Test (priority = 10)
    public void printErrorMessage() throws InterruptedException {
        WebElement message = driver.findElement(By.xpath("/html/body/section/section/section/div[2]/div[3]/div/div[1]/span"));
        System.out.println(message.getText());
        Thread.sleep(5000);
    }

    @Test (priority = 11)
    public void exitIframe() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/section/section/section/div[2]/div[1]/aside")).click();
        Thread.sleep(5000);
    }

    @Test (priority = 9)
    public void switchToBrowser() throws InterruptedException {
        driver.switchTo().defaultContent();
        Thread.sleep(5000);
    }

    @AfterTest
    public void quitBrowser(){
        driver.quit();
    }


}
