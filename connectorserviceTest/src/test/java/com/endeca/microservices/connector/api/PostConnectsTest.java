package com.endeca.microservices.connector.api;

import java.util.Map;

import org.junit.After;
import org.junit.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;


public class PostConnectsTest {
	
	private static Client client = Client.create();;
	private String reqUrl = Constants.connectors;
	private String xmlName = "PostConnectsTest.xml";
	private String testName;
	private Map<String, String> responseMap ;
	
	CommonUtil comUtil = new CommonUtil(client, xmlName, PostConnectsTest.class);
	
	@After
	public void tearDown() throws Exception {
		//delete connector by id after each test
		comUtil.executeDelete(reqUrl+"/"+comUtil.getConnectorId(responseMap.get("jsonRes")).get(0));		
	}
	
	/**
	 * 	create a new connector ,connectorType : CloudStorage ,visibility : import,blackList: rule123
	 */
	@Test
	public void testPOSTconnectors1() {
		testName = "testPostconnectors1";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponse(responseMap,testName);
	}
	
	/**
	 * create a new connector , connectorType : CloudStorage , visibility : export, whiteList: rules456
	 */
	@Test
	public void testPOSTconnectors2() {
		testName = "testPostconnectors2";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponse(responseMap,testName);
	}
	
	/**
	 * 	create a new connector , connectorType : CloudStorage , visibility : import,export, whiteList: rules456" ,blackList: rule123
	 */
	@Test
	public void testPOSTconnectors3() {
		testName = "testPostconnectors3";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponse(responseMap,testName);
	}
	
	/**
	 * create a new connector without whitelist and blacklist,visibility : import,export
	 */
	@Test
	public void testPOSTconnectors4() {
		testName = "testPostconnectors4";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponse(responseMap,testName);
	}
	
	/**
	 * 	create a new connector with ,"visibility" : []
	 */
	@Test
	public void testPOSTconnectors5() {
		testName = "testPostconnectors5";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponse(responseMap,testName);
	}
	
	/**
	 * create a new connector with wrong storageServiceUrl & username and password
	 */
	@Test
	public void testPOSTconnectors6() {
		testName = "testPostconnectors6";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponse(responseMap,testName);
	}
	
	/**
	 * 	create a new connector without visibility
	 */
	@Test
	public void testPOSTconnectors7() {
		testName = "testPostconnectors7";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponse(responseMap,testName);
	}
	
	/**
	 * create a new connector with required parameter
	 */
	@Test
	public void testPOSTconnectors8() {
		testName = "testPostconnectors8";
			
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponse(responseMap,testName);
	}
}