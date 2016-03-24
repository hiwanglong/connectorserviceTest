package com.endeca.microservices.connector.api;

import java.util.Map;

import org.junit.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;


public class DeleteConnectsByIdNegTest {
	
	private static Client client = Client.create();;
	private String reqUrl = Constants.connectors;
	private String xmlName = "DeleteConnectsByIdNegTest.xml";
	private String testName;
	private Map<String, String> responseMap ;
	
	CommonUtil comUtil = new CommonUtil(client, xmlName);
				
	/**
	 * delete connector via wrong connectorId
	 */
	@Test
	public void testDeleteConnectorsById2() {
		testName = "deleteconnectorsById2";
				
		//delete connector by id
		responseMap = comUtil.executeDelete(reqUrl+"/"+"12345-0000-abc");
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * delete connector via null connectorId
	 */
	@Test
	public void testDeleteConnectorsById3() {
		testName = "deleteconnectorsById3";
				
		//delete connector by id
		responseMap = comUtil.executeDelete(reqUrl+"/");
		comUtil.checkStatus(responseMap, testName);
	}
		
}