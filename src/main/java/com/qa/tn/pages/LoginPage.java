package com.qa.tn.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    //Object Repository
    private String email_field = "input#input-email";
    private String password_field="input#input-password";
    private String login_button="input.btn-primary";
    private String failed_login_warning ="div.alert-danger";




    //Pre-build Method
    public AccountPage perform_Login_Function(String username, String password){
        page.fill(email_field, username);
        page.fill(password_field, password);
        page.click(login_button);
        return new AccountPage(page);
    }

    public String get_Title(){
        return page.title();
    }

    public String get_Url(){
        return page.url();
    }

    public String get_noMatch_email_password_warning(){
        return page.textContent(failed_login_warning);
    }

}

