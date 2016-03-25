package com.endeca.microservices.connector.api;

import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;

public class GetConnectsByIdTest{
	
	private static Client client = Client.create();
	private String reqUrl = Constants.connectors;
	private String testName;
	private Map<String, String> responseMap ;
	private String xmlName = "GetConnectsByIdTest.xml";
	private String connectorId;
	
	CommonUtil comUtil = new CommonUtil(client, xmlName, GetConnectsByIdTest.class);
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		//delete connector by id after each test
		comUtil.executeDelete(reqUrl+"/"+connectorId);
	}
	
	/**
	 * Get connector detail via correct connectorId
	 */
	@Test(groups = {"Functional"})
	public void testGetConnectorsById1() {
		testName = "getconnectorsById1";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		
		//get connector by id
		connectorId = comUtil.getConnectorId(responseMap.get("jsonRes")).get(0);
		responseMap = comUtil.executeGet(reqUrl+"/"+connectorId);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponse(responseMap, testName);
	}

}