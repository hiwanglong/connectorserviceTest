package com.endeca.microservices.connector.api;

import static org.junit.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.endeca.microservices.connector.util.JsonParser;
import com.sun.jersey.api.client.Client;

public class GetConnectorsTest {


	private static Client client = Client.create();
	String testFile="GetConnectorsTest.xml";
	CommonUtil util=new CommonUtil(client, testFile, GetConnectorsTest.class);
	String reqUrl=Constants.connectors;
	int count;
	String res, testCase;
	JsonParser parser;

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {

		//delete all connectors
		util.cleanConnectors();
	}

	/**
	 * check get connectors with items=1
	 */
	@Test(groups = {"Functional"})
	public void testGetConnectorsTest1() { 

		testCase="testGetConnectorsTest1";

		// post a connector
		util.executePost(reqUrl,testCase);

		// get connectors
		res=util.executeGet(reqUrl).get("jsonRes");
		parser = new JsonParser(res);
		count=parser.arrayElemSize(parser.jsonObject, "items");

		// check count=1
		assertEquals("Connectors' number is NOT 1: ",1,count);

	}

	/**
	 * check get connectors with items=2 
	 */
	@Test(groups = {"Functional"})
	public void testGetConnectorsTest2() { 

		testCase="testGetConnectorsTest2";

		// post two connectors
		util.executePostBatch(reqUrl,testCase);

		// get connectors
		res=util.executeGet(reqUrl).get("jsonRes");
		parser = new JsonParser(res);
		count=parser.arrayElemSize(parser.jsonObject, "items");

		// check count=2
		assertEquals("Connectors' number is NOT 2: ",2,count);

	}

	/**
	 * check get connectors with items=0
	 */
	@Test(groups = {"Functional"})
	public void testGetConnectorsTest3() { 

		// no connectors have been posted

		// get connectors
		res=util.executeGet(reqUrl).get("jsonRes");
		parser = new JsonParser(res);
		count=parser.arrayElemSize(parser.jsonObject, "items");

		// check count=0
		assertEquals("Connectors' number is NOT 0: ",0,count);

	}
}
