package com.example.swaglabs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPage;
import org.junit.After;
import pages.ProductsPage;
import utils.Utils;

public class SwagLabsTests {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Soporte\\Downloads\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    // Caso de prueba 1
    @Test
    public void verificarInicioDeSesionExitoso() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // Verificar que se vean los productos en la página de productos
        Assert.assertTrue(productsPage.isProductListPresent());

        // Verificar que se todos los productos tengan el botón de "Add to cart"
        Assert.assertTrue(productsPage.areAddToCartButtonsPresent());

        // Sacar una captura de pantalla
        Utils.takeScreenshot(driver, "verificarInicioDeSesionExitoso");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
