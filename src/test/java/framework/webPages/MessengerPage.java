package framework.webPages;

import org.openqa.selenium.By;

public class MessengerPage extends BasePage{

    private By emailField = By.id("email");
    private By passwordField = By.id("pass");
    private By signUpButton = By.id("loginbutton");

    public void enterEmail(String email) {
        setValue(emailField, email);
    }

    public void enterPassword(String password) {
        setValue(passwordField, password);
    }

    public void clickOnSignupButton() {
        clickOn(signUpButton);
    }
}
