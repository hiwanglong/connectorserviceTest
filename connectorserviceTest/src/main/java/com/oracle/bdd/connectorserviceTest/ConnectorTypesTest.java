package com.oracle.bdd.connectorserviceTest;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oracle.bdd.util.Constants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
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
		String conn=Constants.connectorUrl+"connectorTypes";
		System.out.println(conn);
		
		
		
				

		
		
		
        Client client = Client.create();
                
        WebResource webRes = client.resource(conn);
        
       /* ClientResponse response = webRes.accept("application/json").get(ClientResponse.class);
        
        if (response.getStatus() != 200) {
 		   throw new RuntimeException("Failed : HTTP error code : "
 			+ response.getStatus());
 		}

 		String output = response.getEntity(String.class);
 		

 		System.out.println("Output from Server .... \n");
 		System.out.println(output);
        */
        
        
        
        System.out.println( webRes.getProperties());
        System.out.println(webRes.queryParam("name", "CloudStorage"));
        
        
        System.out.println(webRes.entity(String.class));
        
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
