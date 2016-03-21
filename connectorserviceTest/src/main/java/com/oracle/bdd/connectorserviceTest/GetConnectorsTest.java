package com.oracle.bdd.connectorserviceTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oracle.bdd.util.Constants;
import com.oracle.bdd.util.GetResourceXML;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class GetConnectorsTest {
	
	static WebResource webRes;
	static String res;
	static int count;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String conn=Constants.connectorUrl+"connectors";
		System.out.println(conn);	
        Client client = Client.create();
        webRes = client.resource(conn);
        res = webRes.accept("application/json").get(String.class);
        System.out.println(res);

        count=res.split("id").length-1;
        if (count!=0){
        	//delete all connectors
        }
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
		//delete all connectors
	}
	

	@Test
	public void testGetConnectorsTest1() {
		
		// post a connector
		
        // check count=1
		int count=res.split("id").length-1;
		System.out.println(count);
	   
	}
	
	@Test
	public void testGetConnectorsTest2() {
		
		// post two connectors
		
        // check count=2
		int count=res.split("id").length-1;
		System.out.println(count);
	   
	}
	
	@Test
	public void testGetConnectorsTest3() {
		
		// no connector 
		
        // check count=0
		int count=res.split("id").length-1;
		System.out.println(count);
	   
	}
	
	


}
