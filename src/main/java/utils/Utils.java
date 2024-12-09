package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
//import pages.GoogleHomePage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {

    // Ruta para guardar las capturas de pantalla
    private static final String SCREENSHOT_PATH = "C:\\Users\\Soporte\\OneDrive\\Desktop\\Reto II\\Mini desafio 2\\screenshots";

    // Método para redimensionar la pantalla
    public static void resizeWindow(WebDriver driver, int width, int height) {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
    }

    // Método para maximizar la pantalla
    public static void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    // Método para tomar capturas de pantalla
    public static void takeScreenshot(WebDriver driver, String testCaseName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath = SCREENSHOT_PATH + testCaseName + "_" + timestamp + ".png";
        try {
            FileHandler.copy(screenshot, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para manejar los resultados de búsqueda
    public static void handleSearchResults(List<WebElement> resultTitles) {
        for (WebElement title : resultTitles) {
            System.out.println(title.getText());
        }
    }

    // Método para realizar la búsqueda en Google
    /*
     * public static void searchInGoogle(WebDriver driver, String query) {
     * GoogleHomePage homePage = new GoogleHomePage(driver);
     * homePage.searchFor(query);
     * }
     */

    // Método para obtener los resultados de búsqueda en Google
    public static List<WebElement> getGoogleSearchResults(WebDriver driver) {
        List<WebElement> resultTitles = driver.findElements(By.cssSelector("h3"));
        handleSearchResults(resultTitles);
        return resultTitles;
    }

    // Método para cerrar el navegador
    public static void quitBrowser(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
