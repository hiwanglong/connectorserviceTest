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


public class GetConnectsByIdTest{
	
	private static Client client = Client.create();;
	private String reqUrl = Constants.connectors;
	private String testName;
	private Map<String, String> responseMap ;
	private String xmlName = "GetConnectsByIdTest.xml";
	
	CommonUtil comUtil = new CommonUtil(client, xmlName);
	
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
		//delete connector by id after each test
		comUtil.executeDelete(reqUrl+"/"+comUtil.getConnectorId(responseMap.get("jsonRes")).get(0));
	}
	
	/**
	 * delete connector via correct connectorId
	 */
	@Test
	public void testDeleteConnectorsById1() {
		testName = "getconnectorsById1";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		
		//get connector by id
		comUtil.executeGet(reqUrl+"/"+comUtil.getConnectorId(responseMap.get("jsonRes")).get(0));
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponse(responseMap, testName);
	}

}