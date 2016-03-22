package com.oracle.bdd.util;

/**
 *  GET /v1/connectorTypes (sync)
 *  POST /v1/connectors (sync)
 *  GET /v1/connectors (sync)
 *  GET /v1/connectors/{connectorId} (sync)
 *  PUT /v1/connectors/{connectorId} (sync)
 *  DELETE /v1/connectors/{connectorId} (sync)
 *  POST /v1/connectors/{connectorId}/auth (sync)
 *  GET /v1/connectors/{connectorId}/containers/{containerId}?filter="abc"&orderBy=name&limit=10&offset=0&ascending=true (sync)
 *  GET /v1/connectors/{connectorId}/containers?searchByPath="<URL-encoded path>" (sync)
 *  POST /v1/connectors/{connectorId}/previewData  (sync)
 *  GET /v1/parsingOptionMetas (sync)
 *  POST /v1/snapshot/  (async)
 *  GET /v1/health (sync)
 * @author stellaw
 *
 */

public final class Constants {
	
	private Constants(){}
	
	public static final String connectorUrl="http://busgf1515.us.oracle.com:7205/bdd.connectorservice/v1/";
	public static final String connectorTypes=connectorUrl+"connectorTypes";
	public static final String connectors=connectorUrl+"connectors";
	public static final String connectorId=connectorUrl+"connectors/{connectorId}";
	
	
	public static final String connectorAuth=connectorId+"/auth";
	public static final String browseByContainerId=connectorId+"/containers/{containerId}?";
	public static final String browseByPath=connectorId+"/containers?searchByPath={URL-encoded path}";
	public static final String previewData=connectorId+"/previewData";
	
	
	public static final String parsingOptionMetas=connectorUrl+"parsingOptionMetas";
	public static final String snapshot=connectorUrl+"snapshot";
	public static final String health=connectorUrl+"health";
}
