package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBConnection {
	
	private String url = "jdbc:mysql://localhost:3306/";
	private String dbName = "cdDatabase";
	private String userName = "root";
	private String passWord = "root";
	
	static DBConnection instance = null;
	
	private DBConnection() {
	}
	
	public static DBConnection getInstance() {
		if(instance == null ) {
			instance = new DBConnection();
		}
		return instance;
	}
	
	public void read(String query) {
	
		try(Connection connection = DriverManager.getConnection(url + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", userName, passWord); 
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				){
			
				while(resultSet.next()) {
					String title = resultSet.getString("title");
					String artist = resultSet.getString("artist");
					String genre = resultSet.getString("genre");
					int year = resultSet.getInt("year");
					String lable = resultSet.getString("lable");
					System.out.println("Title: " + title + "\nArtist: " + artist + 
										"\nGenre: " + genre + "\nYear: " + year + "\nLable: " + lable +"\n");
					
				}
			} catch (SQLException e) {
			  	e.printStackTrace();
		}
	}
	
	public void runUpdate(String string) {
		
		try(Connection connection = DriverManager.getConnection(url + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", userName, passWord);  
				Statement statement = connection.createStatement();
				){
				statement.executeUpdate(string);
				JOptionPane.showMessageDialog(null, "SUCCESS");
			} catch (SQLException e) {
			  	e.printStackTrace();
		}
	}
}
