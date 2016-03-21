package com.oracle.bdd.connectorserviceTest;

import static org.junit.Assert.*;

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

public class GetConnectorTypesTest {
	
	static WebResource webRes;
	static String res;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String conn=Constants.connectorUrl+"connectorTypes";
		System.out.println(conn);	
        Client client = Client.create();
        webRes = client.resource(conn);
        res = webRes.accept("application/json").get(String.class);
        System.out.println(res);
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
	public void testGetConnectorTypes() {              
        int status=webRes.head().getStatus();
        if(status!=200){ 
        	fail("Fail since status is "+status);
        }
        System.out.println(status);
        Map<String, String> res_map=GetResourceXML.parseXml("GetConnectorTypesTest.xml", "testGetConnectorTypes");
        String res_expected=res_map.get("RESPONSEJSON").trim();
      	assertTrue("ConnectorTypes response is NOT as expected",res.equals(res_expected));
   	
            
	}
	

	
	
}
