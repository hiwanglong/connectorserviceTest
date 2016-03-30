package com.endeca.microservices.connector.api;

import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;

public class PreviewTsvTest {
	private static Client client = Client.create();;
	private String reqUrl = Constants.connectors;
	private String testName;
	private Map<String, String> responseMap ;
	private String connectorId;
	private String containerId;
	private String token;
	private String xmlName = "PreviewTsvTest.xml";
	
	
	CommonUtil comUtil = new CommonUtil(client, xmlName, BrowseContainerByIdTest.class);
	
	@BeforeClass(alwaysRun = true)
	public static void setUpBeforeClass() throws Exception {
		
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		//delete connector by id after each test
		comUtil.executeDelete(Constants.connectors+"/"+connectorId);	
	}
	
	/**
	 * preview csv file with containerId
	 */
	@Test(groups = {"Functional"})
	public void testProviewCsvByContainerId1() {	
		//post connector		
		testName = "previewCsv-connector";	
		responseMap = comUtil.executePost(reqUrl,testName);	//create connector and get response					
		connectorId = comUtil.getConnectorId(responseMap.get("jsonRes")).get(0);	//get connector id
		
		//post auth	
		testName = "proviewCsvByContainerId1-auth";		
		reqUrl = Constants.connectorAuth.replace("{connectorId}",connectorId);	//replace connectorId to get url
		responseMap = comUtil.executePost(reqUrl,testName);				//post auth
		token = comUtil.getToken(responseMap.get("jsonRes"));			//get token
		
		//proview
		testName = "proviewCsvByContainerId1-proview";
		containerId = comUtil.getXmlNode(testName, "CONTAINERID");		//get containerId
		reqUrl = Constants.browseByContainerId.replace("{connectorId}",connectorId).replace("{containerId}",containerId);	//replace connectorId & containerId to get url
		responseMap = comUtil.executeGet(reqUrl, token);				//execute browse
		
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseRegex(responseMap, "browseContainerById2-browse");			//check response with REGEX
		
	}
}
