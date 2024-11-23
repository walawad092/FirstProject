import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v127.storage.model.StorageBucketsDurability;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;

import net.bytebuddy.asm.Advice.Local;

public class MyTestCases {

	WebDriver driver = new ChromeDriver();

	String MyWebSite = "https://automationteststore.com/";

	String[] FirstNames = { "ahmad", "ali", "anas", "omar", "ayat", "alaa", "sawsan", "Rama" };

	String[] LastNames = { "Khaled", "mustafa", "Mohammad", "abdullah", "malek", "omar" };

	Random rand = new Random();
	
	String GlobalUserName = "";
	
	String GlobalUserNameForTheLogin = "";
	
	String Password = "ILOVEMYMOM123!@#";

	@BeforeTest
	public void MySetup() {

	  //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(MyWebSite);

	}

	@Test(priority = 1)
	public void MyTest() throws InterruptedException {

		int RandomIndexForTheFirstName = rand.nextInt(FirstNames.length);

		int RandomIndexForTheLastName = rand.nextInt(LastNames.length);

		String UserFirstName = FirstNames[RandomIndexForTheFirstName];

		String UserLastName = LastNames[RandomIndexForTheLastName];

		int RandomNumberForTheEmail = rand.nextInt(564548);

		String DomainName = "@gmail.com";

		String Email = UserFirstName + UserLastName + RandomNumberForTheEmail + DomainName;

		driver.findElement(By.partialLinkText("Login or register")).click();

		WebElement SignUpButton = driver.findElement(By.xpath("//button[@title='Continue']"));
		SignUpButton.click();

		Thread.sleep(2000);

		WebElement FirstNameInput = driver.findElement(By.id("AccountFrm_firstname"));
		FirstNameInput.sendKeys(UserFirstName);
		GlobalUserName = UserFirstName;

		WebElement LastNameInput = driver.findElement(By.id("AccountFrm_lastname"));
		LastNameInput.sendKeys(UserLastName);

		WebElement EmailInput = driver.findElement(By.id("AccountFrm_email"));
		EmailInput.sendKeys(Email);

		WebElement AddressInput = driver.findElement(By.id("AccountFrm_address_1"));
		AddressInput.sendKeys("amman city - tlaa al ali");

		WebElement CityInput = driver.findElement(By.id("AccountFrm_city"));
		CityInput.sendKeys("capital city");

		WebElement PostalCodeInput = driver.findElement(By.id("AccountFrm_postcode"));
		PostalCodeInput.sendKeys("13310");

		WebElement CountryInput = driver.findElement(By.id("AccountFrm_country_id"));
		Select Selector = new Select(CountryInput);
		int randomCountry = rand.nextInt(1, 240);
		Selector.selectByIndex(randomCountry);

		Thread.sleep(1000);

		WebElement ZoneIdInput = driver.findElement(By.id("AccountFrm_zone_id"));
		Select Selector2 = new Select(ZoneIdInput);
		int randomState = rand.nextInt(1, 6);
		Selector2.selectByIndex(randomState);

		WebElement LoginInput = driver.findElement(By.id("AccountFrm_loginname"));
		String LocalUserName = UserFirstName + UserLastName + RandomNumberForTheEmail;
		LoginInput.sendKeys(LocalUserName);
		GlobalUserNameForTheLogin = LocalUserName;

		WebElement PasswordInput = driver.findElement(By.id("AccountFrm_password"));
		PasswordInput.sendKeys(Password);

		WebElement ConfirmPasswordInput = driver.findElement(By.id("AccountFrm_confirm"));
		ConfirmPasswordInput.sendKeys(Password);

		WebElement AgreeCheckBox = driver.findElement(By.id("AccountFrm_agree"));
		AgreeCheckBox.click();

		WebElement ContinueButton = driver.findElement(By.xpath("//button[@title='Continue']"));
		ContinueButton.click();

	}

	@Test(priority = 2)
	public void Logout() throws InterruptedException {

		Thread.sleep(2000);
		
		WebElement UserNav = driver.findElement(By.id("customernav"));
		
		Actions action = new Actions(driver);
		
		action.moveToElement(UserNav).perform();
		
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Not "+GlobalUserName+"? Logoff")).click();
		
		//String LogoutURL = "https://automationteststore.com/index.php?rt=account/logout";

		//driver.get(LogoutURL);
	}
	
	@Test(priority = 3)
	public void Login () {
		
		driver.findElement(By.linkText("Login or register")).click();
		
		WebElement LoginInput = driver.findElement(By.id("loginFrm_loginname"));
		LoginInput.sendKeys(GlobalUserNameForTheLogin);
		
		WebElement PasswordInput = driver.findElement(By.id("loginFrm_password"));
		PasswordInput.sendKeys(Password);
		
		WebElement LoginButton = driver.findElement(By.xpath("//button[@title='Login']"));
		LoginButton.click();
	}
	
	@Test(priority = 4)
	public void AddItemToThecart() throws InterruptedException {
		
		String [] WebSitesForTheItems = {
			"https://automationteststore.com/index.php?rt=product/category&path=68",
			"https://automationteststore.com/index.php?rt=product/category&path=36",
			"https://automationteststore.com/index.php?rt=product/category&path=43",
			"https://automationteststore.com/index.php?rt=product/category&path=49",
			"https://automationteststore.com/index.php?rt=product/category&path=58",
			"https://automationteststore.com/index.php?rt=product/category&path=52",
			"https://automationteststore.com/index.php?rt=product/category&path=65"
		};
		
		int randomIndex = rand.nextInt(WebSitesForTheItems.length);
		
		driver.get(WebSitesForTheItems[randomIndex]);
		
		WebElement ListOfItem = driver.findElement(By.cssSelector(".thumbnails.row"));
		
		int ToltalNumberOfItems = ListOfItem.findElements(By.tagName("li")).size();
		
		int RandomIndexForTheItem = rand.nextInt(ToltalNumberOfItems);
		
		Thread.sleep(3000);
		
		ListOfItem.findElements(By.tagName("li")).get(RandomIndexForTheItem).click();
		
		WebElement Container = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));
		
		int numberOfPRodects = Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).size();
		
		int RandomIndexTheProduct = rand.nextInt(numberOfPRodects);
		
		Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).get(RandomIndexTheProduct).click();
		
		WebElement ULList = driver.findElement(By.className("productpagecart"));
		
		int LiItem = ULList.findElements(By.tagName("li")).get(0).findElements(By.tagName("a")).size();
		
		if(LiItem>0) {
			
			driver.findElement(By.className("cart")).click();
			
			String ActualResult = driver.findElement(By.className("heading1")).getText();
			
			String ExpectedResult = "Shopping Cart";
			
			Assert.assertEquals(ActualResult,ExpectedResult.toUpperCase());
		}else {
			
			driver.get(MyWebSite);
			
			System.out.print("Sorry The Item Out Of The Stock !!!");
			
			String ExpectedResult = "https://automationteststore.com/";
			
			String ActualResult = driver.getCurrentUrl();
			
			Assert.assertEquals(ActualResult,ExpectedResult);
		}
		
	}

}
