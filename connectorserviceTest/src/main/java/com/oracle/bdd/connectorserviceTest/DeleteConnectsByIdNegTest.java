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


public class DeleteConnectsByIdNegTest {
	
	private static Client client = Client.create();;
	private String reqUrl = Constants.connectors;
	private String xmlName = "DeleteConnectsByIdNegTest.xml";
	private String testName;
	private Map<String, String> responseMap ;
	
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
	}
	
		
	/**
	 * delete connector via wrong connectorId
	 */
	@Test
	public void testDeleteConnectorsById2() {
		testName = "deleteconnectorsById2";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		
		//delete connector by id
		comUtil.executeDelete(reqUrl+"/"+comUtil.getConnectorId(responseMap.get("jsonRes")).get(0)+000000);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * delete connector via null connectorId
	 */
	@Test
	public void testDeleteConnectorsById3() {
		testName = "deleteconnectorsById3";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		
		//delete connector by id
		comUtil.executeDelete(reqUrl+"/");
		comUtil.checkStatus(responseMap, testName);
	}
		
}