package com.oracle.bdd.connectorserviceTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oracle.bdd.util.CommonUtil;
import com.oracle.bdd.util.Constants;
import com.oracle.bdd.util.GetResourceXML;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GetConnectorsTest {
	

	CommonUtil util=new CommonUtil();
	Client client = Client.create();
	String testFile=".xml";
	String reqUrl=Constants.connectors;
	String delUrl=Constants.connectorId;
	int count;
	String res;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ClientResponse response=util.executeGet(client,reqUrl);
		res = response.getEntity(String.class);
		count=res.split("id").length-1;
        if (count!=0){
        	//delete all connectors
        	util.cleanConnectors(client, reqUrl, delUrl);
        }
	}

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	public void testGetConnectorsTest1() {
		
		String testCase="testGetConnectorsTest1";
		
		// post a connector
		util.executePost(client, reqUrl, testFile, testCase);
		
		// get connectors
		util.executeGet(client, reqUrl);
		
        // check count=1
		count=res.split("id").length-1;
		assertEquals("Connectors' number is NOT 1: ",1,count);
	   
	}
	
	@Test
	public void testGetConnectorsTest2() { 
		
		String testCase="testGetConnectorsTest2";
		
		// post two connectors
		util.executePost(client, reqUrl, testFile, testCase);
		
		// get connectors
		util.executeGet(client, reqUrl);
		
        // check count=2
		count=res.split("id").length-1;
		assertEquals("Connectors' number is NOT 2: ",2,count);
	   
	}
	
	@Test
	public void testGetConnectorsTest3() {
		
		// no connectors have been posted
		
		// get connectors
		util.executeGet(client, reqUrl);
		
        // check count=0
		count=res.split("id").length-1;
		assertEquals("Connectors' number is NOT 0: ",0,count);
	   
	}
	
	


}
