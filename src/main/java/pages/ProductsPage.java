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
    private By addToCartButtonBackpack = By.cssSelector("#add-to-cart-sauce-labs-backpack");
    private By addToCartButtonBikeLight = By.cssSelector("#add-to-cart-sauce-labs-bike-light");
    private By addToCartButtonTShirt = By.cssSelector("#add-to-cart-sauce-labs-bolt-t-shirt");
    private By addToCartButtonJacket = By.cssSelector("#add-to-cart-sauce-labs-fleece-jacket");
    private By addToCartButtonOnesie = By.cssSelector("#add-to-cart-sauce-labs-onesie");
    private By addToCartButtonRedTShirt = By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div/div[6]/div[2]/div[2]/button");

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
        WebElement addToCartBackpack = driver.findElement(addToCartButtonBackpack);
        WebElement addToCartBikeLight = driver.findElement(addToCartButtonBikeLight);
        WebElement addToCartTShirt = driver.findElement(addToCartButtonTShirt);
        WebElement addToCartJacket = driver.findElement(addToCartButtonJacket);
        WebElement addToCartOnesie = driver.findElement(addToCartButtonOnesie);
        WebElement addToCartRedTShirt = driver.findElement(addToCartButtonRedTShirt);

        return addToCartBackpack.isDisplayed() &&
                addToCartBikeLight.isDisplayed() &&
                addToCartTShirt.isDisplayed() &&
                addToCartJacket.isDisplayed() &&
                addToCartOnesie.isDisplayed() &&
                addToCartRedTShirt.isDisplayed();
    }

    // Método para agregar un producto al carrito
    public void addToCart(String product) {
        switch (product) {
            case "backpack":
                driver.findElement(addToCartButtonBackpack).click();
                break;
            case "bike light":
                driver.findElement(addToCartButtonBikeLight).click();
                break;
            case "t-shirt":
                driver.findElement(addToCartButtonTShirt).click();
                break;
            case "jacket":
                driver.findElement(addToCartButtonJacket).click();
                break;
            case "onesie":
                driver.findElement(addToCartButtonOnesie).click();
                break;
            case "red t-shirt":
                driver.findElement(addToCartButtonRedTShirt).click();
                break;
            default:
                System.out.println("Producto no encontrado");
        }
    }

    // Ver el carrito
    public void clickCart() {
        driver.findElement(cartLogo).click();
    }
}