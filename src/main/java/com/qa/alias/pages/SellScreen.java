package com.qa.alias.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.alias.constants.AppConstants;
import com.qa.alias.factory.SeleniumFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SellScreen {
	/*
	 * Global Variable
	 */
	AndroidDriver driver;
	SeleniumFactory sf = new SeleniumFactory();

	/*
	 * Giving life to driver by Constracor
	 */
	public SellScreen(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Locaters and Storing In Variable
	 */
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='NEW']")
	private WebElement newText;

	@AndroidFindBy(xpath = "(//android.view.View//android.widget.Button)[1]")
	private WebElement firstPriceElement;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='TOP OFFER']")
	private WebElement topOfferColoumnHeader;

	/**
	 * User Defined Method To UI Actions
	 * 
	 * @throws IOException
	 */
	public boolean verifyNewTextIsPresentInTheScreen(String newTextScreenVerification) {
		return sf.verifyElementIsHavingExpectedText(newText, newTextScreenVerification, AppConstants.casesenstive);
	}

	public void swipAndTapFirstPriceElement() throws IOException {
		sf.swipeUpToElement(firstPriceElement, 10, driver);
		sf.tap(firstPriceElement, driver);
	}

	public void swipTillFirstPriceElement() throws IOException {
		sf.swipeUpToElement(firstPriceElement, 10, driver);
	}
		
    public void swipeDownTillTopOfferColoumnHeader() throws IOException {
    	sf.swipeDownToElement(topOfferColoumnHeader, 10, driver);
    }
    
    public List<WebElement> allTheTopOfferElement() throws IOException {
    	return driver.findElements(By.xpath("//android.widget.ScrollView/android.view.View/android.widget.Button/../android.widget.TextView"));
    }
    
    
    

}
