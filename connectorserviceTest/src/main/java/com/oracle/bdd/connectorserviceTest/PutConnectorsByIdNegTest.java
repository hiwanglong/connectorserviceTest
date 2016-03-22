package com.oracle.bdd.connectorserviceTest;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oracle.bdd.util.CommonUtil;
import com.oracle.bdd.util.Constants;
import com.sun.jersey.api.client.Client;

public class PutConnectorsByIdNegTest {
	
	Client client = Client.create();
	String testFile="PutConnectorsByIdNegTest.xml";
	CommonUtil util=new CommonUtil(client, testFile);
	String reqUrl;
	String testCase, connectorId;
	Map<String, String> response;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
				
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

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
		//util.executeDelete(reqUrl);
	}

	@Test
	public void testPutConnectorsByIdNeg1() {  // check request with  invalid id to put, err:00101
		
		String testName="testPutConnectorsByIdNeg1";
		
		//put connector
		response=util.executePost(Constants.connectorAuth.replace("{connectorId}", "xxxxxxxxxx"), testName);
		System.out.println(response.get("status"));
		
		//check status
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	@Test
	public void testPutConnectorsByIdNeg2() {  // check request body NULL to put, err:00105
		
		String testName="testPutConnectorsByIdNeg2";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	@Test
	public void testPutConnectorsByIdNeg3() {  // check request with extra nodes to put, err:00105
		
		String testName="testPutConnectorsByIdNeg3";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
			
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}

	@Test
	public void testPutConnectorsByIdNeg4() {  // check request with extra parameter to put, err:00105
		
		String testName="testPutConnectorsByIdNeg4";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
			
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	@Test
	public void testPutConnectorsByIdNeg5() {  // check request with missing  parameter node to put, err:00108
		
		String testName="testPutConnectorsByIdNeg5";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
			
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	@Test
	public void testPutConnectorsByIdNeg6() {  // check request with missing  parameter "storageServiceUrl" to put, err:00108
		
		String testName="testPutConnectorsByIdNeg6";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
			
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	@Test
	public void testPutConnectorsByIdNeg7() {  // check request with missing  parameter "username" to put, err:00108
		
		String testName="testPutConnectorsByIdNeg7";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
			
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	@Test
	public void testPutConnectorsByIdNeg8() {  // check request with missing  parameter "password" to put, err:00108
		
		String testName="testPutConnectorsByIdNeg8";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
			
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	@Test
	public void testPutConnectorsByIdNeg9() {  // check request with "connectorType" changed to put, err:00108
		
		String testName="testPutConnectorsByIdNeg9";
		
		//put connector
		response=util.executePut(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
			
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
}
