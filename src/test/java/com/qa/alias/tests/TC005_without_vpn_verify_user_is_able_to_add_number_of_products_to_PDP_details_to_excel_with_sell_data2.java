package com.qa.alias.tests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.alias.base.TestBase;
import com.qa.alias.constants.AppConstants;

public class TC005_without_vpn_verify_user_is_able_to_add_number_of_products_to_PDP_details_to_excel_with_sell_data2 extends TestBase{
	@Test
	public void verifyUserIsAbleToAddPDPDetailsToExcel() throws IOException, InterruptedException, URISyntaxException {
		
		// Adding logger
		logger = Logger.getLogger("Verify User Is Able To Add PDP Details To Excel");
		PropertyConfigurator.configure(AppConstants.log4jfile);
		
		//Setting Base to intarct to excel
		AppConstants.tc005KeyValue= sf.getDataFromExcel(AppConstants.excelPath, AppConstants.testDataSheetName, AppConstants.uniqueDataTC005, AppConstants.isTableHorizontal);
		
		//Stroing the data which got from excel
		String landingScreenVerification = sf.fetchDatFromMap(AppConstants.tc005KeyValue,"Landing_Screen_Verification");
		String signInScreenVerification = sf.fetchDatFromMap(AppConstants.tc005KeyValue,"Sign_In_Screen_Verification");
		String emailID = sf.fetchDatFromMap(AppConstants.tc005KeyValue,"Email_ID");
		String password = sf.fetchDatFromMap(AppConstants.tc005KeyValue,"Password");
		String homeScreenVerification = sf.fetchDatFromMap(AppConstants.tc005KeyValue,"Home_Screen_Verification");
		String searchScreenVerification = sf.fetchDatFromMap(AppConstants.tc005KeyValue,"Search_Screen_Verification");
		String productDescriptionScreenVerification = sf.fetchDatFromMap(AppConstants.tc005KeyValue,"Product_Description_Screen_Verification");
		String startListingButtonVerification = sf.fetchDatFromMap(AppConstants.tc005KeyValue,"Start_Listing_Button_Verification");
		String newTextIsPresentInTheScreen=sf.fetchDatFromMap(AppConstants.tc005KeyValue,"Sell_Screen_Verification");
		String yourEarningsBreakdownScreenVerification=sf.fetchDatFromMap(AppConstants.tc005KeyValue,"Your_Earnings_Breakdown_Screen_Verification");
		
		boolean signUpText=landingScreen.verifySignUpTextIsPresentInTheScreen(landingScreenVerification);
		Assert.assertTrue(signUpText);
		logger.info("Sign Up Text Is Present In The screen");
		landingScreen.tapOnSignInLink();
		logger.info("Succssfully taped on the Sigin Link");
		
		boolean signInText=signInScreen.verifySignInTextIsPresentInTheScreen(signInScreenVerification);
		Assert.assertTrue(signInText);
		logger.info("Sign In Text Is Present In The screen");
		signInScreen.loginWithUserNameAndPassword(emailID, password);
		logger.info("Successfully loggined to the application");
		
		homescreen.ifAccountAndBalanceUpdatesIsPresentTapOnCancel();
		boolean homeText=homescreen.verifyHomeTextIsPresentInTheScreen(homeScreenVerification);
		Assert.assertTrue(homeText);
		logger.info("Home screen Text Is Present In The screen");
		homescreen.tapOnSearchIcon();
		logger.info("Succssfully taped on the Search Icon");
		boolean searchBarText=searchScreen.verifysearchBarIconIsPresentInTheScreen(searchScreenVerification);
		Assert.assertTrue(searchBarText);
		logger.info("search BarText Text Is Present In The screen");
		
		int numberOfRow=sf.getRowCount(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName);
		int numberOfCell=sf.getCellCount(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, 0);
		int numberOfCellInSellInfo=sf.getCellCount(AppConstants.excelPath, AppConstants.sellInfoSheetName,0);
		String productNametoSellInfo = null;
		String expectedProductNameSellInfo = null;
		String expectedProductName= null;
		String productName = null;
		String myIPAddress = null;
		String vpnIPAddress = null;
		for(int row=1; row<numberOfRow;row++ ) {
				 productName=sf.getCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, 0);
				 productNametoSellInfo=sf.getCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, 0);
				 expectedProductName=sf.getCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, 1);
				 if (!productName.isEmpty()) {
					 searchScreen.searchForTheProduct(productName);
					 logger.info("Succssfully saerche "+productName+" Name");
				}
				 else {
					 searchScreen.searchForTheProduct(expectedProductName);
					 logger.info("Succssfully saerche "+expectedProductName+" Name");
					 productNametoSellInfo=" ";
				}

			expectedProductNameSellInfo=sf.getCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, 1);
			try {
			
				searchScreen.swipeTillProductNameVisableAndTapOnIt(expectedProductName);
				logger.info("Succssfully Swiped till expected Product Name is visable and tap on It");
				boolean detailsText=productDescriptionScreen.verifyDetailsTextIsPresentInTheScreen(productDescriptionScreenVerification);
				Assert.assertTrue(detailsText);
				logger.info("Details Text Is Present In The screen");
				productDescriptionScreen.swipeUntillNickNameIsVisable();
				logger.info("Succssfully Swiped till Nick name Name is visable");
				String skuValue=productDescriptionScreen.getSKUValue();
				String releaseDateValue=productDescriptionScreen.getReleaseDateValue();
				String retailPriceValue=productDescriptionScreen.getRetailPriceValue();
				String colorwayValue=productDescriptionScreen.getColorwayValue();
				String brandValue=productDescriptionScreen.getBrandValue();
				String nickNameValue=productDescriptionScreen.getNicknameValue();
				logger.info("Succssfully featched value text from PDP screen");
				for(int cell=2; cell<numberOfCell;cell++) {
					int onlyCellToUpdate=2;
					//writeing back the PDP Details to excel
					sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, myIPAddress);
					sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, vpnIPAddress);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, skuValue);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, releaseDateValue);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, retailPriceValue);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, colorwayValue);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, brandValue);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, nickNameValue);
				    onlyCellToUpdate=0;
				}
				logger.info("Successfully written data into excel");
				boolean startListing=productDescriptionScreen.verifyStartListingTextIsPresentInTheScreen(startListingButtonVerification);
				Assert.assertTrue(startListing);
				logger.info("Start Listing Is Present In The screen");
				productDescriptionScreen.tapOnSellTab();
				logger.info("Successfully tap on sell tab element");
				boolean newText=sellScreen.verifyNewTextIsPresentInTheScreen(newTextIsPresentInTheScreen);
				Assert.assertTrue(newText);
				logger.info("New Text Is Present In The screen");
				sellScreen.swipTillFirstPriceElement();
				logger.info("Successfully Swiped and Taped on the element first sell Product");
				
				List<WebElement> prevElements = new ArrayList<>();
				boolean endOfListReached = false;
				int swipe=0;
				while (!endOfListReached) {
					List<WebElement> currentElements = sellScreen.allTheTopOfferElement();
					
					// Check if the current set of elements is the same as the previous set
					if (currentElements.size() == prevElements.size()) {
						// No new elements, end of the list
						endOfListReached = true; 
					} else {
						// Store The avaliable of the current elements In Excel (duplicates allowed)
						for (WebElement element : currentElements) {
							sf.tap(element, driver);
							yourEarningsBreakdownScreen.verifyYourEarningsBreakdownTextIsPresentInTheScreen(yourEarningsBreakdownScreenVerification);
							sf.implicitlyWait(driver,5);
							String usSize=yourEarningsBreakdownScreen.getUsSize();
							String sellingPrice=yourEarningsBreakdownScreen.getSellingPrice();
							String commissionValue=yourEarningsBreakdownScreen.getCommissionValue();
						    String sellerFee=yourEarningsBreakdownScreen.getSellerFee();
						    String availableToSpend=yourEarningsBreakdownScreen.getAvailableToSpeend();
						    String availableToCashOut=yourEarningsBreakdownScreen.getAvailableToCashOut();
							int cellonly=0;
							int rowCountSellInfoSheetName=sf.getRowCount(AppConstants.excelPath, AppConstants.sellInfoSheetName);
							sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, cellonly++,productNametoSellInfo);
							sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, cellonly++,expectedProductNameSellInfo);
							cellonly=0;
							for(int cell=2;cell<numberOfCellInSellInfo;cell++) {
						    	int onlyCellToUpdate=2;
						    	//writeing back the PDP Details to excel
						    	sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, onlyCellToUpdate++, usSize);
							    sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, onlyCellToUpdate++, sellingPrice);
							    sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, onlyCellToUpdate++, commissionValue);
							    sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, onlyCellToUpdate++, sellerFee);
							    sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, onlyCellToUpdate++, availableToSpend);
							    sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, onlyCellToUpdate++, availableToCashOut);
							    onlyCellToUpdate=0;
						       }
							yourEarningsBreakdownScreen.tapOnPressBackButton();
						    Thread.sleep(1000);
						    logger.info("Successfully written data into excel");
						}
					}

					if (!endOfListReached) {
						// Swipe to load more elements (scroll down)
						swipe++;
						sf.swipeUp(driver);
					}
					if (swipe==6) {
						break;
					}

					// Wait before checking again to avoid too fast iterations
					sf.implicitlyWait(driver, 2);
				}
				Thread.sleep(2000);
				productDescriptionScreen.tapOnPressBackButton();
				logger.info("Successfully written data into excel");

			}
			catch (NoSuchElementException e) {
				for(int cell=2; cell<numberOfCell;cell++) {
					int onlyCellToUpdate=2;
					//writeing back the PDP Details to excel
					sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, myIPAddress);
					sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, vpnIPAddress);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
				    onlyCellToUpdate=0;
				}
				 logger.info("Successfully written "+AppConstants.productNotIsNotPresent+" into excel");
				 for(int cell=0;cell<numberOfCellInSellInfo;cell++) {
				    	int onlyCellToUpdate=0;
				    	//writeing back the PDP Details to excel
				    	int rowCountSellInfoSheetName=sf.getRowCount(AppConstants.excelPath, AppConstants.sellInfoSheetName);
				    	sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, onlyCellToUpdate++, productNametoSellInfo);
						sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, onlyCellToUpdate++, expectedProductNameSellInfo);
				    	sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
					    sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
					    sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
					    sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
					    sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
					    sf.setCellData(AppConstants.excelPath, AppConstants.sellInfoSheetName, rowCountSellInfoSheetName, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
					    onlyCellToUpdate=0;
				       }
				 logger.info("Successfully written data into excel");
			}
			logger.info("Succssfully Entered featched PDP data to execl");
	      	}
		
		}
}