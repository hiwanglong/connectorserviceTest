package com.endeca.microservices.connector.api;

import java.util.Map;

import org.testng.annotations.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.sun.jersey.api.client.Client;



public class GetConnectorTypesTest {
	
	
	Client client = Client.create();
	String testFile="GetConnectorTypesTest.xml";
	CommonUtil util=new CommonUtil(client, testFile,GetConnectorTypesTest.class);
	String reqUrl=Constants.connectorTypes;
	Map<String, String> response;

	
	@Test(groups = {"Functional"})
	public void testGetConnectorTypes(){
	
		String testCase="testGetConnectorTypes";
		
		//get response
	    response=util.executeGet(reqUrl);
	    
	    //check status
	    util.checkStatus(response, testCase);
	   		
		//check response
	    util.checkResponse(response, testCase); 
	   
	}
	
	
}
