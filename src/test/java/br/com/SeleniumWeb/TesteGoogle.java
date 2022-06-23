package br.com.SeleniumWeb;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {
	
	
	
	@Test
	public void teste() {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com.br");
		System.out.println(driver.getTitle());
		assertEquals("Google", driver.getTitle());
		driver.quit();
		
			}

	
	
	
}
