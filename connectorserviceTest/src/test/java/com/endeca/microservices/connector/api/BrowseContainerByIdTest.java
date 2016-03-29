package com.endeca.microservices.connector.api;

import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.endeca.microservices.connector.util.CommonUtil;
import com.endeca.microservices.connector.util.Constants;
import com.endeca.microservices.connector.util.GetResourceXML;
import com.sun.jersey.api.client.Client;


public class BrowseContainerByIdTest {
	
	private static Client client = Client.create();;
	private String reqUrl = Constants.connectors;
	private String testName;
	private Map<String, String> responseMap ;
	private String connectorId;
	private String containerId;
	private String token;
	private String xmlName = "BrowseContainerByIdTest.xml";
	
	
	CommonUtil comUtil = new CommonUtil(client, xmlName, BrowseContainerByIdTest.class);
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		//delete connector by id after each test
		comUtil.executeDelete(reqUrl+"/"+containerId);		
	}
	
	/**
	 * 
	 */
	@Test(groups = {"Functional"})
	public void testBrowseContainerById2() {	
		//post connector		
		testName = "browseContainerById2-connector";	
		String connectorRes = comUtil.executePost(reqUrl,testName).get("jsonRes");						
		connectorId = comUtil.getConnectorId(connectorRes).get(0);	//get connector id
		
		//post auth	
		testName = "browseContainerById2-auth";		
		reqUrl = Constants.connectorAuth.replace("{connectorId}",connectorId);	//replace connectorId
		responseMap = comUtil.executePost(reqUrl,testName);
		token = comUtil.getToken(responseMap.get("jsonRes"));
		System.out.println(token);
		
		//browse
		containerId = "YnJvd3NlQXBpVGVzdERhdGE=";
		reqUrl = Constants.browseByContainerId.replace("{connectorId}",connectorId).replace("{containerId}",containerId);
		responseMap = comUtil.executeGet(reqUrl, token);
		
		//String output = GetResourceXML.trimAllSpaces(responseMap.get("jsonRes")).replace("&", "");
		String output = GetResourceXML.trimAllSpaces(responseMap.get("jsonRes"));
		System.out.println("----------------------------");
		System.out.println(output);
		
		Map<String, String>  xmlMap = GetResourceXML.parseXml(xmlName,"browseContainerById2-browse");
		String expectedResponse = GetResourceXML.trimAllSpaces(xmlMap.get("RESPONSE"));
		System.out.println(expectedResponse);
		//System.out.println(output.matches("\\{\"name\":\"browseApiTestData\",\"id\":.*"));
		System.out.println(output.matches(expectedResponse));
		//System.out.println(output.equals(expectedResponse));
		
	}
	
	public static void main(String[] args) {
		String s = "{\"name\":\"browseApiTestData\",\"id\":\"YnJvd3NlQXBpVGVzdERhdGE\",\"navigable\":true,\"nativePath\":\"browseApiTestData\",\"links\":[{\"rel\":\"self\",\"href\":\"http://busgf1515.us.oracle.com:7205/bdd.connectorservice/v1/connectors/4185c52e-ebec-4f88-88e0-3720f02d1079/containers/YnJvd3NlQXBpVGVzdERhdGE?offset=0&limit=10\"}],\"children\":[{\"name\":\"obj1.csv\",\"id\":\"YnJvd3NlQXBpVGVzdERhdGEvb2JqMS5jc3Y=\",\"navigable\":false,\"nativePath\":\"browseApiTestData/obj1.csv\",\"attributes\":{\"fileSize\":\"51\",\"lastModifiedDate\":\"TueMar2904:03:50EDT2016\"},\"links\":[{\"rel\":\"self\",\"href\":\"http://busgf1515.us.oracle.com:7205/bdd.connectorservice/v1/connectors/4185c52e-ebec-4f88-88e0-3720f02d1079/containers/YnJvd3NlQXBpVGVzdERhdGEvb2JqMS5jc3Y=\"}]},{\"name\":\"obj2.txt\",\"id\":\"YnJvd3NlQXBpVGVzdERhdGEvb2JqMi50eHQ=\",\"navigable\":false,\"nativePath\":\"browseApiTestData/obj2.txt\",\"attributes\":{\"fileSize\":\"52\",\"lastModifiedDate\":\"TueMar2904:04:20EDT2016\"},\"links\":[{\"rel\":\"self\",\"href\":\"http://busgf1515.us.oracle.com:7205/bdd.connectorservice/v1/connectors/4185c52e-ebec-4f88-88e0-3720f02d1079/containers/YnJvd3NlQXBpVGVzdERhdGEvb2JqMi50eHQ=\"}]},{\"name\":\"obj3\",\"id\":\"YnJvd3NlQXBpVGVzdERhdGEvb2JqMw==\",\"navigable\":false,\"nativePath\":\"browseApiTestData/obj3\",\"attributes\":{\"fileSize\":\"0\",\"lastModifiedDate\":\"TueMar2904:05:35EDT2016\"},\"links\":[{\"rel\":\"self\",\"href\":\"http://busgf1515.us.oracle.com:7205/bdd.connectorservice/v1/connectors/4185c52e-ebec-4f88-88e0-3720f02d1079/containers/YnJvd3NlQXBpVGVzdERhdGEvb2JqMw==\"}]}],\"parentContainer\":{\"name\":\"root\",\"id\":\"root\",\"navigable\":true,\"nativePath\":\"\",\"links\":[{\"rel\":\"self\",\"href\":\"http://busgf1515.us.oracle.com:7205/bdd.connectorservice/v1/connectors/4185c52e-ebec-4f88-88e0-3720f02d1079/containers/root\"}]},\"hasMore\":false,\"totalResults\":3}";
		System.out.println(s.matches("\\{\"name\":\"browseApiTestData\",\"id\":.*"));
		
		String s1 = "{s[e]abc.sse,ee}";
		System.out.println(s1.matches("\\{.*\\[.*].*c.ss.*,e.*"));
		System.out.println(s1.matches("{.*"));
	}
}