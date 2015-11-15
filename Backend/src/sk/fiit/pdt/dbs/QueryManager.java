package sk.fiit.pdt.dbs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class QueryManager {
	
	private PostgisConnector connector;
	
	private Statement statement;

	public QueryManager() {
		try {
			connector = new PostgisConnector();
			statement = connector.getStatement();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List<JSONObject> getAll() throws SQLException {
		List<JSONObject> geoJsons = new ArrayList<>();
		
		ResultSet result = statement.executeQuery("SELECT ST_AsGeoJSON(ST_Transform(way, 4326)) AS result, name, brand FROM planet_osm_point where amenity = 'fuel' ORDER BY name");
		while(result.next()) {
			JSONObject json = new JSONObject();
			json.put("type", "Feature");
			json.put("geometry", new JSONObject(result.getString("result")));
			JSONObject properties = new JSONObject();
			properties.put("title", result.getString("name"));
			properties.put("description", result.getString("brand"));
			properties.put("marker-color", "#fc4353");
			properties.put("marker-size", "large");
			properties.put("marker-symbol", "fuel");
			json.put("properties", properties);
			geoJsons.add(json);
		}
		
		return geoJsons;
	}
	
	public List<JSONObject> getNearest(double n, double e, int limit) throws SQLException {
		List<JSONObject> geoJsons = new ArrayList<>();
		
		String queryText = "SELECT ST_Distance(ST_GeomFromText('POINT(" + e + " " + n + ")', 4326), ST_Transform(way, 4326)) AS distance, ST_AsGeoJSON(ST_Transform(way, 4326)) AS result, name, brand FROM planet_osm_point where amenity = 'fuel' ORDER BY distance LIMIT " + limit;
		ResultSet result = statement.executeQuery(queryText);
		while(result.next()) {
			JSONObject json = new JSONObject();
			json.put("type", "Feature");
			json.put("geometry", new JSONObject(result.getString("result")));
			JSONObject properties = new JSONObject();
			properties.put("title", result.getString("name"));
			properties.put("description", result.getString("brand"));
			properties.put("marker-color", "#fc4353");
			properties.put("marker-size", "large");
			properties.put("marker-symbol", "fuel");
			json.put("properties", properties);
			geoJsons.add(json);
		}
		
		return geoJsons;
	}
	
	public List<JSONObject> getByBrands(List<String> brands) throws SQLException {
		if(brands.isEmpty()) {
			return null;
		}
		
		List<JSONObject> geoJsons = new ArrayList<>();
		
		String condition = "";
		for(String brand : brands) {
			condition = condition.concat(" OR LOWER(brand) = LOWER('" + brand + "')");
		}
		condition = condition.substring(4);
		
		ResultSet result = statement.executeQuery("SELECT ST_AsGeoJSON(ST_Transform(way, 4326)) AS result, name, brand FROM planet_osm_point where amenity = 'fuel' AND (" + condition + ")");
		while(result.next()) {
			JSONObject json = new JSONObject();
			json.put("type", "Feature");
			json.put("geometry", new JSONObject(result.getString("result")));
			JSONObject properties = new JSONObject();
			properties.put("title", result.getString("name"));
			properties.put("description", result.getString("brand"));
			properties.put("marker-color", "#fc4353");
			properties.put("marker-size", "large");
			properties.put("marker-symbol", "fuel");
			json.put("properties", properties);
			geoJsons.add(json);
		}
		
		return geoJsons;
	}
}