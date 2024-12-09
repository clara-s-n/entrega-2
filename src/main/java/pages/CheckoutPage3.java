package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage3 {
    private WebDriver driver;

    // Localizadores
    private By confirmationMessage = By.cssSelector(".complete-header"); // Asegúrate de que este selector sea correcto
    private By backToProductsButton = By.cssSelector("#back-to-products"); // Asegúrate de que este selector sea correcto

    public CheckoutPage3(WebDriver driver) {
        this.driver = driver;
    }

    // Método para verificar que el mensaje de confirmación está presente
    public boolean isConfirmationMessagePresent() {
        return driver.findElement(confirmationMessage).isDisplayed();
    }

    // Método para verificar que el botón de volver a la página de productos está presente
    public boolean isBackToProductsButtonPresent() {
        return driver.findElement(backToProductsButton).isDisplayed();
    }

    // Hacer clic en el botón de volver a la página de productos
    public void clickBackToProducts() {
        WebElement backToProductsBtn = driver.findElement(backToProductsButton);
        backToProductsBtn.click();
    }
}
