package com.qa.tn.base;

import com.microsoft.playwright.Page;
import com.qa.tn.factory.PlaywrightFactory;
import com.qa.tn.pages.*;
import com.qa.tn.utilities.UtilsClass;
import org.testng.annotations.*;

import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class Base {


    // Class Referential Variable
    PlaywrightFactory pf;

    // Page Reference variable
    protected Page page;


    //Properties File variable
    protected Properties prop;
    protected Properties testData;


    protected LoginPage loginpage;
    protected AccountPage accountpage;
    protected RegistrationPage registrationpage;
    protected ProductSearchPage productsearchpage;


    //Create CRV
    protected HomePage homepage;
    protected UtilsClass  util;



    @BeforeMethod
    public void setup(){
        pf = new PlaywrightFactory();
        prop = pf.init_config_properties();
        testData = pf.init_testData_properties();
        page = pf.init_Browser(prop);

    }


    @AfterMethod
    public void tearDown(){
        page.context().browser().close();

    }

}
