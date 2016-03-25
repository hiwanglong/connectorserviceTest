package com.endeca.microservices.connector.api;

import java.util.Map;

import org.testng.annotations.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;


public class PostConnectsNegTest {
	
	private static Client client = Client.create();;
	private String reqUrl = Constants.connectors;
	private String xmlName = "PostConnectsNegTest.xml";
	private String testName;
	private Map<String, String> responseMap ;
	
	CommonUtil comUtil = new CommonUtil(client, xmlName, PostConnectsNegTest.class);
	
	/**
	 * create a new connector with wrong connectorType format
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg9() {
		testName = "testPostconnectors9";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * create a new connector with incorrect json format
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg10() {
		testName = "testPostconnectors10";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * create a new connector with missing password parameter
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg11() {
		testName = "testPostconnectors11";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * create a new connector with missing storageServiceUrl parameter
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg12() {
		testName = "testPostconnectors12";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * create a new connector with missing parameter(username)
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg13() {
		testName = "testPostconnectors13";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * create a new connector with incorrect storageServiceUrl format
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg14() {
		testName = "testPostconnectors14";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * create a new connector with too long storageServiceUrl parameter
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg15() {
		testName = "testPostconnectors15";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * create a new connector with too long username parameter
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg16() {
		testName = "testPostconnectors16";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * create a new connector with too long password parameter
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg17() {
		testName = "testPostconnectors17";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * 	create a new connector with too long whiteList parameter
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg18() {
		testName = "testPostconnectors18";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * create a new connector with too long blackList parameter
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg19() {
		testName = "testPostconnectors19";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * 	create a new connector with wrong visibility parameter
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg20() {
		testName = "testPostconnectors20";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * create a new connector with extra parameter in the parameters{}
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg21() {
		testName = "testPostconnectors21";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * create a new connector with extra parameter
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg22() {
		testName = "testPostconnectors22";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * 	create a new connector with too short password
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg23() {
		testName = "testPostconnectors23";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * 	create a new connector with missing comma
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg24() {
		testName = "testPostconnectors24";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * 	create a new connector with null
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg25() {
		testName = "testPostconnectors25";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}
	
	/**
	 * 	create a new connector with empty {}
	 */
	@Test(groups = {"Negative"})
	public void testPostconnectorsNeg26() {
		testName = "testPostconnectors26";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}

}