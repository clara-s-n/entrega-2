package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage2 {
    private WebDriver driver;

    // Localizadores
    private By shippingInfo = By.cssSelector("[data-test='shipping-info-label']");
    private By finishButton = By.cssSelector(".btn_action.cart_button");

    public CheckoutPage2(WebDriver driver) {
        this.driver = driver;
    }

    // Método para verificar que la información de envío está presente
    public boolean isShippingInformationPresent() {
        return driver.findElement(shippingInfo).isDisplayed();
    }

    // Método para verificar que el botón de finalizar está presente
    public boolean isFinishButtonPresent() {
        return driver.findElement(finishButton).isDisplayed();
    }

    // Método para hacer clic en el botón de finalizar
    public void clickFinish() {
        WebElement finishBtn = driver.findElement(finishButton);
        finishBtn.click();
    }
}
