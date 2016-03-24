package com.endeca.microservices.connector.api;

import java.util.Map;

import org.junit.After;
import org.junit.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;


public class GetConnectsByIdNegTest{
	
	private static Client client = Client.create();;
	private String reqUrl = Constants.connectors;
	private String testName;
	private Map<String, String> responseMap ;
	private String xmlName = "GetConnectsByIdNegTest.xml";
	private String connectorId;
	
	CommonUtil comUtil = new CommonUtil(client, xmlName);
	
	@After
	public void tearDown() throws Exception {
		//delete connector by id after each test
		comUtil.executeDelete(reqUrl+"/"+connectorId);
	}
	
	/**
	 * Get connector detail via wrong connectorId
	 */
	@Test
	public void testGetconnectorsById2() {
		testName = "getconnectorsById2";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		
		//get connector by id
		connectorId = comUtil.getConnectorId(responseMap.get("jsonRes")).get(0);
		responseMap = comUtil.executeGet(reqUrl+"/"+connectorId+"00abc00");
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}

}