package br.com.SeleniumWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DSL {
	
	WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	
	public void escreve(String id_campo, String texto) {
		driver.findElement(By.id("id_campo")).sendKeys(texto);
	}

}
