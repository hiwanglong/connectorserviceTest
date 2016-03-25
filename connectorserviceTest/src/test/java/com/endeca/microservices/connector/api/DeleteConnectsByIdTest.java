package com.endeca.microservices.connector.api;

import java.util.Map;

import org.testng.annotations.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;


public class DeleteConnectsByIdTest {
	
	private static Client client = Client.create();;
	private String reqUrl = Constants.connectors;
	private String testName;
	private Map<String, String> responseMap ;
	private String xmlName = "DeleteConnectsByIdTest.xml";
	
	CommonUtil comUtil = new CommonUtil(client, xmlName, DeleteConnectsByIdTest.class);
	
	/**
	 * delete connector via correct connectorId
	 */
	@Test(groups = {"Functional"})
	public void testDeleteConnectorsById1() {
		testName = "deleteconnectorsById1";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		
		//delete connector by id
		comUtil.executeDelete(reqUrl+"/"+comUtil.getConnectorId(responseMap.get("jsonRes")).get(0));
		comUtil.checkStatus(responseMap, testName);
	}
}