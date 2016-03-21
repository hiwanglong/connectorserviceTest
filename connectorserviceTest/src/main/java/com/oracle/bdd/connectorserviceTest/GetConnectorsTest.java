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


public class GetConnectorsTest {
	
	
	Client client = Client.create();
	String testFile="GetConnectorsTest.xml";
	CommonUtil util=new CommonUtil(client, testFile);
	String reqUrl=Constants.connectors;
	int count;
	String res, testCase;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Map<String, String> response=util.executeGet(reqUrl);
		res = response.get("jsonRes");
		count=res.split("id").length-1;
        if (count!=0){
        	//delete all connectors
        	util.cleanConnectors();
        }
	}

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	public void testGetConnectorsTest1() { // check get connectors with item=1
		
		testCase="testGetConnectorsTest1";
		
		// post a connector
		util.executePost(reqUrl,testCase);
		
		// get connectors
		util.executeGet(reqUrl);
		
        // check count=1
		count=res.split("id").length-1;
		assertEquals("Connectors' number is NOT 1: ",1,count);
	   
	}
	
	@Test
	public void testGetConnectorsTest2() { // check get connectors with item=2 
		
		testCase="testGetConnectorsTest2";
		
		// post two connectors
		util.executePost(reqUrl,testCase);
		
		// get connectors
		util.executeGet(reqUrl);
		
        // check count=2
		count=res.split("id").length-1;
		assertEquals("Connectors' number is NOT 2: ",2,count);
	   
	}
	
	@Test
	public void testGetConnectorsTest3() { // check get connectors with item=0
		
		// no connectors have been posted
		
		// get connectors
		util.executeGet(reqUrl);
		
        // check count=0
		count=res.split("id").length-1;
		assertEquals("Connectors' number is NOT 0: ",0,count);
	   
	}
	
	


}
