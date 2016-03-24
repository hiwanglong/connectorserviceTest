package com.endeca.microservices.connector.api;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;

public class PutConnectorsByIdNegTest {
	
	Client client = Client.create();
	String testFile="PutConnectorsByIdNegTest.xml";
	CommonUtil util=new CommonUtil(client, testFile);
	String testName, connectorId, reqUrl;
	Map<String, String> response;
	

	@Before
	public void setUp() throws Exception {
		
		//post connector
		String connectorRes=util.executePost(Constants.connectors, "setUpPutConnectorsByIdNeg").get("jsonRes");
		
		//get connector id
		connectorId=util.getConnectorId(connectorRes).get(0);
		
		//update reqUrl
		reqUrl=Constants.connectorId.replace("{connectorId}",connectorId);
	}

	@After
	public void tearDown() throws Exception {
		
		//delete connector
		util.executeDelete(reqUrl);
	}

	/**
	 * check request with invalid id to put, err:00101
	 */
	@Test
	public void testPutConnectorsByIdNeg1() { 
		
		testName="testPutConnectorsByIdNeg1";
		
		//put connector
		response=util.executePost(Constants.connectorAuth.replace("{connectorId}", "xxxxxxxxxx"), testName);
		System.out.println(response.get("status"));
		
		//check status
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	/**
	 * check request body NULL to put, err:00105
	 */
	@Test
	public void testPutConnectorsByIdNeg2() { 
		
		testName="testPutConnectorsByIdNeg2";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	/**
	 * check request with extra nodes to put, err:00105
	 */
	@Test
	public void testPutConnectorsByIdNeg3() {  
		
		testName="testPutConnectorsByIdNeg3";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
			
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}

	/**
	 * check request with extra parameter to put, err:00105
	 */
	@Test
	public void testPutConnectorsByIdNeg4() {  
		
		testName="testPutConnectorsByIdNeg4";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
			
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	/**
	 * check request with missing  parameter node to put, err:00108
	 */
	@Test
	public void testPutConnectorsByIdNeg5() {  
		
		testName="testPutConnectorsByIdNeg5";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
			
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	/**
	 * check request with missing  parameter "storageServiceUrl" to put, err:00108
	 */
	@Test
	public void testPutConnectorsByIdNeg6() {  
		
		testName="testPutConnectorsByIdNeg6";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
			
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	/**
	 * check request with missing  parameter "username" to put, err:00108
	 */
	@Test
	public void testPutConnectorsByIdNeg7() {  
		
		testName="testPutConnectorsByIdNeg7";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
			
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	/**
	 *  check request with missing  parameter "password" to put, err:00108
	 */
	@Test
	public void testPutConnectorsByIdNeg8() { 
		
		testName="testPutConnectorsByIdNeg8";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
			
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	/**
	 * check request with "connectorType" changed to put, err:00108
	 */
	@Test
	public void testPutConnectorsByIdNeg9() {  
		
		testName="testPutConnectorsByIdNeg9";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
			
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
}
