package com.qa.alias.tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.alias.base.TestBase;
import com.qa.alias.constants.AppConstants;

public class TC002_verify_user_is_able_to_add_number_of_products_to_PDP_details_to_excel extends TestBase{
	
	@Test
	public void verifyUserIsAbleToAddPDPDetailsToExcel() throws IOException, InterruptedException {
		
		// Adding logger
		logger = Logger.getLogger("Verify User Is Able To Add PDP Details To Excel");
		PropertyConfigurator.configure(AppConstants.log4jfile);
		
		//Setting Base to intarct to excel
		AppConstants.tc002KeyValue= sf.getDataFromExcel(AppConstants.excelPath, AppConstants.testDataSheetName, AppConstants.uniqueDataTC002, AppConstants.isTableHorizontal);
		
		//Stroing the data which got from excel
		String landingScreenVerification = sf.fetchDatFromMap(AppConstants.tc002KeyValue,"Landing_Screen_Verification");
		String signInScreenVerification = sf.fetchDatFromMap(AppConstants.tc002KeyValue,"Sign_In_Screen_Verification");
		String emailID = sf.fetchDatFromMap(AppConstants.tc002KeyValue,"Email_ID");
		String password = sf.fetchDatFromMap(AppConstants.tc002KeyValue,"Password");
		String homeScreenVerification = sf.fetchDatFromMap(AppConstants.tc002KeyValue,"Home_Screen_Verification");
		String searchScreenVerification = sf.fetchDatFromMap(AppConstants.tc002KeyValue,"Search_Screen_Verification");
		String productDescriptionScreenVerification = sf.fetchDatFromMap(AppConstants.tc002KeyValue,"Product_Description_Screen_Verification");
		String startListingButtonVerification = sf.fetchDatFromMap(AppConstants.tc002KeyValue,"Start_Listing_Button_Verification");
		
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
		String productName=sf.getCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, 1, 0);
		searchScreen.searchForTheProduct(productName);
		logger.info("Succssfully saerche "+productName+" Name");
		
		int numberOfRow=sf.getRowCount(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName);
		int numberOfCell=sf.getCellCount(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, 0);
		for(int row=1; row<numberOfRow;row++ ) {
			String expectedProductName=sf.getCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, 1);
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
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, skuValue);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, releaseDateValue);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, retailPriceValue);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, colorwayValue);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, brandValue);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, nickNameValue);
				    onlyCellToUpdate=0;
				}
				boolean startListing=productDescriptionScreen.verifyStartListingTextIsPresentInTheScreen(startListingButtonVerification);
				Assert.assertTrue(startListing);
				logger.info("Start Listing Is Present In The screen");
				productDescriptionScreen.tapOnPressBackButton();
			}
			catch (NoSuchElementException e) {
				for(int cell=2; cell<numberOfCell;cell++) {
					int onlyCellToUpdate=2;
					//writeing back the PDP Details to excel
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
				    sf.setCellData(AppConstants.excelPath, AppConstants.pdpDetailsDataSheetName, row, onlyCellToUpdate++, AppConstants.productNotIsNotPresent);
				    onlyCellToUpdate=0;
				}
			}
			logger.info("Succssfully Entered featched PDP data to execl");
		}
	}
}
