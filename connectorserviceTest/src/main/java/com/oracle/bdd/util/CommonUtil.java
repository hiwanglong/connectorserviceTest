package com.oracle.bdd.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
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
	 * delete all connecters
	 */
	public void cleanConnectors(Client client, String getUrl, String delUrl){ //getUrl=connectorUrl+connectors; delUrl=connectorUrl+connectorId
	
		//get all connectors' id
		List <String> connectotIds=getConnectorId(executeGet(client, getUrl));
		
		//delete all connectors one by one
		for (int i=0; i<connectotIds.size();i++){
			delUrl=delUrl.replace("{connectorId}",connectotIds.get(i) );
			executeDelete(client,delUrl);
		}	
	}
	
	
	/**
	 * check response status and response json (optional)
	 * @param response	ClientResponse
	 * @param xmlName	String
	 * @param testName	String
	 */
	public void checkResponse(ClientResponse response,String xmlName,String testName){
		xmlMap = GetResourceXML.parseXml(xmlName,testName);
		String testname = xmlMap.get("testname");
		String status = xmlMap.get("STATUS");
	
		assertEquals(testname+" response status is not "+status,Integer.parseInt(status), response.getStatus());	//check stauts 
		
		if(xmlMap.containsKey("RESPONSEJSON")){			//check response match
			String expectedResponse = xmlMap.get("RESPONSEJSON").trim().replace("%ConnectorId%", getConnectorId(response).get(0));	
			assertEquals(testname+" response and expectation are different",expectedResponse,output);
		}
	} 
	
	
	/**
	 * get connectorId from response json
	 * @param response	ClientResponse
	 * @return
	 */
	public List<String> getConnectorId(ClientResponse response){

		List<String> connectorIds=new ArrayList<String>();
		String res=response.getEntity(String.class);
		JsonParser parser = new JsonParser(res);
		if (res.contains("items")){
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