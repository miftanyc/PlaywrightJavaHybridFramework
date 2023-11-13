package com.qa.tn.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;


    //ThreadLocal variable
    private static ThreadLocal<Playwright> tlPlaywright  = new ThreadLocal<>();
    private static ThreadLocal<Browser> tlBrowser  = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext  = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage  = new ThreadLocal<>();


    //GetMethod (Keep it Static Must)
    public static Playwright getPlaywright(){
        return tlPlaywright.get();
    }

    public static Browser getBrowser(){
        return tlBrowser.get();
    }

    public static BrowserContext getBrowserContext(){
        return tlBrowserContext.get();
    }

    protected static Page getPage(){
        return tlPage.get();
    }


    //Properties File
    Properties prop;
    Properties testData;



    //Initialize Browser
    public Page init_Browser(Properties prop){

        //playwright = Playwright.create();
        tlPlaywright.set(Playwright.create());

        switch (prop.getProperty("browserName").trim().toLowerCase()) {
            case "chromium":
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
                tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome":
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;
            default:
                System.out.println("Please pass right browser name..........!!!!");
                break;
        }

        tlBrowserContext.set(getBrowser().newContext());

        tlPage.set(getBrowserContext().newPage());

        getPage().navigate(prop.getProperty("url").trim());

        return getPage();

    }


    //Load Properties File
    public Properties init_config_properties(){

        prop = new Properties();

        try (FileInputStream fis = new FileInputStream("./src/resources/propertiesFile/config.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;

    }


    //load testData Properties File
    public Properties init_testData_properties(){

        testData = new Properties();

        try(FileInputStream fisTD = new FileInputStream("./src/resources/propertiesFile/testData.properties")){
            testData.load(fisTD);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        return testData;
    }


    public static String takeScreenshot(String methodName) {
        String path ="./screenshot/" + methodName + ".png";
        //getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64Path = Base64.getEncoder().encodeToString(buffer);

        return base64Path;
    }

}
