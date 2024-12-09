package com.example.swaglabs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.*;
import org.junit.After;
import utils.Utils;

public class SwagLabsTests {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage2 checkoutPage2;
    CheckoutPage3 checkoutPage3;
    CheckoutPage1 checkoutPage1;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Soporte\\Downloads\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage1 = new CheckoutPage1(driver);
        checkoutPage2 = new CheckoutPage2(driver);
        //checkoutPage3 = new CheckoutPage3(driver);
    }

    // Caso de prueba 1
    @Test
    public void verificarInicioDeSesionExitoso() {
        // Login
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

    // Caso de prueba 2
    @Test
    public void verificarAgregarProductoAlCarrito() {
        // Login
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // Agregar un producto al carrito
        productsPage.addToCart("backpack");

        // Ver el carrito
        productsPage.clickCart();

        // Verificar que el producto está en el carrito
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"));

        // Verificar que se ve el botón de "Checkout"
        Assert.assertTrue(cartPage.isCheckoutButtonPresent());

        // Sacar una captura de pantalla
        Utils.takeScreenshot(driver, "verificarAgregarProductoAlCarrito");
    }

    // Caso de prueba 3
    @Test
    public void verificarEliminarProductoDelCarrito() {
        // Login
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // Agregar un producto al carrito
        productsPage.addToCart("backpack");

        // Ver el carrito
        productsPage.clickCart();

        // Eliminar el producto del carrito
        cartPage.removeProductFromCart("Sauce Labs Backpack");

        // Verificar que el producto ya no está en el carrito
        Assert.assertFalse(cartPage.isProductInCart("Sauce Labs Backpack"));

        // Verificar que el botón de "Checkout" está presente
        Assert.assertTrue(cartPage.isCheckoutButtonPresent());

        // Sacar una captura de pantalla
        Utils.takeScreenshot(driver, "verificarEliminarProductoDelCarrito");
    }

    // Caso de prueba 4
    @Test
    public void verificarCheckout1() {
        // Login
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // Agregar un producto al carrito
        productsPage.addToCart("backpack");

        // Ver el carrito
        productsPage.clickCart();

        // Hacer clic en el botón de "Checkout"
        cartPage.clickCheckout();

        // Verificar que el campo de nombre está presente
        Assert.assertTrue(checkoutPage1.isFirstNameFieldPresent());

        // Verificar que el campo de apellido está presente
        Assert.assertTrue(checkoutPage1.isLastNameFieldPresent());

        // Verificar que el campo de código postal está presente
        Assert.assertTrue(checkoutPage1.isPostalCodeFieldPresent());

        // Sacar una captura de pantalla
        Utils.takeScreenshot(driver, "verificarCheckout1");
    }

    // Caso de prueba 5
    @Test
    public void verificarCheckout2() {
        // Login
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // Ir al carrito
        productsPage.clickCart();

        // Hacer clic en el botón de checkout
        cartPage.clickCheckout();

        // Ingresar los datos de envío
        checkoutPage1.enterFirstName("John");
        checkoutPage1.enterLastName("Doe");
        checkoutPage1.enterPostalCode("12345");
        checkoutPage1.clickContinue();

        // Verificar que el resumen de la compra está presente
        Assert.assertTrue(checkoutPage2.isShippingInformationPresent());
        
        // Verificar que el botón de finalizar está presente
        Assert.assertTrue(checkoutPage2.isFinishButtonPresent());

        // Sacar una captura de pantalla
        Utils.takeScreenshot(driver, "verificarCheckout2");
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
