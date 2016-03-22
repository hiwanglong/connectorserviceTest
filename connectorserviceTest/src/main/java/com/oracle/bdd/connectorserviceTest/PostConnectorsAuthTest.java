package com.oracle.bdd.connectorserviceTest;

import static org.junit.Assert.*;

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
		System.out.println(reqUrl);
		System.out.println("=============================================================");
		reqUrl=Constants.connectorAuth;
		System.out.println(reqUrl);
		System.out.println("=============================================================");
		reqUrl=Constants.connectorAuth.replace("{connectorId}",connectorId);
		System.out.println(reqUrl);
		System.out.println("=============================================================");
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//delete connector
		util.executeDelete(reqUrl);
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testPostConnectorsAuthTest1() {
		
//		String testName="testPostConnectorsAuthTest1";
//		
//		//post auth
//		System.out.println(reqUrl+"===========================================");
//		response=util.executePost(reqUrl, testName);
//		System.out.println(response.get("status"));
//		
//		//check response
//		util.checkStatus(response, testName);
		
	}

}
