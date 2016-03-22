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

public class PostConnectorsAuthTest {

	static Client client = Client.create();
	static String testFile="PostConnectorsAuthTest.xml";
	static CommonUtil util=new CommonUtil(client, testFile);
	static String reqUrl;
	static String testCase, connectorId;
	Map<String, String> response;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//post connector 
		String connectorRes=util.executePost(Constants.connectors, "setUpPostConnectorsAuth").get("jsonRes");
						
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
	public void testPostConnectorsAuthTest1() {
		
		String testName="testPostConnectorsAuth1";
		
		//post auth
		response=util.executePost(reqUrl, testName);
		
		//check response
		util.checkStatus(response, testName);
		
	}

}
