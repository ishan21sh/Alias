package com.qa.alias.constants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AppConstants {
	
	public  static final String excelPath = System.getProperty("user.dir") + "/src/test/resource/config/alias.xlsx";
	public  static final String apkPath = System.getProperty("user.dir")+"/src/test/resource/config/org_alias_v1.36.1.apk.support.apk";
	public  static final String apkPathPhotonVpn = System.getProperty("user.dir")+"/src/test/resource/config/protonvpn-5-6-38-0.apk";
	public  static final String log4jfile = System.getProperty("user.dir")+"/src/test/resource/config/log4j.properties";
	public  static final String testConfigSheetName = "Test Configuration";
	public  static final String pdpDetailsDataSheetName = "PDP Details Data";
	public  static final String testDataSheetName = "Test Data";
	public  static final String sellInfoSheetName = "Sell Info";
	public  static final String uniqueDataTestConfig = "Test";
	public  static final String uniqueDataTC001 = "TC01";
	public  static final String uniqueDataTC002 = "TC02";
	public  static final String uniqueDataTC003 = "TC03";
	public  static final String uniqueDataTC004 = "TC04";
	public  static final String uniqueDataTC005 = "TC05";
	public  static final String productNotIsNotPresent = "Product Not Is Not Present";
	public  static LinkedHashMap<String, String> testConfigKeyValue = new LinkedHashMap<String, String>();
	public  static LinkedHashMap<String, String> tc001KeyValue = new LinkedHashMap<String, String>();
	public  static LinkedHashMap<String, String> tc002KeyValue = new LinkedHashMap<String, String>();
	public  static LinkedHashMap<String, String> tc003KeyValue = new LinkedHashMap<String, String>();
	public  static LinkedHashMap<String, String> tc004KeyValue = new LinkedHashMap<String, String>();
	public  static LinkedHashMap<String, String> tc005KeyValue = new LinkedHashMap<String, String>();
	public  static final boolean isTableVertical = true;
	public  static final boolean isTableHorizontal = false;
	public  static final boolean casesenstive  = true;
	public  static List<String> allTexts=new ArrayList<>();

}
