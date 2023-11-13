package com.qa.tn.testcases;

import com.qa.tn.base.Base;
import com.qa.tn.factory.PlaywrightFactory;
import com.qa.tn.pages.HomePage;
import com.qa.tn.utilities.UtilsClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends Base {

    @BeforeMethod
    public void setupLoginTest(){
        homepage= new HomePage(page);
        util = new UtilsClass();
    }


    @Test(priority = 1, dataProvider = "Login Data Provider")
    public void login_with_valid_credentials(String emailAddress, String password){
        loginpage = homepage.navigate_to_loginPage();
        accountpage = loginpage.perform_Login_Function(emailAddress, password);

        Assert.assertEquals(accountpage.getConfirm_landing_Account_page(), "Edit your account information");
    }

    @DataProvider(name="Login Data Provider")
    public Object[][] login_data_provider(){
        return new Object[][] { {"miftanyc+qa@gmail.com", "123456"},
                                {"miftanyc+qa1@gmail.com","123456"},
                                {"miftanyc+qa2@gmail.com","123456"},
                                {"miftanyc+qa3@gmail.com","123456"}   };
    }


    @Test(priority = 2)
    public void verify_login_with_invalid_credentials(){
        loginpage = homepage.navigate_to_loginPage();
        accountpage = loginpage.perform_Login_Function(util.generate_email_address(), prop.getProperty("InvalidPassword").trim());

        Assert.assertEquals(loginpage.get_noMatch_email_password_warning(), testData.getProperty("noMatchEmailWarning"));
    }



    @Test(priority = 3)
    public void verify_login_with_invalid_password(){
        loginpage = homepage.navigate_to_loginPage();
        accountpage = loginpage.perform_Login_Function(prop.getProperty("validEmailAddress").trim(), prop.getProperty("InvalidPassword").trim());

        Assert.assertEquals(loginpage.get_noMatch_email_password_warning(), testData.getProperty("noMatchEmailWarning"));
    }


    @Test(priority = 4)
    public void verify_login_with_invalid_email(){
        loginpage = homepage.navigate_to_loginPage();
        accountpage = loginpage.perform_Login_Function(util.generate_email_address(), prop.getProperty("validPassword").trim());

        Assert.assertEquals(loginpage.get_noMatch_email_password_warning(), testData.getProperty("noMatchEmailWarning"));
    }


    @Test(priority = 5)
    public void verify_login_without_providing_any_credential(){
        loginpage = homepage.navigate_to_loginPage();
        accountpage = loginpage.perform_Login_Function("","");

        Assert.assertEquals(loginpage.get_noMatch_email_password_warning(), testData.getProperty("noMatchEmailWarning"));
    }


}
