package com.qa.tn.utilities;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.util.Base64;

public class UtilsClass {

    public String generate_email_address(){
        return new StringBuffer("testOnPlaywright"+System.currentTimeMillis()+"@gmail.com").toString();
    }

}
