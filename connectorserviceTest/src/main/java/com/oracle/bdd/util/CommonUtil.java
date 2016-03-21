package com.oracle.bdd.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;



public class CommonUtil {
	private WebResource webRes; 
	private ClientResponse response;
	private Client client;
	private Map<String, String> xmlMap;
	private String output;
	private String xmlName;
	private String language;
	
	public CommonUtil(Client client,String xmlName,String language) {
		this.client = client;
		this.xmlName = xmlName;
		this.language = language;
	}
	
	/**
	 * execute POST API
	 * @param client 		Client
	 * @param requestUrl	String
	 * @param xmlName		String
	 * @param testName		String
	 * @return
	 */
	public ClientResponse executePost(String requestUrl,String testName){		
		webRes = client.resource(requestUrl);
		
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		String[] reqJsonArr = xmlMap.get("REQUESTJSON").split(";");	
//		System.out.println("1============"+reqJsonArr[0]);
//		System.out.println("2==================="+language);
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
	public ClientResponse executeGet(String requestUrl){
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
	public ClientResponse executeDelete(String requestUrl){
		webRes = client.resource(requestUrl);	
		
		//execute DELETE
		response = webRes.acceptLanguage(language).delete(ClientResponse.class);
		
		return response;
	}
	

	/**
	 * delete all posted connectors
	 * @param client	Client
	 * @param getUrl	String, eg:Constants.connectors
	 * @param delUrl	String, eg:Constants.connectorId
	 */
	public void cleanConnectors(String getUrl, String delUrl){ 
	
		//get all connectors' id
		List <String> connectotIds=getConnectorId(executeGet(getUrl).getEntity(String.class));
		
		//delete all connectors one by one
		for (int i=0; i<connectotIds.size();i++){
			delUrl=delUrl.replace("{connectorId}",connectotIds.get(i) );
			executeDelete(delUrl);
		}	
	}
	
	
	/**
	 * check response status and response json (optional) replace %ConnectorId%
	 * @param response	ClientResponse
	 * @param xmlName	String
	 * @param testName	String
	 */
	public void checkResponse(ClientResponse response,String testName){
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		String testname = xmlMap.get("testname");
		String status = xmlMap.get("STATUS");
		
		assertEquals(testname+" response status is not "+status,Integer.parseInt(status), response.getStatus());	//check stauts 
		
		if(xmlMap.containsKey("RESPONSEJSON")){			//check response match
			output = response.getEntity(String.class);
			String expectedResponse = GetResourceXML.trimAllSpaces(xmlMap.get("RESPONSEJSON"));	
			
			if(expectedResponse.contains("%connectorId%")){
				expectedResponse = expectedResponse.replace("%connectorId%", getConnectorId(output).get(0));
			}
			assertEquals(testname+" response and expectation are different",expectedResponse,GetResourceXML.trimAllSpaces(output));
		}
	} 
	
	

	/**
	 * get connectors' id from response
	 * @param response
	 * @return connectorsIds
	 */

	public List<String> getConnectorId(String jsonResponse){

		List<String> connectorIds=new ArrayList<String>();
	
		JsonParser parser = new JsonParser(jsonResponse);
		if (jsonResponse.contains("items")){ 
			int num=parser.arrayElemSize(parser.jsonObject, "items");
			for (int i=0; i<num; i++){
				connectorIds.add(parser.parser(parser.jsonObject, "items["+i+"].connectorId").toString());
			}
		}
		else{
			connectorIds.add(parser.parser(parser.jsonObject, "id").toString());
		}
		return connectorIds;
	}
	
}