package br.com.SeleniumWeb;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
	
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
	public void deveRealizarCadastroComSucesso() throws InterruptedException {
		    		
			TimeUnit.SECONDS.sleep(1);
			driver.findElement(By.id("elementosForm:nome")).sendKeys("Yamaguche");
			TimeUnit.SECONDS.sleep(1);
			driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Castro");
			TimeUnit.SECONDS.sleep(1);
			driver.findElement(By.id("elementosForm:sexo:0")).click();
			TimeUnit.SECONDS.sleep(1);
			driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
			TimeUnit.SECONDS.sleep(1);
			new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Mestrado");
			new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");
			TimeUnit.SECONDS.sleep(1);
			driver.findElement(By.id("elementosForm:cadastrar")).click();
			
			TimeUnit.SECONDS.sleep(1);
			Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
			TimeUnit.SECONDS.sleep(1);
			Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Yamaguche"));
			Assert.assertEquals("Sobrenome: Castro", driver.findElement(By.id("descSobrenome")).getText());
			Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
			Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
			Assert.assertEquals("Escolaridade: mestrado", driver.findElement(By.id("descEscolaridade")).getText());
			Assert.assertEquals("Esportes: Natacao", driver.findElement(By.id("descEsportes")).getText());
			TimeUnit.SECONDS.sleep(1);
			
			
	}

	@Test
	public void deveValidarNomeObrigatorio() throws InterruptedException {
			
			TimeUnit.SECONDS.sleep(1);
			driver.findElement(By.id("elementosForm:cadastrar")).click();
			Alert alert = driver.switchTo().alert();
			Assert.assertEquals("Nome eh obrigatorio", alert.getText());
			TimeUnit.SECONDS.sleep(1);
			
			
			
			
			
	}
	@Test
	public void deveValidarSobrenomeObrigatorio() throws InterruptedException {
		
		TimeUnit.SECONDS.sleep(1);
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Yamaguche");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		TimeUnit.SECONDS.sleep(1);
		
	}
	@Test
	public void deveValidarSexoObrigatorio() throws InterruptedException {
		
		TimeUnit.SECONDS.sleep(1);
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Yamaguche");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Castro");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		TimeUnit.SECONDS.sleep(1);
		
	}
	@Test
	public void deveValidarComidaObrigatorio() throws InterruptedException {
		
		TimeUnit.SECONDS.sleep(1);
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Yamaguche");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Castro");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		TimeUnit.SECONDS.sleep(1);
		
	}
	
	@Test
	public void deveValidarEsportistaIndeciso() throws InterruptedException {
		
		TimeUnit.SECONDS.sleep(1);
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Yamaguche");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Castro");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		TimeUnit.SECONDS.sleep(1);
		
	}
	
	
}
