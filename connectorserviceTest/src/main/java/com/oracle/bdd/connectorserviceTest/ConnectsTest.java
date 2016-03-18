package com.oracle.bdd.connectorserviceTest;

import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.oracle.bdd.util.Constants;
import com.oracle.bdd.util.GetResourceXML;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ConnectsTest {

	@Test
	public void test_firstC() {
		String first = "First";
		assertEquals("First", first);
	}

	@Test
	public void test_firstC1() {
		String first = "First";
		assertEquals("First", first);
	}

	@Test
	public void test_firstC2() {
		String first = "First";
		assertEquals("First", first);
	}

	public static void main(String[] args) {
		Map<String, String> xmlMap = GetResourceXML.parseXml("BDDConnectsAPITest.xml","GETconnectorTypes");

		System.out.println(xmlMap.get("REQUESTJSON"));

		Client client = Client.create(); 
		String reqUrl =Constants.connectorUrl+Constants.connectorTypes;
		System.out.println("reqUrl="+reqUrl);
		WebResource webRes = client.resource(reqUrl);
//		String res = webRes.acceptLanguage("en-US").get(String.class);
//		System.out.println(res);
				
		ClientResponse response = webRes.accept("application/json").acceptLanguage("en-US").get(ClientResponse.class);
		System.out.println("status == " + response.getStatus());
		String output = response.getEntity(String.class);
		System.out.println(output);
		
		
		
		/*
		 * Client client = Client.create(); String reqUrl =
		 * "http://busgf1515.us.oracle.com:7205/bdd.connectorservice/v1/"+reqMap
		 * .get("xmlRequestUrl"); String reqJson = reqMap.get("xmlRequestJson");
		 * WebResource webRes = client.resource(reqUrl);
		 * 
		 * //post ClientResponse response =
		 * webRes.type("application/json").acceptLanguage("en-US").post(
		 * ClientResponse.class, reqJson);
		 * 
		 * System.out.println("status == " + response.getStatus());
		 * 
		 * System.out.println("Output from Server .... \n"); String output =
		 * response.getEntity(String.class); System.out.println(output);
		 * 
		 * //get String res = webRes.acceptLanguage("en-US").get(String.class);
		 * System.out.println(res);
		 */
	}
}