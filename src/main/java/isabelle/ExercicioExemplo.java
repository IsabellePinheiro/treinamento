package isabelle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.BuscaConfig;
import main.Delay;
import main.Exercicio;
import main.IntervaloDeBusca;

public class ExercicioExemplo implements Exercicio {

	public static void main(String[] args) {
		ExercicioExemplo ex = new ExercicioExemplo();

		 String wikiResume = ex.getWikiResume("new york");
		 System.out.println(wikiResume);

		// ArrayList<String> ar = (ArrayList<String>) ex.getUrls("new york");
		// System.out.println(ar);

		// ArrayList<String> ar = (ArrayList<String>) ex.getUrls("new york", 2);
		// System.out.println(ar);

		/*BuscaConfig busca = new BuscaConfig();
		busca.setTermo("new york");

		IntervaloDeBusca intervalo = IntervaloDeBusca.NA_ULTIMA_HORA;

		busca.setIntervalo(intervalo);

		busca.setPagina(3);
		ArrayList<String> ar = (ArrayList<String>) ex.getUrls(busca);
		System.out.println(ar);*/
	}

	public long getNumeroAproximadoDoResultadoDaBuscaPor(String termo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<String> getUrls(String termo) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver webdriver = new ChromeDriver(options);
		webdriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		webdriver.get("https://www.google.com.br/");

		Delay.of(1000);
		termo = "new york";
		webdriver.findElement(By.id("lst-ib")).sendKeys(termo);
		Delay.of(1000);

		webdriver.findElement(By.id("_fZl")).click();
		Delay.of(5000);

		List<WebElement> url = webdriver.findElements(By.xpath(".//div/h3/a"));
		List<String> result = new ArrayList<String>();
		for (WebElement element : url) {

			result.add(element.getAttribute("href"));
		}

		Delay.of(5000);
		webdriver.close();
		webdriver.quit();
		return result;
	}

	public List<String> getUrls(String termo, int pag) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver webdriver = new ChromeDriver(options);
		webdriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		webdriver.get("https://www.google.com.br/");

		Delay.of(1000);
		termo = "new york";
		webdriver.findElement(By.id("lst-ib")).sendKeys(termo);
		Delay.of(1000);

		webdriver.findElement(By.id("_fZl")).click();
		Delay.of(5000);

		pag = 2;

		webdriver.findElement(By.xpath(".//a[contains(@aria-label, 'Page " + pag + "')]")).click();

		Delay.of(5000);

		List<WebElement> url = webdriver.findElements(By.xpath(".//div/h3/a"));
		List<String> result = new ArrayList<String>();
		for (WebElement element : url) {

			result.add(element.getAttribute("href"));
		}

		Delay.of(5000);
		webdriver.close();
		webdriver.quit();
		return result;
	}

	public List<String> getUrls(BuscaConfig config) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver webdriver = new ChromeDriver(options);
		webdriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		webdriver.get("https://www.google.com.br/");

		Delay.of(1000);
		BuscaConfig busca = new BuscaConfig();

		busca.setTermo("new york");
		webdriver.findElement(By.id("lst-ib")).sendKeys(busca.getTermo());
		Delay.of(1000);

		webdriver.findElement(By.id("_fZl")).click();
		Delay.of(5000);

		// IntervaloDeBusca intervalo = IntervaloDeBusca.NA_ULTIMA_HORA;

		// busca.setIntervalo(intervalo);

		webdriver.findElement(By.id("hdtb-tls")).click();

		Delay.of(2000);

		webdriver.findElement(By.xpath(".//div[contains(@aria-label, 'Em qualquer data')]")).click();

		webdriver.findElement(By.id("qdr_d")).click();

		Delay.of(5000);

		busca.setPagina(3);

		webdriver.findElement(By.xpath(".//a[contains(@aria-label, 'Page " + busca.getPagina() + "')]")).click();

		Delay.of(5000);

		List<WebElement> url = webdriver.findElements(By.xpath(".//div/h3/a"));
		List<String> result = new ArrayList<String>();
		for (WebElement element : url) {

			result.add(element.getAttribute("href"));
		}

		Delay.of(5000);
		webdriver.close();
		webdriver.quit();
		return result;

	}

	public String getWikiResume(String termo) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver webdriver = new ChromeDriver(options);
		webdriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		webdriver.get("https://www.google.com.br/");

		Delay.of(1000);
		termo = "new york";
		webdriver.findElement(By.id("lst-ib")).sendKeys(termo);
		Delay.of(1000);
		explicitWait(webdriver, "new york");

		webdriver.findElement(By.id("_fZl")).click();
		Delay.of(5000);

		String resume;

		resume = webdriver.findElement(By.xpath(".//div[contains(@class, 'kno-rdesc')]")).getText();

		webdriver.close();
		webdriver.quit();
		return resume;
	}

	public static void explicitWait(WebDriver driver, String text) {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		WebElement hello1 = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(text)));

	}

}
