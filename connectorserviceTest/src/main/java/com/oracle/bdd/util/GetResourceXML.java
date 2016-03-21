package com.oracle.bdd.util;


import java.io.*;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.*;



public class GetResourceXML {
	
	public static Map<String, String> parseXml(String xmlName,String testName) {
		Map<String, String> requestMap = new HashMap<String, String>();
		
		SAXReader reader = new SAXReader();
		try {
			InputStream f = GetResourceXML.class.getResourceAsStream("../xml/"+xmlName); 
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

	public static void main(String arge[]) {
		
        
	}
}
