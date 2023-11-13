package com.qa.tn.pages;

import com.microsoft.playwright.Page;

public class AccountPage {

    Page page;
    public AccountPage(Page page) {
        this.page = page;
    }

    //Object Repository
    public String edit_my_account_Information = "//a[text()='Edit your account information']";
    public String your_account_created_message_element = "div#content h1";



    //Pre-build Method
    public String getConfirm_landing_Account_page(){
        return page.textContent(edit_my_account_Information);
    }

    public String getConfirm_account_created_message(){
        return page.textContent(your_account_created_message_element);
    }


}
