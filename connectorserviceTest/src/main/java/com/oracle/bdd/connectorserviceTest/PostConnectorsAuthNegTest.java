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

public class PostConnectorsAuthNegTest {

	static Client client = Client.create();
	static String testFile="PostConnectorsAuthNegTest.xml";
	static CommonUtil util=new CommonUtil(client, testFile);
	static String reqUrl;
	static String testCase, connectorId;
	Map<String, String> response;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//post connector 
		String connectorRes=util.executePost(Constants.connectors, "setUpPostConnectorsAuthNeg").get("jsonRes");
						
	    //get connector id
		connectorId=util.getConnectorId(connectorRes).get(0);
				
		//update reqUrl	
		reqUrl=Constants.connectorAuth.replace("{connectorId}",connectorId);
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//delete connector
		String delUrl=Constants.connectorId.replace("{connectorId}",connectorId);
		util.executeDelete(delUrl);
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testPostConnectorsAuthNeg1() { //check request with invalid connectorId to post, err:00101
		
		String testName="testPostConnectorsAuthNeg1";
		
		//post auth
		response=util.executePost(Constants.connectorAuth.replace("{connectorId}", "xxxxxxxxxx"), testName);
				
		//check response
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	@Test
	public void testPostConnectorsAuthNeg2() { //check request with body NULL to post, err:00105
		
		String testName="testPostConnectorsAuthNeg2";
		
		//post auth
		response=util.executePost(reqUrl, testName);
				
		//check response
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	@Test
	public void testPostConnectorsAuthNeg3() { //check request with extra nodes to post, err:00105
		
		String testName="testPostConnectorsAuthNeg3";
		
		//post auth
		response=util.executePost(reqUrl, testName);
				
		//check response
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	@Test
	public void testPostConnectorsAuthNeg4() { //check request with extra parameters to post, err:00105
		
		String testName="testPostConnectorsAuthNeg4";
		
		//post auth
		response=util.executePost(reqUrl, testName);
				
		//check response
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	@Test
	public void testPostConnectorsAuthNeg5() { //check request with missing parameters "username" to post, err:00107
		
		String testName="testPostConnectorsAuthNeg5";
		
		//post auth
		response=util.executePost(reqUrl, testName);
				
		//check response
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	@Test
	public void testPostConnectorsAuthNeg6() { //check request with missing parameters "password" to post, err:00107
		
		String testName="testPostConnectorsAuthNeg6";
		
		//post auth
		response=util.executePost(reqUrl, testName);
				
		//check response
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	@Test
	public void testPostConnectorsAuthNeg7() { //check request with invalid parameters "username" to post, err:00104
		
		String testName="testPostConnectorsAuthNeg7";
		
		//post auth
		response=util.executePost(reqUrl, testName);
				
		//check response
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	@Test
	public void testPostConnectorsAuthNeg8() { //check request with invalid parameters "password" to post, err:00104
		
		String testName="testPostConnectorsAuthNeg8";
		
		//post auth
		response=util.executePost(reqUrl, testName);
				
		//check response
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	

}
