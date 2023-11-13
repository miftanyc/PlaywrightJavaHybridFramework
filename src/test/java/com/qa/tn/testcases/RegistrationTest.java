package com.qa.tn.testcases;

import com.qa.tn.base.Base;
import com.qa.tn.pages.HomePage;
import com.qa.tn.utilities.UtilsClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends Base {

    @BeforeMethod
    public void setupRegistrationTest(){
        homepage = new HomePage(page);
        util = new UtilsClass();
    }


    @Test(priority = 1)
    public void verify_registering_an_account_by_providing_only_mandatory_fields() throws InterruptedException {
        registrationpage = homepage.navigate_to_registerPage();
        registrationpage.provide_firstname(testData.getProperty("firstName"));
        registrationpage.provide_lastname(testData.getProperty("lastName"));
        registrationpage.provide_email(util.generate_email_address());
        registrationpage.provide_telephone(testData.getProperty("phoneNumber"));
        registrationpage.provide_password(prop.getProperty("validPassword"));
        registrationpage.provide_confirm_password(prop.getProperty("validPassword"));
        registrationpage.check_privacy_policy();
        accountpage = registrationpage.click_registration_continue_button();

        Assert.assertEquals(accountpage.getConfirm_account_created_message(), testData.getProperty("accountCreatedSuccessfullyMessage"));

    }

    @Test(priority = 2)
    public void verify_registering_an_account_by_providing_all_fields(){
        registrationpage = homepage.navigate_to_registerPage();
        registrationpage.provide_firstname(testData.getProperty("firstName"));
        registrationpage.provide_lastname(testData.getProperty("lastName"));
        registrationpage.provide_email(util.generate_email_address());
        registrationpage.provide_telephone(testData.getProperty("phoneNumber"));
        registrationpage.provide_password(prop.getProperty("validPassword"));
        registrationpage.provide_confirm_password(prop.getProperty("validPassword"));
        registrationpage.click_yes_to_subscribe();
        registrationpage.check_privacy_policy();
        accountpage = registrationpage.click_registration_continue_button();

        Assert.assertEquals(accountpage.getConfirm_account_created_message(), testData.getProperty("accountCreatedSuccessfullyMessage"));

    }

    @Test(priority = 3)
    public void verify_registering_an_account_with_all_missing_field(){
        registrationpage = homepage.navigate_to_registerPage();
        registrationpage.click_registration_continue_button();

        Assert.assertEquals(registrationpage.getFirstname_warning(), testData.getProperty("firstNameWarning"));
        Assert.assertEquals(registrationpage.getLastname_warning(), testData.getProperty("lastNameWarning"));
        Assert.assertEquals(registrationpage.getEmail_warning(), testData.getProperty("emailWarning"));
        Assert.assertEquals(registrationpage.getTelephone_warning(), testData.getProperty("phoneWarning"));
        Assert.assertEquals(registrationpage.getPassword_warning(), testData.getProperty("passwordWarning"));


    }

    @Test(priority = 4)
    public void verify_registering_an_account_with_existing_email(){
        registrationpage = homepage.navigate_to_registerPage();
        registrationpage.provide_firstname(testData.getProperty("firstName"));
        registrationpage.provide_lastname(testData.getProperty("lastName"));
        registrationpage.provide_email(prop.getProperty("validEmailAddress"));
        registrationpage.provide_telephone(testData.getProperty("phoneNumber"));
        registrationpage.provide_password(prop.getProperty("validPassword"));
        registrationpage.provide_confirm_password(prop.getProperty("validPassword"));
        registrationpage.click_yes_to_subscribe();
        registrationpage.check_privacy_policy();
        registrationpage.click_registration_continue_button();

        Assert.assertEquals(registrationpage.getFailed_registration_warning(), testData.getProperty("alreadyRegisteredEmailWarning"));

    }

}
