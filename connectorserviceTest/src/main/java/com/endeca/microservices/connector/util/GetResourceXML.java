package com.endeca.microservices.connector.util;


import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.*;
import org.dom4j.io.*;



public class GetResourceXML {
	
	/**
	 * parser input(s) and output(s) to a map
	 * @param xmlName xml file store input(s) and expected output(s)
	 * @param testName test name to identify
	 * @return hash map include input(s) and output(s)
	 */
	public static Map<String, String> parseXml(String xmlName,String testName) {
		Map<String, String> requestMap = new HashMap<String, String>();
		
		SAXReader reader = new SAXReader();
		try {
			File f1 = new File(GetResourceXML.class.getResource("/"+xmlName).getPath());
			InputStream f = new FileInputStream(f1);
			
			Document doc = reader.read(f);
			Element root = doc.getRootElement(); 								//get xml root ,getName() get root names
			
			for(Object fo : root.elements("TEST")){								//cycle TEST element
				Element foo = (Element)fo;
				
				String xmlTestName = foo.attributeValue("testname");			//get testname
				
				if (testName.equals(xmlTestName)){								// get test element
					requestMap.put("testname", xmlTestName);
					
					for(Object o :foo.elements()){
						Element e = (Element)o;						
						requestMap.put(e.getName(), e.getText());	
						
						//System.out.println(e.getName());
						//System.out.println(e.getText());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestMap;
	}
	
	/**
	 * replace character of \s*|\t|\r|\n in a json string  to ""
	 * @param json json string
	 * @return json string without \s*|\t|\r|\n
	 */
	public static String trimAllSpaces(String json){
		if(json == null || json.length() == 0)
			return null;
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(json);
		return m.replaceAll("");
	}

	public static void main(String arge[]) {
		
		Map<String, String> res_map=GetResourceXML.parseXml("GetConnectorTypesTest.xml", "testGetConnectorTypes");
		String res_expected=res_map.get("RESPONSEJSON").trim();
		String result = GetResourceXML.trimAllSpaces(res_expected);
		System.out.println(result);
	}
}
