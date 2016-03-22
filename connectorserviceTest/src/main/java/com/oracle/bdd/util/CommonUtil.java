package com.oracle.bdd.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
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
	private Map<String, String> responseMap = new HashMap<String, String>();;
	
	public CommonUtil(Client client,String xmlName,String language) {
		this.client = client;
		this.xmlName = xmlName;
		this.language = language;
	}
	
	public CommonUtil(Client client,String xmlName) {
		this.client = client;
		this.xmlName = xmlName;
		this.language = "en-US";		 
	}
	
	/**
	 * execute POST API
	 * @param requestUrl	String
	 * @param testName		String
	 * @return	Map<String, String> responseMap status,jsonRes
	 */
	public Map<String, String> executePost(String requestUrl,String testName){		
		webRes = client.resource(requestUrl);
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		String reqJson = xmlMap.get("REQUESTJSON");	
		System.out.println("execute POST "+requestUrl);
		
		response =webRes.type("application/json").acceptLanguage(language).post(ClientResponse.class, reqJson);
		return getResponseMap(response);
	}
	
	
	
	/**
	 * execute GET API
	 * @param requestUrl	String
	 * @return Map<String, String> responseMap status,jsonRes
	 */
	public Map<String, String> executeGet(String requestUrl){
		webRes = client.resource(requestUrl);			
		System.out.println("execute GET "+requestUrl);
		
		//execute GET
		response = webRes.acceptLanguage(language).get(ClientResponse.class);
	
		return getResponseMap(response);
	}
	
	
	/**
	 * execute DELETE API
	 * @param requestUrl	String
	 * @return Map<String, String> responseMap status,jsonRes
	 */
	public Map<String, String>  executeDelete(String requestUrl){
		webRes = client.resource(requestUrl);
		System.out.println("execute DELETE "+requestUrl);
		
		//execute DELETE
		response = webRes.acceptLanguage(language).delete(ClientResponse.class);
		
		return getResponseMap(response);
	}
	

	/**
	 * delete all posted connectors
	 */
	public void cleanConnectors(){ 
	
		//get all connectors' id	
		List <String> connectotIds=getConnectorId(executeGet(Constants.connectors).get("jsonRes"));
		
		//delete all connectors one by one
		for (int i=0; i<connectotIds.size();i++){
			String delUrl=Constants.connectorId.replace("{connectorId}",connectotIds.get(i) );
			executeDelete(delUrl);
		}	
	}
	
	/**
	 * execute POST batch
	 * @param requestUrl	String
	 * @param testName	String
	 */
	public void executePostBatch(String requestUrl,String testName){		
		webRes = client.resource(requestUrl);
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		String[] reqJsonArr = xmlMap.get("REQUESTJSON").split(";");	
		
		System.out.println("execute POST batch");
		for(String reqJson : reqJsonArr){
			//execute POST
			webRes.type("application/json").acceptLanguage(language).post(ClientResponse.class, reqJson);
		}
	}
	
	
	/**
	 * check response json with replace %ConnectorId% RESPONSEJSON of xml
	 * @param responseMap	Map<String,String>
	 * @param testName	String
	 */
	public void checkResponse(Map<String,String> responseMap,String testName){
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		
		output = responseMap.get("jsonRes");
		System.out.println(output);
		
		String expectedResponse = GetResourceXML.trimAllSpaces(xmlMap.get("RESPONSEJSON"));	
		
		if(expectedResponse.contains("%connectorId%")){
			expectedResponse = expectedResponse.replace("%connectorId%", getConnectorId(output).get(0));
		}
		assertEquals(testName+" response and expectation are different",expectedResponse,GetResourceXML.trimAllSpaces(output));
		
	} 
	
	/**
	 * check response status with STATUS of xml
	 * @param responseMap	Map<String,String>
	 * @param testName	String
	 */
	public void checkStatus(Map<String,String> responseMap,String testName){
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		String status = xmlMap.get("STATUS");
		System.out.println("status==========="+status);
		
		assertEquals(testName+" response status is not "+status, status,responseMap.get("status"));	//check status 
	}
	
	/**
	 * check if response contains node element of xml
	 * @param responseMap	Map<String,String>
	 * @param testName	String
	 * @param nodeName	String	xml node
	 */
	public void checkResponseNode(Map<String,String> responseMap,String testName,String nodeName){
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		
		output = responseMap.get("jsonRes");
		System.out.println(output);
		
		String nodeElement = xmlMap.get(nodeName);	
		
		assertTrue(testName+" response doesn't contains "+ nodeElement,output.contains(nodeElement));	
		
	}
	

	/**
	 * get connectors' id from response
	 * @param response String, a Json String Response
	 * @return List<String> connectorsIds
	 */

	public List<String> getConnectorId(String response){

		List<String> connectorIds=new ArrayList<String>();
	
		JsonParser parser = new JsonParser(response);
		if (response.contains("items")){ 
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
	
	/**
	 * encapsulate response
	 * @param response
	 * @return Map<String, String> status,jsonRes
	 */
	public Map<String, String> getResponseMap(ClientResponse response){
		String responseStatus = response.getStatus()+"";
		responseMap.put("status", responseStatus);
		if(!"204".equals(responseStatus)){
			responseMap.put("jsonRes", response.getEntity(String.class));
		}		
		response.close();
		return responseMap;
	}
}