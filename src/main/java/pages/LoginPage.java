package pages;

// LoginPage.java

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // Localizadores
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.xpath("//input[@type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // Verificar que el botón de login está presente
    public boolean isLoginButtonPresent() {
        return driver.findElement(loginButton).isDisplayed();
    }

    // Verificar que estamos en la página de login
    public boolean isLoginPage() {
        // La URL de la página de login es https://www.saucedemo.com/
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/");
    }
}

