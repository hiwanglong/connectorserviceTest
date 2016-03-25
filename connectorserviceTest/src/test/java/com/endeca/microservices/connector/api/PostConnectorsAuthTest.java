package com.endeca.microservices.connector.api;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;

public class PostConnectorsAuthTest {

	static Client client = Client.create();
	static String testFile="PostConnectorsAuthTest.xml";
	static CommonUtil util=new CommonUtil(client, testFile, PostConnectorsAuthTest.class);
	static String reqUrl, connectorId;
	String testName;
	Map<String, String> response;
	
	@BeforeClass(alwaysRun = true)
	public static void setUpBeforeClass() throws Exception {
		
		//post connector 
		String connectorRes=util.executePost(Constants.connectors, "setUpPostConnectorsAuth").get("jsonRes");
						
	    //get connector id
		connectorId=util.getConnectorId(connectorRes).get(0);
				
		//update connectorAuth reqUrl	
		reqUrl=Constants.connectorAuth.replace("{connectorId}",connectorId);
		
		
	}

	@AfterClass(alwaysRun = true)
	public static void tearDownAfterClass() throws Exception {
		
		//delete connector
		String delUrl=Constants.connectorId.replace("{connectorId}",connectorId);
		util.executeDelete(delUrl);
		
	}


	/**
	 * check request with valid body to post, status:200
	 */
	@Test(groups = {"Functional"})
	public void testPostConnectorsAuthTest1() {
		
		testName="testPostConnectorsAuth1";
		
		//post auth request
		response=util.executePost(reqUrl, testName);
		
		//check status
		util.checkStatus(response, testName);
	}

}
