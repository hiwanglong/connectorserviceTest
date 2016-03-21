package com.oracle.bdd.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Usage:
 * <li>
 * InputStream f = JsonParser.class.getResourceAsStream("../xml/json.txt"); 
 * JsonParser parser= new JsonParser(f);
 * parser.parser(parser.jsonObject, "items[1].userAuthParameters.username.type.maxLength");
 * <li>
 * String json = "...";
 * parser = new JsonParser(json);
 * parser.parser(parser.jsonObject, "parameters.username");
 * @author zxie
 *
 */
public class JsonParser {

	public Object jsonObject;

	/**
	 * Construct with file stream
	 * @param io
	 */
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

	/**
	 * Construct with json string
	 * @param json json string
	 */
	public JsonParser(String json) {
		JSONParser jsonParser = new JSONParser();
		try {
			this.jsonObject = jsonParser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * internal function
	 * @param parser
	 * @param item
	 * @return
	 */
	private Object parserJSONArray(JSONArray parser, String item){
		int start = item.indexOf("[");
		int end = item.indexOf("]");
		int index = Integer.parseInt(item.substring(start+1, end));
		item = item.substring(0, start);
		return parser.get(index);
	}

	/**
	 * internal function
	 * @param parser
	 * @param item
	 * @return
	 */
	private Object parserJSONObject(JSONObject parser,String item ){
		return parser.get(item);
	}

	/**
	 * internal function
	 * @param parser
	 * @param item
	 * @return
	 */
	private Object parserJson(Object parser, String item){
		if(item.indexOf("[")!=-1 && item.indexOf("]")!=-1){
			int start = item.indexOf("[");
			return parserJSONArray((JSONArray)((JSONObject)parser).get(item.substring(0, start)), item);
		}
		else
			return parserJSONObject((JSONObject)parser, item);
	}

	/**
	 * internal function
	 * @param parser
	 * @param items
	 * @return
	 */
	private Object parserJson(Object parser, String[] items){
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

		return parserJson(parser, items);
	}

	/**
	 * Get the json object value of path
	 * @param parser JSONObject
	 * @param path path from json tree root to some element
	 * @return element object value
	 */
	public Object parser(Object parser, String path){
		String[] elements =  path.split("\\.");
		return parserJson(parser, elements);
	}
	
	/**
	 * Get the JSONArray object size
	 * @param parser object
	 * @param path path to the JSONArray object
	 * @return the number of elements
	 */
	public int arrayElemSize(Object parser, String path){
		return ((JSONArray)parser(parser, path)).size();
	}
}
