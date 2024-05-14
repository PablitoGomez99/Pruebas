package Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import Objects.objectGoogle;

public class BaseGoogle {
	WebDriver driver;
	
	
	
	@BeforeMethod
	public void  setup () {
		
		System.setProperty("Webdriver.FirefoxDriver", "./src/test/resources/firefox/geckodriversa.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.gmail.com");

		
		

		
	}
	
	
	@SuppressWarnings("deprecation")
	@Test(priority=1)
	public void primerTest() throws InterruptedException {
		System.out.println("Iniciando primer test.....");
		objectGoogle validoUser = new objectGoogle (driver);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		validoUser.iniciarSesion();
		System.out.println("Prueba exitosa: Inicio de sesión exitoso.");
		
		
	}
	@Test(priority=2 , dependsOnMethods = "primerTest")
	public void segundoTest() throws InterruptedException {
		System.out.println("....Iniciando Segundo Test.....");
		objectGoogle invalidoPass = new objectGoogle(driver);
		
		invalidoPass.ingresoInvalidoPass();
		System.out.println("Prueba existosa :Inicio con contraseña invalida");
		
	}
	@SuppressWarnings("deprecation")
	@Test(priority=3 , dependsOnMethods = "segundoTest")
	public void tercerTest() {
		System.out.println("....Iniciando tercero Test.....");
		objectGoogle invalidoUser = new objectGoogle(driver);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		invalidoUser.ingresoInvalidoUser();
		System.out.println("Prueba existosa : Inicio con usuario incorrecto");
		
	}
	
	@SuppressWarnings("deprecation")
	@Test(priority=4 , dependsOnMethods = "tercerTest")
	public void cuartoTest() {
		System.out.println("....Iniciando cuarto Test.....");
		objectGoogle randomusuario = new objectGoogle(driver);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		randomusuario.randomUser();
		System.out.println("Prueba existosa: Usuario random");
		
	}
	
	@SuppressWarnings("deprecation")
	@Test(priority=5 , dependsOnMethods = "cuartoTest")
	public void quintoTest() {
		System.out.println("....Iniciando quinto Test.....");
		objectGoogle randomPassword = new objectGoogle(driver);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		randomPassword.randomPass();
		System.out.println("Prueba existosa: Contraseña random");
	}
	
	
	@AfterMethod
	 public void finalizacionDeTest() {
		
		  driver.quit();
	       
	}

}
