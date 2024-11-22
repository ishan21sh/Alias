package com.qa.alias.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.alias.factory.SeleniumFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class VPNAllTheScreenInOne {
	/*
	 * Global Variable
	 */
	AndroidDriver driver;
	SeleniumFactory sf =new SeleniumFactory();

	/*
	 *  Giving life to driver by Constracor
	 */
	public VPNAllTheScreenInOne(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/**
	 * Locaters and Storing In Variable
	 */
	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='ch.protonvpn.android:id/sign_in']")
	private WebElement signInButton;
	
	@AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id='ch.protonvpn.android:id/input'])[1]")
	private WebElement userNameTextField;
	
	@AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id='ch.protonvpn.android:id/input'])[2]")
	private WebElement passwordTextField;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='ch.protonvpn.android:id/signInButton']")
	private WebElement signInButtonInLoginscreen;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Connect']")
	private WebElement connectButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Disconnect']")
	private WebElement disconnectButton;
	
	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='You are unprotected']/..//android.widget.TextView)[2]")
	private WebElement mydeviceIPAddress;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='connection details']")
	private WebElement connectiondetailsButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='VPN IP']/..//android.widget.TextView[2]")
	private WebElement vpnIpAddress;
	
	@AndroidFindBy(xpath = "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.Button")
	private WebElement pressBackButton;
	
	/**
	 * User Defined Method To UI Actions
	 * @throws IOException 
	 */
	public void tapOnSignInButton() throws IOException {
		sf.tap(signInButton, driver);
	}
	
	public void loginWithUserNameAndPassword(String userName,String password) throws IOException {
		sf.tap(userNameTextField, driver);
		sf.enterInputIntoTheElement(userNameTextField, userName);
		sf.tap(passwordTextField, driver);
		sf.enterInputIntoTheElement(passwordTextField, password);
	    sf.tap(signInButtonInLoginscreen, driver);
	}
	
	public void tapOnConnectButton() throws IOException {
		boolean result=sf.verifyElementIsDisplayedAreNot(connectButton);
		//connect button is Present then tap on connect button
		if (result==true) {
			sf.tap(connectButton, driver);
		}
	}
	
	public void tapOnDisconnectButton() throws IOException {
		boolean result=sf.verifyElementIsDisplayedAreNot(disconnectButton);
		//Disconnect button is Present then tap on disconnect button
		if (result==true) {
			sf.tap(disconnectButton, driver);
		}
	}
	
	public String getMydeviceIPAddress() throws IOException {
		return mydeviceIPAddress.getText();
	}
	
	public void tapOnTheConnectiondetailsButton() throws IOException {
		sf.waitUntilvisibilityOf(connectiondetailsButton, 20);
		sf.tap(connectiondetailsButton, driver);
	}
	
	public String getVpnIPAddress() throws IOException {
		sf.waitUntilvisibilityOf(vpnIpAddress, 20);
		return vpnIpAddress.getText();
	}
	
	public void tapOnBackButton() throws IOException {
	     sf.tap(pressBackButton, driver);
	}
	
	
}
