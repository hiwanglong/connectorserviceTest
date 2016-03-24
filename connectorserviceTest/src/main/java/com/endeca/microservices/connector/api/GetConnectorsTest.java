package com.endeca.microservices.connector.api;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.endeca.microservices.connector.util.JsonParser;
import com.sun.jersey.api.client.Client;


public class GetConnectorsTest {
	
	
	Client client = Client.create();
	String testFile="GetConnectorsTest.xml";
	CommonUtil util=new CommonUtil(client, testFile);
	String reqUrl=Constants.connectors;
	int count;
	String res, testCase;
	JsonParser parser;

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
	    parser = new JsonParser(res);
		count=parser.arrayElemSize(parser.jsonObject, "items");
        if (count!=0){
        	//delete all connectors
        	util.cleanConnectors();
        }
	}

	@After
	public void tearDown() throws Exception {
		
	}
	

	@Test
	public void testGetConnectorsTest1() { // check get connectors with items=1
		
		testCase="testGetConnectorsTest1";
		
		// post a connector
		util.executePost(reqUrl,testCase);
		
		// get connectors
		res=util.executeGet(reqUrl).get("jsonRes");
		parser = new JsonParser(res);
		count=parser.arrayElemSize(parser.jsonObject, "items");
		
        // check count=1
		assertEquals("Connectors' number is NOT 1: ",1,count);
	   
	}
	
	@Test
	public void testGetConnectorsTest2() { // check get connectors with items=2 
		
		testCase="testGetConnectorsTest2";
		
		// post two connectors
		util.executePostBatch(reqUrl,testCase);
		
		// get connectors
		res=util.executeGet(reqUrl).get("jsonRes");
		parser = new JsonParser(res);
		count=parser.arrayElemSize(parser.jsonObject, "items");
		
        // check count=2
		assertEquals("Connectors' number is NOT 2: ",2,count);
	   
	}
	
	@Test
	public void testGetConnectorsTest3() { // check get connectors with items=0
		
		// no connectors have been posted
		
		// get connectors
		res=util.executeGet(reqUrl).get("jsonRes");
		parser = new JsonParser(res);
		count=parser.arrayElemSize(parser.jsonObject, "items");
		
        // check count=0
		assertEquals("Connectors' number is NOT 0: ",0,count);
	   
	}
}
