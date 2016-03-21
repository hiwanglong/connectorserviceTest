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



public class GetConnectorTypesTest {
	
	
	Client client = Client.create();
	String testFile="GetConnectorTypesTest.xml";
	CommonUtil util=new CommonUtil(client, testFile);
	

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
		
		String reqUrl=Constants.connectorTypes;
		String testCase="testGetConnectorTypes";
		
		//get connector types
	    Map<String, String>response=util.executeGet(reqUrl);
	   		
		//check get response
	    util.checkResponse(response, testCase); 
	   
	}
	
	
}
