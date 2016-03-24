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
	
	CommonUtil comUtil = new CommonUtil(client, xmlName, DeleteConnectsByIdNegTest.class);
				
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