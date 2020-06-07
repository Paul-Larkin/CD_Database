package main;

import javax.swing.JOptionPane;

public class UserInterface {
	
	public static void main(String[] args) {
		new UserInterface();		
	}
	
	UserInterface() {
		welcomeScreen();
	}
	
	public void welcomeScreen() {
		String[] options = {"Create", "Read", "Update", "Delete", "Exit"};
		int input = JOptionPane.showOptionDialog(null, "Select An Option", "CD Center", 0, JOptionPane.QUESTION_MESSAGE, null, options,0);
		switch(input) {
		case 0: insert();
				break;
		case 1: read();
				break;
		case 2: update();
				break;
		case 3: delete();
			break;
		default: JOptionPane.showMessageDialog(null, "****Goodbye****");
				System.exit(0);
		}
	}
	
	public void read() {
		String query = null;
		String[] options = {"Read All", "Enter Query", "Exit"};
		int input = JOptionPane.showOptionDialog(null, "Select An Option", "CD Center", 0, JOptionPane.QUESTION_MESSAGE, null, options,0);
		
		switch(input) {
		case 0: query = "select * from cdTable";
				break;
		case 1: JOptionPane.showInputDialog(null, "Enter an query: ");
				break;
		default: JOptionPane.showMessageDialog(null, "****Goodbye****");
				System.exit(0);
		}
		DBConnection conn = DBConnection.getInstance();
		conn.read(query);
		conn = null;
		welcomeScreen();
	}

	public void insert() {
		String title = JOptionPane.showInputDialog(null, "Enter an title: ");
		String artist = JOptionPane.showInputDialog(null, "Enter an artist: ");
		String genre = JOptionPane.showInputDialog(null, "Enter a genre: ");
		int year = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter an year: "));
		String lable = JOptionPane.showInputDialog(null, "Enter an record lable: ");
		
		String query = "INSERT INTO cdTable(title, artist, genre, year,lable)"
				+ "VALUES('"+title+"', '"+artist+"', '"+genre+"', "+year+", '"+lable+"')";
		
		DBConnection conn = DBConnection.getInstance();
		conn.runUpdate(query);
		conn = null;
		welcomeScreen();
	}
	
	public void update() {
		int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the id of which entry to UPDATE: "));
		String title = JOptionPane.showInputDialog(null, "Enter an title: ");
		String artist = JOptionPane.showInputDialog(null, "Enter an artist: ");
		String genre = JOptionPane.showInputDialog(null, "Enter a genre: ");
		int year = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter an year: "));
		String lable = JOptionPane.showInputDialog(null, "Enter an record lable: ");
		String query = "UPDATE cdTable SET "
				+ "title = '"+title+"', artist = '"+artist+"', genre = '"+genre+"', year = '"+year+"', lable = '"+lable +"' WHERE id = '"+id+"'"; 
		DBConnection conn = DBConnection.getInstance();
		conn.runUpdate(query);
		conn = null;
		welcomeScreen();
	}
	
	public void delete() {
		String query = null;
		String[] options = {"Delete All", "Delete Query", "Exit"};
		int input = JOptionPane.showOptionDialog(null, "Select An Option", "CD Center", 0, JOptionPane.QUESTION_MESSAGE, null, options,0);
		
		switch(input) {
		case 0: query = "truncate table cdTable";
				break;
		case 1: JOptionPane.showInputDialog(null, "Enter a delete query: ");
				break;
		default: JOptionPane.showMessageDialog(null, "****Goodbye****");
				System.exit(0);
		}
		DBConnection conn = DBConnection.getInstance();
		conn.runUpdate(query);
		conn = null;
		welcomeScreen();
	}
}