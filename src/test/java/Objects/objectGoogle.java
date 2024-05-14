package Objects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class objectGoogle {
	WebDriver driver;
	
	By botonIniciarSesion = By.xpath("//a[contains(text(),'Inicia sesión')]");
	By campoIniciarSesion = By.xpath("//input[@id='identifierId']");
	By botonSiguiente = By.xpath("//span[contains(text(),'Siguiente')]");
	By campoPass = By.name("Passwd");
	By validarPerfil = By.xpath("//header/div[2]/div[3]/div[1]/div[2]/div[1]/a[1]");
	By perfil = By.xpath("//header/div[2]/div[3]/div[1]/div[2]/div[1]/a[1]");
	By error = By.xpath("//body/div[1]/div[1]/div[2]/c-wiz[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]");
	By errorPass = By.xpath("//body/div[1]/div[1]/div[2]/c-wiz[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/span[1]/section[2]/div[1]/div[1]/div[1]/div[2]");
	
	
	public objectGoogle(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void  credencialesValidas() throws InterruptedException {
		
			driver.findElement(campoIniciarSesion).sendKeys("pruebalamansys@gmail.com");
			driver.findElement(botonSiguiente).click();
			Thread.sleep(3000);

			driver.findElement(campoPass).sendKeys("12345678Prueba");
			driver.findElement(botonSiguiente).click();
			



		
	}
	
	public void iniciarSesion() throws InterruptedException {
		
		
			 
			 credencialesValidas();
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			 if(driver.findElement(validarPerfil).isDisplayed()) {
				 
				 	
					System.out.println("Se valido el perfil de la cuenta de gmail");
				}else {
					System.out.println("El elemento no está presente y tampoco visible en la página.");
				}

			 
			

			
		
		
		
	}
	
	public void credencialesInvalidasPass() throws InterruptedException {
		
		
			driver.findElement(campoIniciarSesion).sendKeys("pruebalamansys@gmail.com");
			driver.findElement(botonSiguiente).click();
			Thread.sleep(3000);
			driver.findElement(campoPass).sendKeys("12345678Pruebass");
			driver.findElement(botonSiguiente).click();
			driver.findElement(errorPass).isDisplayed();
			

		
	}
	
	public void ingresoInvalidoPass() throws InterruptedException {
		credencialesInvalidasPass();
	}
	
	public void ingresoInvalidoUser() {
		
		try {
			driver.findElement(campoIniciarSesion).sendKeys("pruebalamansys@gmails.com");
			driver.findElement(botonSiguiente).click();
			Thread.sleep(3000);
			driver.findElement(error).isDisplayed();

		}catch(Exception e){
			System.out.println("El user fue correcto.");
			

		}


	
		
	
	}
	
	public String listaRandomUser() {
		  List<String> usuarios = new ArrayList<>();
		  usuarios.add("pruebalamansys@gmail.com");
		  usuarios.add("pruebalamansys@gmail.com");
		  usuarios.add("pruebalamansysssss@gmail.com");
		  usuarios.add("pruebalamansy1231231232s@gmail.com");

	        
	        Random rand = new Random();
	        String UserAleatorio = usuarios.get(rand.nextInt(usuarios.size()));
	        System.out.println("El user seleccionado es : " + UserAleatorio);
	    ;
			
	        return UserAleatorio;
	}
	
	public String listarandomPass() {
		 List<String> contraseña = new ArrayList<>();
	        contraseña.add("1jsakdjas");
	        contraseña.add("fafaffsafsafas");
	        contraseña.add("12345678Pruebas");
	        contraseña.add("12345678Prueba");
	        
	        Random rand = new Random();
	        String passAleatorio = contraseña.get(rand.nextInt(contraseña.size()));
	        System.out.println("La contraseña seleccionada es : " + passAleatorio);
	        
	        return passAleatorio;
	}
	
	public void randomUser() {
		String UserAleatorios = listaRandomUser();
		
		try {
			driver.findElement(campoIniciarSesion).sendKeys(UserAleatorios);
			driver.findElement(botonSiguiente).click();
			driver.findElement(error).isDisplayed();
		}catch(Exception e){
			System.out.println("El user fue correcto.");
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			System.out.println("por ingresar la contraseña correcta.");
			driver.findElement(campoPass).sendKeys("12345678Prueba");
			driver.findElement(botonSiguiente).click();
			

		}
		
	}
	
	public void randomPass() {
		String passRandom =	listarandomPass();
		try {
			
			driver.findElement(campoIniciarSesion).sendKeys("pruebalamansys@gmail.com");
			driver.findElement(botonSiguiente).click();
			
			Thread.sleep(4000);
			driver.findElement(campoPass).sendKeys(passRandom);
			driver.findElement(botonSiguiente).click();
			driver.findElement(errorPass).isDisplayed();
			System.out.println("La pass fue incorrecta.");
			
			
		}catch(Exception e){
			
			System.out.println("La pass fue correcta.");
			
			

		}
		
	}
	
	
	
	
	
	

}
