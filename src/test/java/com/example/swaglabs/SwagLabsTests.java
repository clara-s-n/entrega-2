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

    @Test
    public void testLogin() {
        // Aquí puedes agregar aserciones para verificar el resultado esperado
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
    }

    @Test
    public void verificarInicioDeSesionExitoso() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // Verificar que se vean los productos en la página de productos
        Assert.assertTrue(productsPage.isProductListPresent());

        // Verificar que se vea el botón del carrito de compras
        Assert.assertTrue(productsPage.isCartLogoPresent());

        // Sacar una captura de pantalla
        Utils.takeScreenshot(driver, "verificarInicioDeSesionExitoso");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
