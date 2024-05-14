package Objects;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class objectEbay {
	WebDriver driver;
	By buscador = By.xpath("//input[@id='gh-ac']");
	By primerElemento = By.xpath("//body/div[5]/div[4]/div[3]/div[1]/div[2]/ul[1]/li[2]/div[1]/div[1]/div[1]/a[1]/div[1]/img[1]");
	By precioConsola = By.xpath("//div[@class='x-price-primary' and @data-testid='x-price-primary']");
			 
	public objectEbay (WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	
	
	public void buscar() throws InterruptedException {
		driver.findElement(buscador).sendKeys("Electric Guitar");
		driver.findElement(buscador).submit();
		Thread.sleep(5000);
		driver.findElement(primerElemento).click();
		Thread.sleep(5000);
		
		Set<String> ventanas = driver.getWindowHandles();
		driver.switchTo().window(ventanas.toArray()[1].toString());
		System.out.println("El precio del producto es: " + driver.findElement(precioConsola).getText());
		

	     
	}
	

}


