package com.epam.learn.pages;

import org.openqa.selenium.WebDriver;

public interface LoginPageActions {
    LoginPageActions typeLoginName(String username);
    HomePage clickSubmit();
    LoginPageActions waitLoginFormVisibility();
    HomePage login(String username);
}