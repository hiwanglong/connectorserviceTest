package com.endeca.microservices.connector.api;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;

public class PreviewTsvTest {

	static Client client = Client.create();
	static String testFile="PreviewTsvTest.xml";
	static CommonUtil util=new CommonUtil(client, testFile, PostConnectorsAuthTest.class);
	static String reqUrl, connectorId, token, previewUrl;
	String testName;
	Map<String, String> response;
	
	@BeforeClass(alwaysRun = true)
	public static void setUpBeforeClass() throws Exception {
		
		//post connector 
		String connectorRes=util.executePost(Constants.connectors, "setUpPreviewTsvToConnectorId").get("jsonRes");
						
	    //get connector id
		connectorId=util.getConnectorId(connectorRes).get(0);
				
		//update reqUrl	with connectorId
		reqUrl=Constants.connectorAuth.replace("{connectorId}",connectorId);
		
		//update browseUrl with connectorId
		previewUrl=Constants.previewData.replace("{connectorId}",connectorId);
		
		//post auth
		String authRes=util.executePost(reqUrl, "setUpPreviewTsvToAuth").get("jsonRes");
		
		//get token
		token=util.getToken(authRes);	
		
		
	}
	
	@AfterClass(alwaysRun = true)
	public static void tearDownAfterClass() throws Exception {

		//delete connector
		String delUrl=Constants.connectorId.replace("{connectorId}",connectorId);
		util.executeDelete(delUrl);
		
	}
	
	/**
	 * preview tsv file with containerId only request
	 */
	@Test(groups = {"Functional"})
	public void testPreviewTsv1() {	
		
		testName="testPreviewTsv1";
		
		//post request with a containerId
		response=util.executePost(previewUrl, testName, token);
		
		//check status
		util.checkStatus(response, testName);
				
		//check response
		util.checkResponse(response, testName);
		
	}
	
	/**
	 * preview tsv file with containerId + topNRow=1 request
	 */
	@Test(groups = {"Functional"})
	public void testPreviewTsv2() {	
		
		testName="testPreviewTsv2";
		
		//post request with a containerId
		response=util.executePost(previewUrl, testName, token);
		
		//check status
		util.checkStatus(response, testName);
				
		//check response node
		util.checkResponseNode(response, testName, "DATANODE");
		
	}
	
	/**
	 * preview tsv file with containerId + sql=1 column request
	 */
	@Test(groups = {"Functional"})
	public void testPreviewTsv3() {	
		
		testName="testPreviewTsv3";
		
		//post request with a containerId
		response=util.executePost(previewUrl, testName, token);
		
		//check status
		util.checkStatus(response, testName);
				
		//check response node
		util.checkResponseNode(response, testName, "DATANODE");
		
	}
	
	/**
	 * preview tsv file with containerId + parsingOption:herder=false request
	 */
	@Test(groups = {"Functional"})
	public void testPreviewTsv4() {	
		
		testName="testPreviewTsv3";
		
		//post request with a containerId
		response=util.executePost(previewUrl, testName, token);
		
		//check status
		util.checkStatus(response, testName);
				
		//check response node
		util.checkResponseNode(response, testName, "DATANODE");
		
	}
}
