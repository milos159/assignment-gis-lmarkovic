package sk.fiit.pdt.dbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgisConnector {
	private Connection connection;
	
	private Statement statement;
	
	public PostgisConnector() throws SQLException, ClassNotFoundException {
		createConnection();
	}
	
	private void createConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver"); 
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pdt", "postgres", "database");
		statement = connection.createStatement();
	}
	
	public Statement getStatement() {
		return statement;
	}
}