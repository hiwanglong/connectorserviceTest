package com.endeca.microservices.connector.api;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;

public class BrowseObjectByIdTest {
	
	
	static Client client = Client.create();
	static String testFile="BrowseObjectByIdTest.xml";
	static CommonUtil util=new CommonUtil(client, testFile, BrowseObjectByIdTest.class);
	String reqUrl, connectorId, token, browseUrl;
	String testName;
	Map<String, String> response;
	
	@BeforeClass(alwaysRun = true)
	public void setUpBeforeClass() throws Exception {
		
		//post connector 
		String connectorRes=util.executePost(Constants.connectors, "setUpBrowseObjectByIdToConnectorId").get("jsonRes");
						
	    //get connector id
		connectorId=util.getConnectorId(connectorRes).get(0);
				
		//update reqUrl	with connectorId
		reqUrl=Constants.connectorAuth.replace("{connectorId}",connectorId);
			
		//post auth
		String authRes=util.executePost(reqUrl, "setUpBrowseObjectByIdToAuth").get("jsonRes");
		
		//get token
		token=util.getToken(authRes);	
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDownAfterClass() throws Exception {
		
		//delete connector
		String delUrl=Constants.connectorId.replace("{connectorId}",connectorId);
		util.executeDelete(delUrl);
		
	}
	
	/**
	 * check response with filesize=0 status:200
	 */
	@Test(groups = {"Functional"})
	public void testBrowseObjectById1() {
		
		testName="testBrowseObjectById1";
		
		//update browserUrl with containerId
		browseUrl=reqUrl.replace("auth","containers/"+util.getXmlNode(testName, "CONTAINERID"));
		
		//browse(get) object request
		response=util.executeGet(browseUrl, token);
		
		//check status
		util.checkStatus(response, testName);
		
		//check regex
		util.checkResponseRegex(response, testName);
	}
	
	/**
	 * check response with filesize!=0 status:200
	 */
	@Test(groups = {"Functional"})
	public void testBrowseObjectById2() {
		
		testName="testBrowseObjectById2";
		
		//update browserUrl with containerId
		browseUrl=reqUrl.replace("auth","containers/"+util.getXmlNode(testName, "CONTAINERID"));
	
		//browse(get) object request
		response=util.executeGet(browseUrl, token);
		
		//check status
		util.checkStatus(response, testName);
		
		//check regex
		util.checkResponseRegex(response, testName);
	}
	

}
