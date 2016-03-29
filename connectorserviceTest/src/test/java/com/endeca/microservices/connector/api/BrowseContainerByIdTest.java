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
	private String connectorId;
	private String containerId;
	private String token;
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
		//post connector		
		testName = "browseContainerById2-connector";	
		String connectorRes = comUtil.executePost(reqUrl,testName).get("jsonRes");						
		connectorId = comUtil.getConnectorId(connectorRes).get(0);	//get connector id
		
		//post auth	
		testName = "browseContainerById2-auth";		
		reqUrl = Constants.connectorAuth.replace("{connectorId}",connectorId);	//replace connectorId
		responseMap = comUtil.executePost(reqUrl,testName);
		token = comUtil.getToken(responseMap.get("jsonRes"));
		System.out.println(token);
		
		//browse
		containerId = "YnJvd3NlQXBpVGVzdERhdGE";
		reqUrl = Constants.browseByContainerId.replace("{connectorId}",connectorId).replace("{containerId}",containerId);
		
	}
}