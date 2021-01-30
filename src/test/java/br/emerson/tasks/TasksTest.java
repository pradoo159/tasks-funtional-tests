package br.emerson.tasks;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	private String urlBase = "http://localhost:8001/tasks/";

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to(urlBase);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
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
	public void naoDeveSalvarTarefaSemDescricao() {
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
	public void naoDeveSalvarTarefaSemData() {
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
	public void naoDeveSalvarTarefaComDataPassada() {
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
