package com.oracle.bdd.connectorserviceTest;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oracle.bdd.util.CommonUtil;
import com.oracle.bdd.util.Constants;
import com.sun.jersey.api.client.Client;

public class PostConnectorsAuthTest {

	static Client client = Client.create();
	static String testFile="PostConnectorsAuthTest.xml";
	static CommonUtil util=new CommonUtil(client, testFile);
	static String reqUrl;
	static String testCase, connectorId;
	Map<String, String>response;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//post connector 
		String connectorRes=util.executePost(Constants.connectors, "setUpPostConnectorsAuth").get("jsonRes");
						
	    //get connector id
		connectorId=util.getConnectorId(connectorRes).get(0);
				
		//update reqUrl	
		reqUrl=Constants.connectorAuth.replace("{connectorId}",connectorId);
		System.out.println(reqUrl);
		
		
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
	public void createData(){
		System.out.println("hello");
	}
	
	@Test
	public void testPostConnectorsAuthTest1() {
		//fail("Not yet implemented");
	}

}
