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
	public ClientResponse executePost(Client client,String requestUrl,String xmlName,String testName,String language){
		
		webRes = client.resource(requestUrl);
		
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		String[] reqJsonArr = xmlMap.get("REQUESTJSON").split(";");	
		//System.out.println(reqJson);
		
		for(String reqJson : reqJsonArr){
			//execute POST
			response =webRes.type("application/json").acceptLanguage(language).post(ClientResponse.class, reqJson);
		}
		
		return response;
	}
	
	
	/**
	 * execute GET API
	 * @param client Client
	 * @param requestUrl	String
	 * @return
	 */
	public ClientResponse executeGet(Client client,String requestUrl,String language){
		webRes = client.resource(requestUrl);
		//System.out.println(reqJson);				
		
		//execute GET
		response = webRes.acceptLanguage(language).get(ClientResponse.class);
	
		return response;
	}
	
	
	/**
	 * execute DELETE API
	 * @param client	Client
	 * @param requestUrl	String
	 * @return
	 */
	public ClientResponse executeDelete(Client client,String requestUrl,String language){
		webRes = client.resource(requestUrl);	
		
		//execute DELETE
		response = webRes.acceptLanguage(language).delete(ClientResponse.class);
		
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
		//System.out.println("status======="+response.getStatus());
		
		assertEquals(testname+" response status is not "+status,Integer.parseInt(status), response.getStatus());	//check stauts 
		
		if(xmlMap.containsKey("RESPONSEJSON")){			//check response match
			output = response.getEntity(String.class);
			
			String expectedResponse = GetResourceXML.trimAllSpaces(xmlMap.get("RESPONSEJSON"));	
			
			if(expectedResponse.contains("%connectorId%")){
				expectedResponse = expectedResponse.replace("%connectorId%", getConnectorId(output));
			}
			
			/*System.out.println(expectedResponse);
			System.out.println("=======================");
			System.out.println(output);*/
			assertEquals(testname+" response and expectation are different",expectedResponse,GetResourceXML.trimAllSpaces(output));
		}
	} 
	
	
	/**
	 * get connectorId from response json
	 * @param response	ClientResponse
	 * @return
	 */
	public String getConnectorId(String responseJson){
		JSONObject jsStr = JSONObject.fromObject(responseJson); 			 
		String connectorId =jsStr.getString("id");	
		return connectorId;
	}
	
	/**
	 * delete all connecters
	 */
	public void cleanConnectors(){}
	
}
