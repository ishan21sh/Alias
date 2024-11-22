package com.qa.alias.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.alias.constants.AppConstants;
import com.qa.alias.factory.SeleniumFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class YourEarningsBreakdownScreen {
	/*
	 * Global Variable
	 */
	AndroidDriver driver;
	SeleniumFactory sf =new SeleniumFactory();

	/*
	 *  Giving life to driver by Constracor
	 */
	public YourEarningsBreakdownScreen(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/**
	 * Locaters and Storing In Variable
	 */
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/sellNowEarningsBreakdownTitle']")
	private WebElement yourEarningsBreakdownText;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/pricingToolbarSubtitle']")
	private WebElement usSize;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/earningsBreakdownSellingPriceValue']")
	private WebElement sellingPriceText;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/earningsBreakdownCommissionValue']")
	private WebElement commissionValueText;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/earningsBreakdownFinalFeeValue']")
	private WebElement sellerFeeText;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/earningsBreakdownSpendAmountValue']")
	private WebElement availableToSpeendText;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/earningsBreakdownSpendApproximateCurrency']")
	private WebElement approxAvailableToSpeendText;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/earningsBreakdownFinalCashOutAmountValue']")
	private WebElement availableToCashOutText;
	
	@AndroidFindBy(xpath =  "//android.widget.ImageButton")
	private WebElement pressBackButton;
	
	/**
	 * User Defined Method To UI Actions
	 * @throws IOException 
	 */
	public boolean verifyYourEarningsBreakdownTextIsPresentInTheScreen(String yourEarningsBreakdownTextScreenVerification) {
		return sf.verifyElementIsHavingExpectedText(yourEarningsBreakdownText, yourEarningsBreakdownTextScreenVerification, AppConstants.casesenstive);
	}
	
	public String getUsSize() throws IOException {
		 String input =usSize.getText();
		 String[] parts = input.split(" US");
	        return parts[0].trim(); 
	}
	
	public String getSellingPrice() {
		return sellingPriceText.getText();
	}
	
	public String getCommissionValue() {
		return commissionValueText.getText();
	}
	
	public String getSellerFee() {
		return sellerFeeText.getText();
	}
	
	public String getAvailableToSpeend() {
		return availableToSpeendText.getText()+" "+approxAvailableToSpeendText.getText();
	}
	
	public String getAvailableToCashOut() {
		return availableToCashOutText.getText();
	}
	
	public void tapOnPressBackButton() throws IOException {
		sf.tap(pressBackButton, driver);
	}
	
	public void ifSearchBarIconIsPresentTapOnBackButtonToSellTab() throws IOException {
		boolean result=sf.verifyElementIsDisplayedAreNot(yourEarningsBreakdownText);
		if (result==true) {
			sf.tap(pressBackButton, driver);
		}
	}
}
