package com.oracle.bdd.util;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import net.sf.json.JSONObject;



public class CommonUtil {
	private WebResource webRes; 
	private ClientResponse response;
	private String output;
	private Map<String, String> xmlMap;
	
	
	/**
	 * execute POST API
	 * @param client 		Client
	 * @param requestUrl	String
	 * @param xmlName		String
	 * @param testName		String
	 * @return
	 */
	public ClientResponse executePost(Client client,String requestUrl,String xmlName,String testName){
		
		webRes = client.resource(requestUrl);
		
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		String reqJson = xmlMap.get("REQUESTJSON");	
		//System.out.println(reqJson);
		
		//execute POST
		response =webRes.type("application/json").acceptLanguage("en-US").post(ClientResponse.class, reqJson);
		
		return response;
	}
	
	
	/**
	 * execute GET API
	 * @param client Client
	 * @param requestUrl	String
	 * @return
	 */
	public ClientResponse executeGet(Client client,String requestUrl){
		webRes = client.resource(requestUrl);
		//System.out.println(reqJson);				
		
		//execute GET
		response = webRes.acceptLanguage("en-US").get(ClientResponse.class);
	
		return response;
	}
	
	
	/**
	 * execute DELETE API
	 * @param client	Client
	 * @param requestUrl	String
	 * @return
	 */
	public ClientResponse executeDelete(Client client,String requestUrl){
		webRes = client.resource(requestUrl);	
		
		//execute DELETE
		response = webRes.acceptLanguage("en-US").delete(ClientResponse.class);
		
		return response;
	}
	
	
	/**
	 * check response status and response json (optional) replace %ConnectorId%
	 * @param response	ClientResponse
	 * @param xmlName	String
	 * @param testName	String
	 */
	public void checkResponse(ClientResponse response,String xmlName,String testName){
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		String testname = xmlMap.get("testname");
		String status = xmlMap.get("STATUS");
		System.out.println("status======="+response.getStatus());
		
		assertEquals(testname+" response status is not "+status,Integer.parseInt(status), response.getStatus());	//check stauts 
		
		if(xmlMap.containsKey("RESPONSEJSON")){			//check response match
			String expectedResponse = xmlMap.get("RESPONSEJSON").trim();	
			
			if(expectedResponse.contains("%ConnectorId%")){
				expectedResponse = expectedResponse.replace("%ConnectorId%", getConnectorId(response));
			}
			
			output = response.getEntity(String.class);
			System.out.println(expectedResponse);
			System.out.println("=======================");
			System.out.println(output);
			assertEquals(testname+" response and expectation are different",expectedResponse,output);
		}
	} 
	
	
	/**
	 * get connectorId from response json
	 * @param response	ClientResponse
	 * @return
	 */
	public String getConnectorId(ClientResponse response){
		JSONObject jsStr = JSONObject.fromObject(response.getEntity(String.class)); 			 
		String connectorId =jsStr.getString("id");	
		return connectorId;
	}
	
	/**
	 * delete all connecters
	 */
	public void cleanConnectors(){}
	
}
