package br.emerson.tasks;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {

	private String urlBase = "http://192.168.25.171:8001/tasks/";

	public WebDriver acessarAplicacao() throws MalformedURLException {
//		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.25.171:4444/wd/hub"), cap);
		driver.navigate().to(urlBase);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();

		try {

			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
			driver.findElement(By.name("task")).sendKeys("Teste via Front");

			// escrever a data
			driver.findElement(By.name("dueDate")).sendKeys("13/12/2021");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", mensagem);
			
			// fechar o driver
		} finally {
			
			driver.quit();
		}

	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();

		try {

			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
			driver.findElement(By.name("task")).sendKeys("");

			// escrever a data
			driver.findElement(By.name("dueDate")).sendKeys("13/12/2021");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", mensagem);
			
			// fechar o driver
		} finally {
			
			driver.quit();
		}

	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();

		try {

			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
			driver.findElement(By.name("task")).sendKeys("Teste via Front");

			// escrever a data
			driver.findElement(By.name("dueDate")).sendKeys("");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", mensagem);
			
			// fechar o driver
		} finally {
			
			driver.quit();
		}

	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();

		try {

			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
			driver.findElement(By.name("task")).sendKeys("Teste via Front");

			// escrever a data
			driver.findElement(By.name("dueDate")).sendKeys("13/12/2010");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", mensagem);
			
			// fechar o driver
		} finally {
			
			driver.quit();
		}

	}

}
