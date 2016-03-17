package com.oracle.bdd.connectorserviceTest;

import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.oracle.bdd.util.GetResourceXML;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ConnectsAPITest {

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
		Map<String, String> reqMap1 = GetResourceXML.parseXml("GETconnectorTypes");
		Map<String, String> reqMap = GetResourceXML.parseXml("POSTconnectors");

		System.out.println(reqMap1.get("REQUESTJSON"));
		System.out.println(reqMap1.get("r"));

		System.out.println(reqMap.get("REQUESTJSON"));
		System.out.println(reqMap.get("r"));

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