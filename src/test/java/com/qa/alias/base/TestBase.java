package com.qa.alias.base;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.qa.alias.constants.AppConstants;
import com.qa.alias.factory.SeleniumFactory;
import com.qa.alias.pages.HomeScreen;
import com.qa.alias.pages.LandingScreen;
import com.qa.alias.pages.ProductDescriptionScreen;
import com.qa.alias.pages.SearchScreen;
import com.qa.alias.pages.SellScreen;
import com.qa.alias.pages.SignInScreen;
import com.qa.alias.pages.VPNAllTheScreenInOne;
import com.qa.alias.pages.YourEarningsBreakdownScreen;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class TestBase {

	/**
	 * Global variable
	 */
	public SeleniumFactory sf;
	public AndroidDriver driver;
	private AppiumDriverLocalService service;
	public static Logger logger;
	protected HomeScreen homescreen;
	protected LandingScreen landingScreen;
	protected ProductDescriptionScreen productDescriptionScreen;
	protected SearchScreen searchScreen;
	protected SignInScreen signInScreen;
	protected SellScreen sellScreen;
	protected YourEarningsBreakdownScreen yourEarningsBreakdownScreen;
	protected VPNAllTheScreenInOne vpnAllTheScreenInOne;

	/**
	 * This is to start the appium server in befoure suite
	 * 
	 * @throws IOException
	 * @throws InterruptedException 
	 * @throws URISyntaxException 
	 */
	@BeforeSuite
	public void startAppiumServer() throws IOException, InterruptedException, URISyntaxException {
		sf = new SeleniumFactory();

		AppConstants.testConfigKeyValue = sf.getDataFromExcel(AppConstants.excelPath, AppConstants.testConfigSheetName,AppConstants.uniqueDataTestConfig, AppConstants.isTableVertical);

		String withIPAddress = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "withIPAddress");
		String usingPortInString = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "usingPort");
		int port = sf.stringToInteger(usingPortInString);
		String basePathe = "--" + sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "basePathe");
		String remortHostEndPoint = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "remortHostEndPoint");
		String emulatorPath = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "emulatorPath");
		String avdName = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "avdName");

		/**
		 * This to on the appium serevrs
		 */
		try {
			AppiumServiceBuilder builder = new AppiumServiceBuilder();
			builder.withIPAddress(withIPAddress);
			builder.usingPort(port);
			builder.withArgument(() -> basePathe, remortHostEndPoint);
			service = AppiumDriverLocalService.buildService(builder);
			service.start();
			Thread.sleep(5000);

			if (service.isRunning()) {
				System.out.println("Appium Server started on: " + service.getUrl());
			} else {
				throw new RuntimeException("Appium Server failed to start!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * This to on the emulator
		 * If Your running in the physical device then uncomment the sf.launchEmulatore line
		 */
         sf.launchEmulatore(emulatorPath, avdName);
		
		/**
		 * This Is to Delete the date in Sell info sheet of excel expet first row
		 */
		sf.deleteTheDataInRow(AppConstants.excelPath, AppConstants.sellInfoSheetName);

	}

	/**
	 * This is to open the application
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 */

	@BeforeTest
	public void openTheAppliaction() throws IOException, URISyntaxException, InterruptedException {
		sf = new SeleniumFactory();

		AppConstants.testConfigKeyValue = sf.getDataFromExcel(AppConstants.excelPath, AppConstants.testConfigSheetName,AppConstants.uniqueDataTestConfig, AppConstants.isTableVertical);

		String platformName = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "Platform_Name");
		String deviceName = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "Device_Name");
		String automationName = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "automationName");
		String appPackage = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "App_Package");
		String appActivity = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "App_Activity");
		String noResetInString = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "No_Reset");
		boolean noReset = sf.stringToBoolean(noResetInString);
		String autoGrantPermissionsInString = sf.fetchDatFromMap(AppConstants.testConfigKeyValue,"Auto_Grantpermissions");
		boolean autoGrantPermissions = sf.stringToBoolean(autoGrantPermissionsInString);
		String mobileHubUrl = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "Mobile_Hub_Url");
		String implicitWaitTimeInString = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "Implicit_Wait_Time");
		int implicitWaitTime = sf.stringToInteger(implicitWaitTimeInString);
		driver = sf.openApkFileAppPackageAndActivity(AppConstants.apkPath,appPackage,appActivity, platformName, deviceName, automationName, noReset,autoGrantPermissions, mobileHubUrl);
		sf.implicitlyWait(driver, implicitWaitTime);
		Thread.sleep(2000);
		homescreen = new HomeScreen(driver);
		landingScreen = new LandingScreen(driver);
		productDescriptionScreen = new ProductDescriptionScreen(driver);
		searchScreen = new SearchScreen(driver);
		signInScreen = new SignInScreen(driver);
		sellScreen = new SellScreen(driver);
		yourEarningsBreakdownScreen = new YourEarningsBreakdownScreen(driver);
	}

	/**
	 * close the application
	 * @throws IOException 
	 */
	@AfterTest
	public void tearDown() throws IOException {
		/**
		 * This is to close the running application that is alias
		 */
		sf.closingApplication(driver);
		
		
	}

	/**
	 * Close the server
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@AfterSuite
	public void stopAppiumServer() throws IOException, InterruptedException {
		
		/**
		 * This To Remove the delete Duplicate Rows In '2' Column of sell info sheet
		 */
		sf.deleteDuplicateRowsInColumn(AppConstants.excelPath, AppConstants.sellInfoSheetName, 1,2);
		
		/**
		 * this is to close the appium serever
		 */
		if (service != null && service.isRunning()) {
			service.stop();
			System.out.println("Appium Server stopped.");
		}

	}

}
