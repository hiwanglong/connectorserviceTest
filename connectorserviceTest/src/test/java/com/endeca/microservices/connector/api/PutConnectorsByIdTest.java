package com.endeca.microservices.connector.api;

import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;

public class PutConnectorsByIdTest {
	
	Client client = Client.create();
	String testFile="PutConnectorsByIdTest.xml";
	CommonUtil util=new CommonUtil(client, testFile, PutConnectorsByIdTest.class);
	String testName, connectorId, reqUrl;
	Map<String, String> response;
	

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		
		//post connector
		String connectorRes=util.executePost(Constants.connectors, "setUpPutConnectorsById").get("jsonRes");
		
		//get connector id
		connectorId=util.getConnectorId(connectorRes).get(0);
		
		//update reqUrl
		reqUrl=Constants.connectorId.replace("{connectorId}",connectorId);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		
		//delete connector
		util.executeDelete(reqUrl);
	}

	/**
	 * check all nodes updated without "connectorType" to put
	 */
	@Test(groups = {"Functional"})
	public void testPutConnectorsById1() {  
		
		testName="testPutConnectorsById1";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
		
		//check response
		util.checkResponse(response, testName);	
	}
	
	/**
	 * check  missing "connectorName" "description" "visibility" to put
	 */
	@Test(groups = {"Functional"})
	public void testPutConnectorsById2() {  
		
		testName="testPutConnectorsById2";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
		
		//check response
		util.checkResponse(response, testName);	
	}
	
	/**
	 * check  missing "blackList" "whiteList" to put
	 */
	@Test(groups = {"Functional"})
	public void testPutConnectorsById3() {  
		
		testName="testPutConnectorsById3";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
		
		//check response
		util.checkResponse(response, testName);	
	}

}
