package com.oracle.bdd.connectorserviceTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oracle.bdd.util.Contants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class ConnectorTypesTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testConnectorTypes() {

		//String conn=Contants.getConnectorserviceUrl()+"connectorTypes";
		String conn=Contants.connectorUrl+"connectorTypes";
		System.out.println(conn);
		
        Client client = Client.create();
        WebResource webRes = client.resource(conn);
        
        // check status 200
        int status=webRes.head().getStatus();
        System.out.println(status);
        assertEquals(200, status);
        
        // check response "cloud" "HDFS"
        String res = webRes.acceptLanguage("en-US").get(String.class);
        System.out.println(res);
        //assert...?


		//fail("Not yet implemented");
	}
}
