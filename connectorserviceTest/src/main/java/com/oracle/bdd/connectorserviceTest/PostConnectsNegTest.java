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


public class PostConnectsNegTest {
	
	private static Client client = Client.create();;
	private String reqUrl = Constants.connectors;
	private String xmlName = "PostConnectsNegTest.xml";
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
	 * create a new connector with wrong connectorType format
	 */
	@Test
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
	@Test
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
	@Test
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
	@Test
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
	@Test
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
	@Test
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
	@Test
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
	@Test
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
	@Test
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
	@Test
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
	@Test
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
	@Test
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
	@Test
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
	@Test
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
	@Test
	public void testPostconnectorsNeg23() {
		testName = "testPostconnectors23";
		
		//post connector		
		responseMap = comUtil.executePost(reqUrl,testName);
		comUtil.checkStatus(responseMap, testName);
		comUtil.checkResponseNode(responseMap, testName, "ERRORCODE");
	}

}