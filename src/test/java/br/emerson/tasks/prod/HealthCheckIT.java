package br.emerson.tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {
	
	private String urlBase = "http://192.168.25.171:9999/tasks/";
	
	@Test
	public void healthCheck() throws MalformedURLException {
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.25.171:4444/wd/hub"), cap);
		driver.navigate().to(urlBase);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			String version = driver.findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));
		} finally {
			driver.quit();
		}
		
		
	}

}
