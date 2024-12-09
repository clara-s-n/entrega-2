package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MetodosParaEncontrarElementos {
    public static void main(String[] args) {
        // Configura el WebDriver
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\nicor\\eclipe-workspace\\Testing_UCU\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Abre la página web
        driver.get("https://www.ejemplo.com");

        // Localiza elementos utilizando diferentes métodos
        WebElement elementById = driver.findElement(By.id("elementId"));
        System.out.println("Elemento encontrado por ID: " + elementById);

        WebElement elementByName = driver.findElement(By.name("elementName"));
        System.out.println("Elemento encontrado por Nombre: " + elementByName);

        WebElement elementByClassName = driver.findElement(By.className("className"));
        System.out.println("Elemento encontrado por Clase: " + elementByClassName);

        WebElement elementByTagName = driver.findElement(By.tagName("tagName"));
        System.out.println("Elemento encontrado por Etiqueta: " + elementByTagName);

        WebElement elementByLinkText = driver.findElement(By.linkText("linkText"));
        System.out.println("Elemento encontrado por Texto del Enlace: " + elementByLinkText);

        WebElement elementByPartialLinkText = driver.findElement(By.partialLinkText("partialLinkText"));
        System.out.println("Elemento encontrado por Texto Parcial del Enlace: " + elementByPartialLinkText);

        WebElement elementByCssSelector = driver.findElement(By.cssSelector("cssSelector"));
        System.out.println("Elemento encontrado por Selector CSS: " + elementByCssSelector);

        WebElement elementByXpath = driver.findElement(By.xpath("xpathExpression"));
        System.out.println("Elemento encontrado por XPath: " + elementByXpath);

        // Cierra el navegador
        driver.quit();
    }
}
