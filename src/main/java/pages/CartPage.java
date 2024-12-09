package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    // Localizadores
    private By productDescription = By.className("cart_item");
    private By checkoutButton = By.id("checkout");
    private By removeButton = By.className("btn_secondary");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Método para verificar que el producto agregado esté presente
    public boolean isProductInCart(String productName) {
        List<WebElement> products = driver.findElements(productDescription);
        for (WebElement product : products) {
            if (product.getText().contains(productName)) {
                return true; // El producto está en el carrito
            }
        }
        return false; // El producto no está en el carrito
    }

    // Método para verificar que el botón de checkout esté presente
    public boolean isCheckoutButtonPresent() {
        WebElement button = driver.findElement(checkoutButton);
        return button.isDisplayed(); // Devuelve true si el botón es visible
    }

    // Método para eliminar un producto del carrito
    public void removeProductFromCart(String productName) {
        List<WebElement> products = driver.findElements(productDescription);
        for (WebElement product : products) {
            if (product.getText().contains(productName)) {
                // Encuentra el botón de eliminar correspondiente
                WebElement removeBtn = product.findElement(removeButton);
                removeBtn.click(); // Haz clic en el botón de eliminar
                break; // Sal del bucle después de eliminar el producto
            }
        }
    }

    // Método para verificar que la lista de productos en el carrito está actualizada
    public boolean isCartUpdated(List<String> expectedProducts) {
        List<WebElement> products = driver.findElements(productDescription);
        List<String> actualProducts = new ArrayList<>();

        for (WebElement product : products) {
            actualProducts.add(product.getText());
        }

        // Compara la lista actual con la lista esperada
        return actualProducts.containsAll(expectedProducts) && expectedProducts.containsAll(actualProducts);
    }

    // Método para verificar que el carrito esté vacío
    public boolean isCartEmpty() {
        List<WebElement> products = driver.findElements(productDescription);
        return products.isEmpty(); // Devuelve true si la lista de productos está vacía
    }

    // Método para hacer clic en el botón de checkout
    public void clickCheckout() {
        WebElement button = driver.findElement(checkoutButton);
        button.click(); // Haz clic en el botón de checkout
    }
}
