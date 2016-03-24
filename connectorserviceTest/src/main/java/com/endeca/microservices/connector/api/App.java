package com.endeca.microservices.connector.api;

import java.io.InputStream;

import com.endeca.microservices.connector.util.JsonParser;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args){

		//			String path = "items[0].parameters.directoryPath.type.maxLength";
		//			String path = "items[1]";

		String json = "{"+
	    "\"connectorType\": \"OracleCloudStorage\","+
	    "\"connectorName\": \"storage 1\","+
	    "\"description\": \"storage 1 description\","+
	    "\"visibility\": [\"import\"],"+
	    "\"parameters\": {"+
	        "\"storageServiceUrl\": \"https://storage.oraclecorp.com/v1/Storage-dfisher\","+
	        "\"username\" : \"dfisher.Storageadminivalid\","+
	        "\"password\" : \"welcome1\","+
	        "\"blackList\": \"rule123\","+
	        "\"whiteList\": \"rules456\""+
	     "}}";
		
		InputStream f = JsonParser.class.getResourceAsStream("../xml/json.txt"); 
		JsonParser parser= new JsonParser(f);

		System.out.println(parser.parser(parser.jsonObject, "items[1].userAuthParameters.username.type.maxLength"));
		System.out.println(parser.parser(parser.jsonObject, "items[0].parameters.directoryPath.type"));
		System.out.println(parser.arrayElemSize(parser.jsonObject, "items"));
		
		parser = new JsonParser(json);
		System.out.println(parser.parser(parser.jsonObject, "parameters.username"));
	}
}
