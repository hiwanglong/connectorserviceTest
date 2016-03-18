package com.oracle.bdd.connectorserviceTest;

import java.util.Map;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oracle.bdd.util.CommonUtil;
import com.oracle.bdd.util.Constants;
import com.oracle.bdd.util.GetResourceXML;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import net.sf.json.JSONObject;

public class PostConnectsTest {
	private static Client client ;
	private static WebResource webRes; 
	private ClientResponse response;
	private Map<String, String> xmlMap;
	private String reqUrl =Constants.connectorUrl+Constants.connectors;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		client = Client.create(); 
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testPOSTconnectors1() {
		//post connector

		xmlMap = GetResourceXML.parseXml("PostConnectsTest.xml","testPOSTconnectors");
		response = new CommonUtil().executePost(client, reqUrl, xmlMap);
		
		JSONObject jsStr = JSONObject.fromObject(response.getEntity(String.class)); 			 
		String connectorId =jsStr.getString("id");										//get connectorId from response
		
		//get connector by id
		response = new CommonUtil().executeGet(client, reqUrl+"/"+connectorId, xmlMap);
		
		//delete connector by id
		webRes = client.resource(reqUrl+"/"+connectorId);	
		response = webRes.acceptLanguage("en-US").delete(ClientResponse.class);
		assertEquals("204", response.getStatus());
		
	}

	@Test
	public void test_firstC1() {
		String first = "First";
		assertTrue("sab".contains("s"));
		
	}

	@Test
	public void test_firstC2() {
		String first = "First";
		assertEquals("First", first);
	}
}