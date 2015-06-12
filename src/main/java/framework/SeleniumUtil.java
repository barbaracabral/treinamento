package framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebConsole.Logger;
import com.thoughtworks.selenium.Selenium;

/**
 * 
 * author Hézio D. Fernandes <br>
 * Neoway - 12/06/2015 <br>
 * <a href="mailto:fernandes.bcc@gmail.com">fernandes.bcc@gmail.com</a>
 *
 */

public class SeleniumUtil {

	public static WebDriver driver;
	private static WebDriverWait wait;

	public Selenium selenium;

	public Logger logger;

	public SeleniumUtil(WebDriver driver) {
		wait = new WebDriverWait(driver, 10);
		SeleniumUtil.driver = driver;
	}

	public static void doubleClickElement(WebElement elemento) throws Exception {
		Actions action = new Actions(driver);
		Actions doubleClick = action.doubleClick(elemento);
		doubleClick.perform();
		Thread.sleep(1000L);
	}

	public static boolean validFindElementCSSAndText(String css,
			String nomeDoTexto) throws Exception {
		List<WebElement> elements = driver.findElements(By.cssSelector(css));
		for (WebElement element : elements) {
			String text = element.getText();
			if (nomeDoTexto.equalsIgnoreCase(text)) {
				return true;
			}
		}
		return false;
	}

	public static boolean validFindElementClassAndText(String className,
			String nomeDoTexto) throws Exception {
		aguardaElementoVisivelPorClassName(className);
		List<WebElement> elements = driver
				.findElements(By.className(className));
		for (WebElement element : elements) {
			String text = element.getText();
			if (nomeDoTexto.equalsIgnoreCase(text)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Método que verifica elemento existente na interface
	 * 
	 * @param Css
	 *            atributo passado por parametro que irá ser verificado
	 * @return retorna true se o elemento for encontrado.
	 * @throws Exception
	 */
	public static boolean validFindElementCSS(String css) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.cssSelector(css)));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean validFindElementClassName(String className) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.className(className)));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Método que verifica elemento existente na interface
	 * 
	 * @param Id
	 *            atributo passado por parametro que irá ser verificado
	 * @return retorna true se o elemento for encontrado.
	 * @throws Exception
	 */
	public static boolean validFindElementID(String Id) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean validFindElementName(String name) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.name(name)));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void doubleClickElementFindCSSAndText(String css, String nomeDoTexto)
			throws Throwable {
		List<WebElement> buttons = driver.findElements(By.cssSelector(css));
		for (WebElement button : buttons) {
			String text = button.getText();
			if (nomeDoTexto.equals(text)) {
				doubleClickElement(button);
				return;
			}
		}
	}

	public static void pressionaTeclaEnter() {
		new Actions(driver).sendKeys(Keys.ENTER).build().perform();
	}

	public static void aguardaElementoVisivelPorID(String Id) {
		try {
			while (!validFindElementID(Id)) {
				boolean elemento = validFindElementID(Id);
				if (elemento) {
					return;
				}
				System.out.println("Aguardando Elemento " + Id);
			}
		} catch (Exception e) {
			throw new ElementNotFoundException(
					"Elemento por ID não encontrado", "",
					"Elemento por ID não encontrado!");
		}
	}

	public static void aguardaElementoVisivelPorName(String name) {
		try {
			Boolean element = validFindElementName(name);
			while (!element) {
				boolean elemento = validFindElementName(name);
				if (elemento) {
					return;
				}
				System.out.println("Aguardando Elemento " + name);
			}
		} catch (Exception e) {
			throw new ElementNotFoundException(
					"Elemento por name não encontrado", "",
					"Elemento por name não encontrado!");
		}
	}

	public static void aguardaElementoVisivelPorCss(String css) {
		try {
			Boolean element = validFindElementCSS(css);
			while (!element) {
				boolean elemento = validFindElementCSS(css);
				if (elemento) {
					return;
				}
				System.out.println("Aguardando Elemento " + css);
			}
		} catch (Exception e) {
			throw new ElementNotFoundException(
					"Elemento por Css não encontrado", "",
					"Elemento por Css não encontrado!");
		}
	}

	public static void aguardaElementoVisivelPorClassName(String className) {
		try {
			while (!validFindElementClassName(className)) {
				boolean elemento = validFindElementClassName(className);
				if (elemento) {
					return;
				}
				System.out.println("Aguardando Elemento " + className);
			}
		} catch (Exception e) {
			throw new ElementNotFoundException(
					"Elemento por className não encontrado", "",
					"Elemento por className não encontrado!");
		}
	}
}