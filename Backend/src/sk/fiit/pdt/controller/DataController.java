package sk.fiit.pdt.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import sk.fiit.pdt.dbs.QueryManager;

@Path("")
public class DataController {
	private QueryManager manager = new QueryManager();
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		JSONArray array = new JSONArray();
		
		try {
			List<JSONObject> result = manager.getAll();
			for(JSONObject json : result) {
				array.put(json);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return array.toString();
	}
	
	@GET
	@Path("/shop")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWithShop() {
		JSONArray array = new JSONArray();
		
		try {
			List<JSONObject> result = manager.getWithShop();
			for(JSONObject json : result) {
				array.put(json);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return array.toString();
	}
	
	@GET
	@Path("/wash")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWash() {
		JSONArray array = new JSONArray();
		
		try {
			List<JSONObject> result = manager.getWash();
			for(JSONObject json : result) {
				array.put(json);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return array.toString();
	}
	
	@GET
	@Path("/withwash")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWithWash() {
		JSONArray array = new JSONArray();
		
		try {
			List<JSONObject> result = manager.getWithWash();
			for(JSONObject json : result) {
				array.put(json);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return array.toString();
	}
	
	@GET
	@Path("/nearest/{n}/{e}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getNearest(@PathParam("n") double n, @PathParam("e") double e, @PathParam("limit") int limit) {
		JSONArray array = new JSONArray();
		
		try {
			List<JSONObject> result = manager.getNearest(n, e, limit);
			for(JSONObject json : result) {
				array.put(json);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return array.toString();
	}
	
	@GET
	@Path("/brands/{cb1}/{cb2}/{cb3}/{cb4}/{cb5}/{cb6}/{cb7}/{cb8}/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getByBrands(@PathParam("cb1") boolean cb1, @PathParam("cb2") boolean cb2, @PathParam("cb3") boolean cb3, @PathParam("cb4") boolean cb4, @PathParam("cb5") boolean cb5, @PathParam("cb6") boolean cb6, @PathParam("cb7") boolean cb7, @PathParam("cb8") boolean cb8) {
		JSONArray array = new JSONArray();
		
		List<String> brands = new ArrayList<>();
		if(cb1) brands.add("Slovnaft");
		if(cb2) brands.add("OMV");
		if(cb3) brands.add("Agip");
		if(cb4) brands.add("Avanti");
		if(cb5) brands.add("Jurki");
		if(cb6) brands.add("Lukoil");
		if(cb7) brands.add("Shell");
		if(cb8) brands.add("Tesco");
		
		try {
			List<JSONObject> result = manager.getByBrands(brands);
			for(JSONObject json : result) {
				array.put(json);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return array.toString();
	}
}
