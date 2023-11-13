package com.qa.tn.pages;

import com.microsoft.playwright.Page;

public class ProductSearchPage {

    Page page;

    public ProductSearchPage(Page page) {
        this.page = page;
    }

    //Object Repository
    private String search_product_validation = "div#content h1";
    private String noProductMatchSearchCriteriaElement = "input#button-search ~ p";


    //Pre-Build Method
    public String getSearch_product_validation(){
        return page.textContent(search_product_validation);
    }

    public boolean isVisible_No_product_match_search_criteria(){
        return page.isVisible(noProductMatchSearchCriteriaElement);
    }


}
