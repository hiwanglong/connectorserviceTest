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
		comUtil.executeDelete(reqUrl+"/"+containerId);		
	}
	
	/**
	 * 
	 */
	@Test(groups = {"Functional"})
	public void testBrowseContainerById2() {	
		//post connector		
		testName = "browseContainerById2-connector";	
		String connectorRes = comUtil.executePost(reqUrl,testName).get("jsonRes");	//create connector and get response					
		connectorId = comUtil.getConnectorId(connectorRes).get(0);	//get connector id
		
		//post auth	
		testName = "browseContainerById2-auth";		
		reqUrl = Constants.connectorAuth.replace("{connectorId}",connectorId);	//replace connectorId to get url
		responseMap = comUtil.executePost(reqUrl,testName);				//post auth
		token = comUtil.getToken(responseMap.get("jsonRes"));			//get token
		
		//browse
		testName = "browseContainerById2-browse";
		containerId = comUtil.getXmlNode(testName, "CONTAINERID");		//get containerId
		reqUrl = Constants.browseByContainerId.replace("{connectorId}",connectorId).replace("{containerId}",containerId);	//replace connectorId & containerId to get url
		responseMap = comUtil.executeGet(reqUrl, token);				//execute browse

		comUtil.checkResponseRegex(responseMap, "browseContainerById2-browse");			//check response with REGEX
		
	}
	
}