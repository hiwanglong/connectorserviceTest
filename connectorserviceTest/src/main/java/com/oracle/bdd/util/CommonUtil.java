package com.oracle.bdd.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CommonUtil {
	private WebResource webRes; 
	private ClientResponse response;
	private String output;
	
	/**
	 * execute POST API ,check if response status 200, 
	 * check response contains <RESPONSECONTAINS> if this tag exist in the xml
	 * <RESPONSECONTAINS> can contains multiple checks, separated by &
	 * @param client Client
	 * @param reqUrl String
	 * @param xmlMap	Map
	 * @return ClientResponse 
	 */
	public ClientResponse executePost(Client client,String reqUrl,Map<String, String> xmlMap){
		
		webRes = client.resource(reqUrl);	
		String reqJson = xmlMap.get("REQUESTJSON");		
		//System.out.println(reqJson);
				
		response =webRes.type("application/json").acceptLanguage("en-US").post(ClientResponse.class, reqJson);		//post api
	
		assertEquals("200", response.getStatus());																	//check stauts 200
		output = response.getEntity(String.class);
		//System.out.println(output);	
		
		assertNotSame("response is empty","",output);																//check response is not empty
		
		if(xmlMap.containsKey("EXPECTRESPONSECONTS")){																//check response contains
			String[] expectContainsArr = xmlMap.get("EXPECTRESPONSECONTS").split("&");	
			for(String expectContains : expectContainsArr){
				assertTrue("response doesn't contains "+ expectContains,output.contains(expectContains));				
			}
		}
		
		return response;
	}
	
	/**
	 * execute GET API ,check if response status 200, 
	 * check response contains <RESPONSECONTAINS> if this tag exist in the xml
	 * <RESPONSECONTAINS> can contains multiple checks, separated by &
	 * check response match <RESPONSEJSON> if this tag exist in the xml
	 * <RESPONSEJSON> need to keep format without whiteSpace
	 * @param client 
	 * @param requestUrl String
	 * @param xmlMap	Map
	 * @return ClientResponse
	 */
	public ClientResponse executeGet(Client client,String reqUrl,Map<String, String> xmlMap){
		webRes = client.resource(reqUrl);	
		//System.out.println(reqJson);
				
		response = webRes.acceptLanguage("en-US").get(ClientResponse.class);	//get api
	
		assertEquals("200", response.getStatus());								//check stauts 200
		output = response.getEntity(String.class);
		//System.out.println(output);	
		
		assertNotSame("response is empty","",output);							//check response is not empty
		
		if(xmlMap.containsKey("RESPONSECONTAINS")){		// check response contains
			String[] expectContainsArr = xmlMap.get("RESPONSECONTAINS").split("&");	
			for(String expectContains : expectContainsArr){
				assertTrue("response doesn't contains "+ expectContains,output.contains(expectContains));				
			}
		}
		
		if(xmlMap.containsKey("RESPONSEJSON")){			//check response match
			String expectedResponse = xmlMap.get("RESPONSEJSON").trim();	
			assertEquals("response and expectation are different",expectedResponse,output);
		}
		
		return response;
	}
	
	
}
