package com.qa.tn.pages;

import com.microsoft.playwright.Page;

public class HomePage {
    Page page;


    public HomePage(Page page) {
        this.page = page;
    }

    private String my_account_button = "i.fa-user";
    private String register_button = "//a[text()='Register']";
    private String login_button = "//a[text()='Login']";
    private String search_box_field="div#search input";
    private String search_button="span.input-group-btn";



    public LoginPage navigate_to_loginPage(){
        page.click(my_account_button);
        page.click(login_button);
        return new LoginPage(page);
    }

    public RegistrationPage navigate_to_registerPage(){
        page.click(my_account_button);
        page.click(register_button);
        return new RegistrationPage(page);
    }

    public String get_Title(){
        return page.title();
    }

    public String get_Url(){
        return page.url();
    }

    public void setProduct_in_searchBox(String productName){
        page.fill(search_box_field, productName);
    }
    public ProductSearchPage click_on_search_button(){
        page.click(search_button);
        return new ProductSearchPage(page);
    }






}
