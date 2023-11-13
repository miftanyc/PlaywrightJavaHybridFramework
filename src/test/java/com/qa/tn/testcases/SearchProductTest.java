package com.qa.tn.testcases;

import com.qa.tn.base.Base;
import com.qa.tn.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchProductTest extends Base {

    @BeforeMethod
    public void setupSearchProductTest(){
        homepage= new HomePage(page);
    }


    @Test(priority = 1, dataProvider = "Existing Search Product")
    public void search_with_existing_product(String productName){
        homepage.setProduct_in_searchBox(productName);
        productsearchpage = homepage.click_on_search_button();

        Assert.assertEquals(productsearchpage.getSearch_product_validation(), testData.getProperty("searchProductFound")+productName);

    }

    @DataProvider(name="Existing Search Product")
    public Object[][] existing_product_data(){
        return new Object[][] { {"iphone"},
                                {"mac"},
                                {"hp"}   };
    }


    @Test(priority = 2, dataProvider = "NonExisting Product data")
    public void search_with_nonExisting_product(String nonExistingProductName){
        homepage.setProduct_in_searchBox(nonExistingProductName);
        productsearchpage = homepage.click_on_search_button();

        Assert.assertEquals(productsearchpage.getSearch_product_validation(), testData.getProperty("searchProductFound")+nonExistingProductName);
        Assert.assertTrue(productsearchpage.isVisible_No_product_match_search_criteria());
    }

    @DataProvider(name="NonExisting Product data")
    public Object[][] nonExisting_product_data(){
        return new Object[][] { {"dell"},
                                {"mobile"},
                                {"glass"}   };
    }


    @Test(priority = 3)
    public void search_with_noText_in_searchBox(){
        productsearchpage = homepage.click_on_search_button();
        Assert.assertEquals(productsearchpage.isVisible_No_product_match_search_criteria(), false);

        //Intentionally made it fail
    }
}
