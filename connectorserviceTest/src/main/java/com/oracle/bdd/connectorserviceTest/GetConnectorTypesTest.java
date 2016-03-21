package com.oracle.bdd.connectorserviceTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oracle.bdd.util.CommonUtil;
import com.oracle.bdd.util.Constants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;


public class GetConnectorTypesTest {
	
	CommonUtil util=new CommonUtil();
	Client client = Client.create();
	String testFile="GetConnectorTypesTest.xml";
	String reqUrl=Constants.connectorUrl+Constants.connectorTypes;
	

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


/*	@Test
	public void testGetConnectorTypes() {    
		
		String conn=Constants.connectorUrl+"connectorTypes";
		System.out.println(conn);	
        Client client = Client.create();
        WebResource webRes = client.resource(conn);
        String res = webRes.accept("application/json").get(String.class);
        System.out.println(res);
        
        int status=webRes.head().getStatus();
        if(status!=200){ 
        	fail("Fail since status is "+status);
        }
        System.out.println(status);
        Map<String, String> res_map=GetResourceXML.parseXml("GetConnectorTypesTest.xml", "testGetConnectorTypes");
        String res_expected=res_map.get("RESPONSEJSON").trim();
      	assertTrue("ConnectorTypes response is NOT as expected",res.equals(res_expected));            
	}*/

	
	@Test
	public void testGetConnectorTypes(){
		
		//get connector types
		String testCase="testGetConnectorTypes";
	    ClientResponse response=util.executeGet(client, reqUrl);
	   		
		//check get response
	    util.checkResponse(response, testFile, testCase); 
	}
	
	
}
