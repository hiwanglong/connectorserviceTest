package com.oracle.bdd.connectorserviceTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oracle.bdd.util.CommonUtil;
import com.oracle.bdd.util.Constants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;


public class PostConnectsTest {
	private static Client client = Client.create();;
	private ClientResponse response;
	private String reqUrl =Constants.connectorUrl+Constants.connectors;
	private String xmlName = "PostConnectsTest.xml";
	private String language = "en-US";
	private String testname;
	CommonUtil comUtil = new CommonUtil(client, xmlName, language);
	
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
		//delete connector by id
		//response = comUtil.executeDelete(client, reqUrl+"/"+comUtil.getConnectorId(response));
	}
	
	@Test
	public void testPOSTconnectors1() {
		testname = "testPostconnectors1";
		
		//post connector		
		
		response = comUtil.executePost(reqUrl,testname);
		comUtil.checkResponse(response,testname);
		
	}

	@Test
	public void test_firstC2() {
		String first = "First";
		assertEquals("First", first);
	}
	
	
	
}