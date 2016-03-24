package com.endeca.microservices.connector.api;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;

public class PostConnectorsAuthNegTest {

	static Client client = Client.create();
	static String testFile="PostConnectorsAuthNegTest.xml";
	static CommonUtil util=new CommonUtil(client, testFile);
	static String reqUrl, connectorId;
	String testName;
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


	/**
	 * check request with invalid connectorId to post, err:00101
	 */
	@Test
	public void testPostConnectorsAuthNeg1() { 
		
		testName="testPostConnectorsAuthNeg1";
		
		//post auth
		response=util.executePost(Constants.connectorAuth.replace("{connectorId}", "xxxxxxxxxx"), testName);
				
		//check status
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	/**
	 * check request with body NULL to post, err:00105
	 */
	@Test
	public void testPostConnectorsAuthNeg2() { 
		
		testName="testPostConnectorsAuthNeg2";
		
		//post auth
		response=util.executePost(reqUrl, testName);
				
		//check status
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	/**
	 * check request with extra nodes to post, err:00105
	 */
	@Test
	public void testPostConnectorsAuthNeg3() { 
		
		testName="testPostConnectorsAuthNeg3";
		
		//post auth
		response=util.executePost(reqUrl, testName);
				
		//check status
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	/**
	 * check request with extra parameters to post, err:00105
	 */
	@Test
	public void testPostConnectorsAuthNeg4() { 
		
		testName="testPostConnectorsAuthNeg4";
		
		//post auth
		response=util.executePost(reqUrl, testName);
				
		//check status
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	/**
	 * check request with missing parameters "username" to post, err:00107
	 */
	@Test
	public void testPostConnectorsAuthNeg5() { 
		
		testName="testPostConnectorsAuthNeg5";
		
		//post auth
		response=util.executePost(reqUrl, testName);
				
		//check status
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	/**
	 * check request with missing parameters "password" to post, err:00107
	 */
	@Test
	public void testPostConnectorsAuthNeg6() { 
		
		testName="testPostConnectorsAuthNeg6";
		
		//post auth
		response=util.executePost(reqUrl, testName);
				
		//check status
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	/**
	 * check request with invalid parameters "username" to post, err:00104
	 */
	@Test
	public void testPostConnectorsAuthNeg7() { 
		
		testName="testPostConnectorsAuthNeg7";
		
		//post auth
		response=util.executePost(reqUrl, testName);
				
		//check status
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
	/**
	 * check request with invalid parameters "password" to post, err:00104
	 */
	@Test
	public void testPostConnectorsAuthNeg8() { 
		
		testName="testPostConnectorsAuthNeg8";
		
		//post auth
		response=util.executePost(reqUrl, testName);
				
		//check status
		util.checkStatus(response, testName);
		
		//check errCode
		util.checkResponseNode(response, testName, "ERRORCODE");
	}
	
}
