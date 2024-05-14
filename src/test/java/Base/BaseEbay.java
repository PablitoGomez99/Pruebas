package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Objects.objectEbay;



public class BaseEbay {
	WebDriver driver;
	
	@BeforeMethod
	public void  setup () {
		
		System.setProperty("Webdriver.FirefoxDriver", "./src/test/resources/firefox/geckodriversa.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.ebay.com");

		
		

		
	}
	
	@Test
	public void ebayProduct() throws InterruptedException {
		System.out.println("....Iniciando el test .....");
		objectEbay ebay = new objectEbay (driver);
		ebay.buscar();
		System.out.println("....Prueba existosa: Buscar producto.....");
	}
	
	@AfterMethod
	 public void finalizacionDeTest() {
		
		  driver.quit();
		  System.out.println("....Se termino la prueba.....");
	       
	}

}
