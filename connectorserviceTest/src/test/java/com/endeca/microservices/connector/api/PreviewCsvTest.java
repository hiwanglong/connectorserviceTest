package com.endeca.microservices.connector.api;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;

public class PreviewCsvTest {
	private static Client client = Client.create();;
	private static String reqUrl = Constants.connectors;
	private static String testName;
	private static Map<String, String> responseMap ;
	private static String connectorId;
	private static String token;
	private static String xmlName = "PreviewCsvTest.xml";
	
	
	private static CommonUtil comUtil = new CommonUtil(client, xmlName, PreviewCsvTest.class);
	
	@BeforeClass(alwaysRun = true)
	public static void setUpBeforeClass() throws Exception {
		//post connector		
		testName = "previewCsv-connector";	
		responseMap = comUtil.executePost(reqUrl,testName);	//create connector and get response					
		connectorId = comUtil.getConnectorId(responseMap.get("jsonRes")).get(0);	//get connector id
		
		//post auth	
		testName = "previewCsv-auth";		
		reqUrl = Constants.connectorAuth.replace("{connectorId}",connectorId);	//replace connectorId to get url
		responseMap = comUtil.executePost(reqUrl,testName);				//post auth
		token = comUtil.getToken(responseMap.get("jsonRes"));			//get token
		
		System.out.println(token);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDownAfterClass() throws Exception {
		//delete connector by id after each test
		comUtil.executeDelete(Constants.connectors+"/"+connectorId);	
	}
	
	/**
	 * preview csv file with containerId
	 */
	@Test(groups = {"Functional"})
	public void testProviewCsvByContainerId1() {		
		//proview
		testName = "previewCsvByContainerId1";
		reqUrl = Constants.previewData.replace("{connectorId}",connectorId);	//replace connectorId to get url
		responseMap = comUtil.executePost(reqUrl, testName, token);				//execute preview
		
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseRegex(responseMap, testName);			//check response with REGEX
		
	}
	
	/**
	 * preview csv file with topNRow
	 */
	@Test(groups = {"Functional"})
	public void testProviewCsvBytopNRow2() {		
		//proview
		testName = "previewCsvBytopNRow2";
		reqUrl = Constants.previewData.replace("{connectorId}",connectorId);	//replace connectorId to get url
		responseMap = comUtil.executePost(reqUrl, testName, token);				//execute preview
		
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "DATANODE");			//check response with REGEX
		
	}
	
	/**
	 * preview csv file with sql
	 */
	@Test(groups = {"Functional"})
	public void testProviewCsvBysql3() {		
		//proview
		testName = "previewCsvBysql3";
		reqUrl = Constants.previewData.replace("{connectorId}",connectorId);	//replace connectorId to get url
		responseMap = comUtil.executePost(reqUrl, testName, token);				//execute preview
		
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "DATANODE");			//check response with REGEX
		
	}
}
