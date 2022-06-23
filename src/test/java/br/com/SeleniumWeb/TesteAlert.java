package br.com.SeleniumWeb;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteAlert {
	
	WebDriver driver;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,765));
		driver.get("file:///" + System.getProperty("user.dir") + "./Pages/componentes.html");
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	
	@Test
	public void DeveInteragirComAlert() {
			
			driver.findElement(By.id("Alert")).click();
			Alert alert = driver.switchTo().alert();
			String texto = alert.getText();
			Assert.assertEquals("Alert Simples", texto);
			alert.accept();
			driver.findElement(By.id("elementosform:nome")).sendKeys(texto);	
			
			}
	
	@Test
	public void DeveInteragirComAlertConfirme() {
			
			driver.findElement(By.id("confirm")).click();
			Alert alert = driver.switchTo().alert();
			Assert.assertEquals("Confirm Simples", alert.getText());
			alert.accept();
			Assert.assertEquals("Confirmado", alert.getText());
			alert.accept();
			
			driver.findElement(By.id("confirm")).click();
			Assert.assertEquals("Confirm Simples", alert.getText());
			alert.dismiss();
			Assert.assertEquals("Negado", alert.getText());
			alert.dismiss();
			
			
			
	}
	
	@Test
	public void DeveInteragirComAlertPrompt() {
			
			driver.findElement(By.id("prompt")).click();
			Alert alert = driver.switchTo().alert();
			Assert.assertEquals("Digite um numero", alert.getText());
			alert.sendKeys("12");
			alert.accept();
			Assert.assertEquals("Era 12?", alert.getText());
			alert.accept();
			Assert.assertEquals(":D", alert.getText());
			alert.accept();
			
	
	}
	
	
	
	
	

}
