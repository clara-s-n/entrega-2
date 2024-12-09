package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static WebDriver getDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver",
                        "C:\\Users\\nicor\\eclipe-workspace\\Testing_UCU\\drivers\\chromedriver.exe"); // Especifica la
                                                                                                       // ruta al
                                                                                                       // chromedriver
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                // chromeOptions.addArguments("--headless"); // Ejecutar en modo headless si es
                // necesario
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver",
                        "C:\\Users\\Soporte\\Downloads\\geckodriver\\geckodriver.exe"); // Especifica la ruta al
                                                                                        // geckodriver
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--remote-allow-origins=*");
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.addArguments("--disable-dev-shm-usage");
                // firefoxOptions.addArguments("--headless"); // Ejecutar en modo headless si es
                // necesario
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver",
                        "C:\\Users\\nicor\\eclipe-workspace\\Testing_UCU\\drivers\\msedgedriver.exe"); // Especifica la
                                                                                                       // ruta al
                                                                                                       // edgedriver
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        return driver;
    }
}
