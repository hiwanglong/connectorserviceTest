package com.oracle.bdd.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {

	public File jfile;
	public String jStr;
	public Object jsonObject;

	public JsonParser(InputStream io) {
		InputStreamReader reader = new InputStreamReader(io);
		JSONParser jsonParser = new JSONParser();
		try {
			this.jsonObject = jsonParser.parse(reader);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public JsonParser(String json) {
		this.jStr = json;
		JSONParser jsonParser = new JSONParser();
		try {
			this.jsonObject = jsonParser.parse(jStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private Object parserJSONArray(JSONArray parser, String item){
		int start = item.indexOf("[");
		int end = item.indexOf("]");
		int index = Integer.parseInt(item.substring(start+1, end));
		item = item.substring(0, start);
		return parser.get(index);
	}

	private Object parserJSONObject(JSONObject parser,String item ){
		return parser.get(item);
	}

	private Object parserJson(Object parser, String item){
		if(item.indexOf("[")!=-1 && item.indexOf("]")!=-1){
			int start = item.indexOf("[");
			return parserJSONArray((JSONArray)((JSONObject)parser).get(item.substring(0, start)), item);
		}
		else
			return parserJSONObject((JSONObject)parser, item);
	}

	public Object parser(Object parser, String[] items){
		if(items == null || items.length == 0)
			return null;

		if(items.length == 1)
			return parserJson(parser, items[0]);

		if(items[0].indexOf("[")!= -1 && items[0].indexOf("]") != -1){
			int start = items[0].indexOf("[");
			parser =  parserJSONArray((JSONArray)((JSONObject)parser).get(items[0].substring(0, start)), items[0]);
		}
		else
			parser = parserJSONObject((JSONObject)parser, items[0]);

		items = Arrays.copyOfRange(items, 1, items.length);

		return parser(parser, items);
	}


	public static void main(String[] args){

		//			String path = "items[0].parameters.directoryPath.type.maxLength";
		//			String path = "items[1]";
		String path = "items[1].userAuthParameters.username.type";
		String[] elements =  path.split("\\.");

		InputStream f = JsonParser.class.getResourceAsStream("../xml/json.txt"); 
		JsonParser parser1= new JsonParser(f);

		System.out.println(parser1.parser(parser1.jsonObject, elements));

		JSONArray connectorTyps = (JSONArray)((JSONObject)parser1.jsonObject).get("items");
		JSONObject item = (JSONObject)connectorTyps.get(0);
		JSONObject param = (JSONObject)item.get("parameters");
		JSONObject directoryPath = (JSONObject)param.get("directoryPath");
		JSONObject type = (JSONObject)directoryPath.get("type");
		//			System.out.println(type.get("maxLength"));

	}
}
