package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage1 {
    private WebDriver driver;

    // Localizadores
    private By firstNameField = By.id("first-name"); // Asegúrate de que este ID sea correcto
    private By lastNameField = By.id("last-name"); // Asegúrate de que este ID sea correcto
    private By postalCodeField = By.id("postal-code"); // Asegúrate de que este ID sea correcto
    private By continueButton = By.cssSelector(".btn_primary"); // Asegúrate de que este selector sea correcto

    public CheckoutPage1(WebDriver driver) {
        this.driver = driver;
    }

    // Método para ingresar el nombre
    public void enterFirstName(String firstName) {
        WebElement firstNameInput = driver.findElement(firstNameField);
        firstNameInput.sendKeys(firstName);
    }

    // Método para ingresar el apellido
    public void enterLastName(String lastName) {
        WebElement lastNameInput = driver.findElement(lastNameField);
        lastNameInput.sendKeys(lastName);
    }

    // Método para ingresar el código postal
    public void enterPostalCode(String postalCode) {
        WebElement postalCodeInput = driver.findElement(postalCodeField);
        postalCodeInput.sendKeys(postalCode);
    }

    // Método para hacer clic en el botón de continuar
    public void clickContinue() {
        WebElement continueBtn = driver.findElement(continueButton);
        continueBtn.click();
    }

    // Método para verificar que el campo de nombre está presente
    public boolean isFirstNameFieldPresent() {
        return driver.findElement(firstNameField).isDisplayed();
    }

    // Método para verificar que el campo de apellido está presente
    public boolean isLastNameFieldPresent() {
        return driver.findElement(lastNameField).isDisplayed();
    }

    // Método para verificar que el campo de código postal está presente
    public boolean isPostalCodeFieldPresent() {
        return driver.findElement(postalCodeField).isDisplayed();
    }
}

