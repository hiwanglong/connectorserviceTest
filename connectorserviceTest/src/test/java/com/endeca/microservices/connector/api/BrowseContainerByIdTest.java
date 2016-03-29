package com.endeca.microservices.connector.api;

import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;


public class BrowseContainerByIdTest {
	
	private static Client client = Client.create();;
	private String reqUrl = Constants.connectors;
	private String testName;
	private Map<String, String> responseMap ;
	private String xmlName = "BrowseContainerByIdTest.xml";
	
	CommonUtil comUtil = new CommonUtil(client, xmlName, BrowseContainerByIdTest.class);
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		//delete connector by id after each test
		comUtil.executeDelete(reqUrl+"/"+comUtil.getConnectorId(responseMap.get("jsonRes")).get(0));		
	}
	
	/**
	 * 
	 */
	@Test(groups = {"Functional"})
	public void testBrowseContainerById2() {
		testName = "browseContainerById2-connector";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		
		
		
	}
}