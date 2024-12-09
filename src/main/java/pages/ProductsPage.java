package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {
    private WebDriver driver;

    // Localizadores
    private By productList = By.className("inventory_item");
    private By cartLogo = By.className("shopping_cart_link");
    private By addToCartButton = By.className("add-to-cart-sauce-labs-bike-light");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Método para verificar que el listado de productos esté presente
    public boolean isProductListPresent() {
        List<WebElement> products = driver.findElements(productList);
        return !products.isEmpty();
    }

    // Método para verificar que el logo del carrito esté presente
    public boolean isCartLogoPresent() {
        WebElement cart = driver.findElement(cartLogo);
        return cart.isDisplayed();
    }

    // Método para verificar que el botón "Add to Cart" esté presente en cada ítem
    public boolean areAddToCartButtonsPresent() {
        List<WebElement> products = driver.findElements(productList);
        for (WebElement product : products) {
            List<WebElement> buttons = product.findElements(addToCartButton);
            if (buttons.isEmpty()) {
                return false; // Si algún producto no tiene botón, devuelve false
            }
        }
        return true; // Todos los productos tienen botón
    }
}