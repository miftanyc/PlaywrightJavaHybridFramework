package com.qa.tn.pages;

import com.microsoft.playwright.Page;

public class RegistrationPage {

    Page page;

    public RegistrationPage(Page page) {
        this.page = page;
    }


    //Object Repository
    private String firstname = "input#input-firstname";
    private String lastname = "input#input-lastname";
    private String email = "input#input-email";
    private String telephone = "input#input-telephone";
    private String password="input#input-password";
    private String confirm_password = "input#input-confirm";
    private String subscribe_yes_radiobutton ="//input[@ name='newsletter'][@value='1']";
    private String privacy_policy_checkbox = "//input[@name='agree']";
    private String continue_registration_button = "input.btn-primary";
    private String failed_registration_warning = "div.alert-danger";
    private String firstname_provide_warning ="input#input-firstname + div.text-danger";
    private String lastname_provide_warning ="input#input-lastname + div.text-danger";
    private String email_provide_warning ="input#input-email + div.text-danger";
    private String telephone_provide_warning ="input#input-telephone + div.text-danger";
    private String password_provide_warning ="input#input-password + div.text-danger";



    //Pre-built Method
    public void provide_firstname(String firstName){
        page.fill(firstname,firstName);
    }

    public void provide_lastname(String lastName){
        page.fill(lastname, lastName);
    }

    public void provide_email(String emailAddress){
        page.fill(email, emailAddress);
    }

    public void provide_telephone(String phoneNumber){
        page.fill(telephone, phoneNumber);
    }

    public void provide_password(String passWord){
        page.fill(password, passWord);
    }

    public void provide_confirm_password(String passWord){
        page.fill(confirm_password, passWord);
    }

    public void click_yes_to_subscribe(){
        page.click(subscribe_yes_radiobutton);
    }

    public void check_privacy_policy(){
        page.click(privacy_policy_checkbox);
    }

    public AccountPage click_registration_continue_button(){
        page.click(continue_registration_button);
        return new AccountPage(page);
    }

    public String getFailed_registration_warning(){
        return page.textContent(failed_registration_warning);
    }

    public String getFirstname_warning(){
        return page.textContent(firstname_provide_warning);
    }

    public String getLastname_warning(){
        return page.textContent(lastname_provide_warning);
    }

    public String getEmail_warning(){
        return page.textContent(email_provide_warning);
    }

    public String getTelephone_warning(){
        return page.textContent(telephone_provide_warning);
    }

    public String getPassword_warning(){
        return page.textContent(password_provide_warning);
    }

}
