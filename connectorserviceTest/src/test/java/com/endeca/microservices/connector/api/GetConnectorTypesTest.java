package com.endeca.microservices.connector.api;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;



public class GetConnectorTypesTest {
	
	
	Client client = Client.create();
	String testFile="GetConnectorTypesTest.xml";
	CommonUtil util=new CommonUtil(client, testFile,GetConnectorTypesTest.class);
	String reqUrl=Constants.connectorTypes;
	Map<String, String> response;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testGetConnectorTypes(){
	
		String testCase="testGetConnectorTypes";
		
		//get response
	    response=util.executeGet(reqUrl);
	    
	    //check status
	    util.checkStatus(response, testCase);
	   		
		//check response
	    util.checkResponse(response, testCase); 
	   
	}
	
	
}
