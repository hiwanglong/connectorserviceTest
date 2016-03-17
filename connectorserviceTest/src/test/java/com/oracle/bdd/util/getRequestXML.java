package com.oracle.bdd.util;


import java.io.*;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class getRequestXML {
	
	public  Map<String, String> parseXml(String testName) {
		Map<String, String> requestMap = new HashMap<String, String>();
		
		SAXReader reader = new SAXReader();
		try {
			InputStream f = this.getClass().getResourceAsStream("../xml/BDDConnectsAPITest.xml"); 
			Document doc = reader.read(f);
			Element root = doc.getRootElement(); //get xml root ,getName() get root names
			
			for(Object fo : root.elements("TEST")){		//  cycle TEST element 
				Element foo = (Element)fo;
				String xmlTestName = foo.elementText("TESTNAME");
				if (testName.equals(xmlTestName)){
					String xmlRequestJson = foo.elementText("REQUESTJSON");
					
//					System.out.println("TESTNAME: " + xmlTestName);
//					System.out.println("REQUESTJSON: " + xmlRequestJson);
					
					requestMap.put("xmlTestName", xmlTestName);
					requestMap.put("xmlRequestJson", xmlRequestJson);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestMap;
	}

	public static void main(String arge[]) {
		getRequestXML rq = new getRequestXML();
		Map<String,String> reqMap1 = rq.parseXml("GETconnectorTypes");
		Map<String,String> reqMap = rq.parseXml("POSTconnectors");
			
		
//		System.out.println(reqMap.get("xmlTestName"));
//		System.out.println(reqMap.get("xmlRequestJson"));
		
/*		 Client client = Client.create();
		 String reqUrl = "http://busgf1515.us.oracle.com:7205/bdd.connectorservice/v1/"+reqMap.get("xmlRequestUrl");
		 String reqJson = reqMap.get("xmlRequestJson");
         WebResource webRes = client.resource(reqUrl);

//post
         ClientResponse response = webRes.type("application/json").acceptLanguage("en-US").post(ClientResponse.class, reqJson);

      	 System.out.println("status == " + response.getStatus()); 
      		
  		 System.out.println("Output from Server .... \n");
  		 String output = response.getEntity(String.class);
  		 System.out.println(output);

//get
         String res = webRes.acceptLanguage("en-US").get(String.class);
         System.out.println(res);*/
        
	}
}
