package br.com.SeleniumWeb;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTrein {

	WebDriver driver;
	DSL dsl;

	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "./Pages/componentes.html");
	    dsl = new DSL(driver);
	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void testeTextField() {
        //dsl.escreve(null, null);  
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		Assert.assertEquals("Teste de falha", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

	}

	@Test
	public void DeveInteragirComTextArea() {

		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("TesteLinha");
		Assert.assertEquals("TesteLinha", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

	}

	@Test
	public void DeveInteragirComRadioButton() {

		driver.findElement(By.id("elementosForm:sexo:1")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:1")).isSelected());

	}

	@Test
	public void DeveInteragirComCheckBox() {

		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

	}

	@Test
	public void DeveInteragirComCombo() {

		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		combo.selectByIndex(2);
		combo.selectByValue("superior");
		combo.selectByVisibleText("2o grau completo");
		Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());

	}

	@Test
	public void DeveVerificarValorescombo() {

		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());

		boolean encontrou = false;
		for (WebElement option : options) {
			if (option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);

	}

	@Test
	public void DeveVerificarValoresComboMultiplos() {

		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");

		combo.deselectByVisibleText("Corrida");
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());

	}

	@Test
	public void DeveInteragirComBotoes() {

		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();

		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));

	}

	@Test
	public void DeveInteragirComLinks() {

		driver.findElement(By.linkText("Voltar")).click();

		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());

	}

	@Test
	public void DeveBuscarTextosNaPagina() {

		driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
				driver.findElement(By.className("facilAchar")).getText());

	}

}
