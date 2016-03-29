package com.endeca.microservices.connector.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;



public class CommonUtil {
	Logger logger;
	private WebResource webRes; 
	private ClientResponse response;
	private Client client;
	private Map<String, String> xmlMap;
	private String xmlName;
	private String language;
	private Map<String, String> responseMap = new HashMap<String, String>();
	
	public CommonUtil(Client client,String xmlName,String language) {
		this.client = client;
		this.xmlName = xmlName;
		this.language = language;
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	public CommonUtil(Client client,String xmlName) {
		this(client, xmlName, "en-US");	 
	}
	
	public CommonUtil(Client client,String xmlName, Class<?> clazz) {
		this(client, xmlName, "en-US", clazz);	
	}
	
	public CommonUtil(Client client,String xmlName, String language, Class<?> clazz) {
		this.client = client;
		this.xmlName = xmlName;
		this.language = language;
		logger = LoggerFactory.getLogger(clazz);
	}
	
	/**
	 * execute POST API
	 * @param requestUrl	String
	 * @param testName		String
	 * @return	Map<String, String> responseMap status,jsonRes
	 */
	public Map<String, String> executePost(String requestUrl,String testName){		
		webRes = client.resource(requestUrl);
		logger.info("execute POST "+requestUrl);
		
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		String reqJson = xmlMap.get("REQUESTJSON");	
			
		response =webRes.type("application/json").acceptLanguage(language).post(ClientResponse.class, reqJson);
		return getResponseMap(response);
	}
	
	public Map<String, String> executePost(String requestUrl,String testName,String token){		
		
		return null;
	}
	
	/**
	 * execute PUT API
	 * @param requestUrl	String
	 * @param testName		String
	 * @return	Map<String, String> responseMap status,jsonRes
	 */
	public Map<String, String> executePut(String requestUrl,String testName){		
		webRes = client.resource(requestUrl);
		logger.info("execute PUT "+requestUrl);
		
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		String reqJson = xmlMap.get("REQUESTJSON");	
			
		response =webRes.type("application/json").acceptLanguage(language).put(ClientResponse.class, reqJson);
		return getResponseMap(response);
	}
	
	
	/**
	 * execute GET API
	 * @param requestUrl	String
	 * @return Map<String, String> responseMap status,jsonRes
	 */
	public Map<String, String> executeGet(String requestUrl){
		webRes = client.resource(requestUrl);
		logger.info("execute GET "+requestUrl);
		
		//execute GET
		response = webRes.acceptLanguage(language).get(ClientResponse.class);
	
		return getResponseMap(response);
	}
	
	
	/**
	 * execute GET API
	 * @param requestUrl	String
	 * @param token		String
	 * @return Map<String, String> responseMap status,jsonRes
	 */
	public Map<String, String> executeGet(String requestUrl,String token){		
		
		webRes = client.resource(requestUrl);
		logger.info("execute GET "+requestUrl);
		
		//execute GET
		response = webRes.header("X-Connector-Auth-Token", token).acceptLanguage(language).get(ClientResponse.class);
		//response = webRes.acceptLanguage(language).get(ClientResponse.class);
	
		return getResponseMap(response);
	}
	
	
	/**
	 * execute DELETE API
	 * @param requestUrl	String
	 * @return Map<String, String> responseMap status,jsonRes
	 */
	public Map<String, String>  executeDelete(String requestUrl){
		webRes = client.resource(requestUrl);
		logger.info("execute DELETE "+requestUrl);
		
		//execute DELETE
		response = webRes.acceptLanguage(language).delete(ClientResponse.class);
		
		return getResponseMap(response);
	}
	

	/**
	 * delete all posted connectors
	 * @param client	Client
	 */
	public void cleanConnectors(){ 
		logger.info("================== execute DELETE ALL =====================");
	
		//get all connectors' id	
		List <String> connectorIds=getConnectorId(executeGet(Constants.connectors).get("jsonRes"));
		
		//make sure instance including connectors
		if (connectorIds.size() == 0)
			return;
		
		//delete all connectors one by one
		for (int i=0; i<connectorIds.size();i++){
			String delUrl=Constants.connectorId.replace("{connectorId}",connectorIds.get(i) );
			
			//execute DELETE
			webRes = client.resource(delUrl);	
			response = webRes.acceptLanguage(language).delete(ClientResponse.class);
			response.close();
		}	
	}
	

	/**
	 *  post a batch of request
	 * @param requestUrl	String
	 * @param testName	String
	 */
	public void executePostBatch(String requestUrl,String testName){
		logger.info(testName + " do batch POST operation ...");
		webRes = client.resource(requestUrl);
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		String[] reqJsonArr = xmlMap.get("REQUESTJSON").split(";");	
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
		String output = responseMap.get("jsonRes");
		String expectedResponse = GetResourceXML.trimAllSpaces(xmlMap.get("RESPONSEJSON"));	
		if(expectedResponse.contains("%connectorId%")){
			expectedResponse = expectedResponse.replace("%connectorId%", getConnectorId(output).get(0));
		}
		
		logger.debug(testName);
		logger.debug("Actual Response:=====================================================");
		logger.debug(output);
		logger.debug("=====================================================================");
				
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
		
		logger.info(testName);
		logger.info("Actual status:"+responseMap.get("status"));
		logger.info("Expected status:"+status);
		
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
		String output = GetResourceXML.trimAllSpaces(responseMap.get("jsonRes"));	
		String nodeElement = GetResourceXML.trimAllSpaces(xmlMap.get(nodeName));	
		
		logger.debug(testName);
		logger.debug("Actual Node:=====================================================");
		logger.debug(output);
		logger.debug("=====================================================================");
		
		assertTrue(testName+" response doesn't contains "+ nodeElement,output.contains(nodeElement));	
		
	}
	
	/**
	 * check response with REGEX
	 * @param responseMap
	 * @param testName
	 */
	public void checkResponseRegex(Map<String,String> responseMap,String testName){
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		String output = GetResourceXML.trimAllSpaces(responseMap.get("jsonRes"));
		String expectedResponse = GetResourceXML.trimAllSpaces(xmlMap.get("RESPONSEJSON"));	

		logger.debug(testName);
		logger.debug("Actual Response:=====================================================");
		logger.debug(output);
		logger.debug("=====================================================================");
				
		assertTrue(testName+" response and expectation are different",output.matches(expectedResponse));
		
	}
	
	/**
	 * get element of xml
	 * @param testName	String
	 * @param nodeName	String	xml node
	 * @return
	 */
	public String getXmlNode(String testName,String nodeName){
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		String nodeElement = xmlMap.get(nodeName);	
		return nodeElement;
	}

	/**
	 * get connectors' id from response
	 * @param response 	String, a JSON String Response
	 * @return List<String> connectorsIds
	 */
	public List<String> getConnectorId(String response){

		List<String> connectorIds=new ArrayList<String>();
	
		JsonParser parser = new JsonParser(response);
		if (response.contains("items")){ 
			int num=parser.arrayElemSize(parser.jsonObject, "items");
			for (int i=0; i<num; i++){
				connectorIds.add(parser.parser(parser.jsonObject, "items["+i+"].id").toString());
			}
		}
		else{
			connectorIds.add(parser.parser(parser.jsonObject, "id").toString());
		}
		return connectorIds;
	}
	
	/**
	 * get token from response
	 * @param response	String, a JSON String Response
	 * @return
	 */
	public String getToken(String response){
		JsonParser parser = new JsonParser(response);
		return parser.parser(parser.jsonObject, "token").toString();
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