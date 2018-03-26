package Model;
import Client.*; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class represents a database using mysql and jdbc to store clients
 * @author Justin, Robert
 */
public class DataBase {
	
	/**
	 * unique id list which stores all unique id and generates new ones 
	 */
	private UniqueId idList;
	
	/**
	 * rows of items in table
	 */
	private int size; 
	
	/**
	 * return size 
	 * @return size rows in table
	 */
	public int size() { return size; } 
	
	/**
	 * JDBC connection to mysql 
	 */
	public Connection jdbc_connection;
	
	/**
	 * prepared statement for executing mysql commands 
	 */
	public PreparedStatement statement;
	
	/**
	 * database name 
	 */
	public String databaseName = "ClientsDataBase";
	
	/**
	 * data file for initial database 
	 */
	private String dataFile = "C:\\Users\\justi\\eclipse-workspace\\409Lab8Ex3\\src\\clients.txt";
	
   //adjusts the variables to access a table named demo on MYSQL 
	public String connectionInfo = "jdbc:mysql://localhost:3306/demo",  
				  login          = "root",
				  password       = "Chordatgh!234";

	/**
	 * Connects to database with connection info, login and password
	 */
	public DataBase()
	{
		try{
			// If this throws an error, make sure you have added the mySQL connector JAR to the project
			Class.forName("com.mysql.jdbc.Driver");
			// If this fails make sure your connectionInfo and login/password are correct
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		}
		catch(Exception e) { e.printStackTrace(); }
	}
	
	public void setIdList(UniqueId list) {
		idList = list;
	}

	/**
	 * Use jdbc connection to create a database in MySQL
	 */
	public void createDB()
	{
		try {
			statement = jdbc_connection.prepareStatement("CREATE DATABASE ? " );
			statement.setString(1, databaseName);
			statement.executeUpdate();
			System.out.println("Created Database " + databaseName);
		} 
		catch( SQLException e)
		{
		}
	}

	/**
	 * Create table to hold clients with parameters: first name, last name, address
	 * , postalcode, phone number, client type. 
	 */
	public void createTable()
	{

		String sql =   "CREATE TABLE CLIENTS (" + 
					 "id INTEGER NOT NULL, " +
				     "first VARCHAR(20), " + 
				     "last VARCHAR(20), " + 
				     "address VARCHAR(50), " + 
				     "postalcode VARCHAR(7), " + 
				     "phonenumber VARCHAR(12), " +
				     "type VARCHAR(1), " + 
				     "PRIMARY KEY( id ))";
		try{
			Statement stmt = jdbc_connection.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Created Table ");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * removes table and all contents inside 
	 */
	public void removeTable()
	{
		String sql = "DROP TABLE CLIENTS";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
		}
	}
	
	/**
	 * fill table with data from `clients.txt` 
	 */
	public void fillTable()
	{
		try{ 
			Scanner sc = new Scanner(new FileReader(dataFile));
			while(sc.hasNext())
			{
				String clientInfo[] = sc.nextLine().split(";");
				Client theClient = new Client(idList.getUniqueId(), clientInfo[0], clientInfo[1], clientInfo[2],
						clientInfo[3], clientInfo[4], clientInfo[5].charAt(0));
				addItem(theClient); 
			}
			sc.close();
			System.out.println("Table filled");
		}
		catch(FileNotFoundException e)
		{
			System.err.println("File " + dataFile + " Not Found!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * add Client to database
	 * @param theClient client being added 
	 */
	public void addItem(Client theClient)
	{	
		try{
			Statement stmt = jdbc_connection.createStatement();
			String sql = "INSERT INTO CLIENTS (ID, first, last, address, postalcode, phonenumber, type)"
				+ " values ("
				+ theClient.getId() + ", '"  
				+ theClient.getfName() + "', '"
				+ theClient.getlName() + "', '"
				+ theClient.getaddress() + "', '" 
				+ theClient.getpost() + "', '" 
				+ theClient.getphone() + "', '" 
				+ Character.toString(theClient.getType()) + "');";
			size = stmt.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * search client by id and return client 
	 * @param clientId key being searched
	 * @return client for key in db, null if no key found in db
	 */
	public Client searchTool(int clientId)
	{
		String sql = "SELECT FROM CLIENTS WHERE ID = ?";
		ResultSet client;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, clientId);
			client = statement.executeQuery();
			if(client.next())
			{
				return new Client(client.getInt("ID"),
								client.getString("first"), 
								client.getString("last"), 
								client.getString("address"), 
								client.getString("postalcode"),
								client.getString("phonenumber"),
								client.getString("type").charAt(0));
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	/**
	 * delete client from list using id as key
	 * @param id key for delete
	 */
	public void delete(int id) {
		try {
			String sql = "DELETE FROM CLIENTS WHERE ID = ?";
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			System.out.println(id + " deleted from db");
		}
		catch (SQLException e) {e.printStackTrace();} 
	}
	
	/**
	 * update Client information in database 
	 * @param newClient new Client information
	 * @param id key of client being changed 
	 */
	public void update(Client newClient, int id)
	{
		
		try {
			statement = jdbc_connection.prepareStatement("UPDATE CLIENTS SET " +
					"ID = ?, first = ?, last = ?, address = ?, postalcode = ?," +
					"phonenumber = ?, type = ? WHERE ID = ?"); 
			statement.setInt(1, newClient.getId());
			statement.setString(2, newClient.getfName());
			statement.setString(3, newClient.getlName());
			statement.setString(4, newClient.getaddress());
			statement.setString(5, newClient.getpost());
			statement.setString(6, newClient.getphone());
			statement.setString(7, Character.toString(newClient.getType()));
			statement.setInt(8, id);
			statement.executeUpdate();
			System.out.println(id + " updated");
		}
		catch (SQLException e) {e.printStackTrace();} 
		
	}

	/**
	 * return ArrayList of clients from database 
	 * @return arrayList of Clients from database 
	 */
	public ArrayList<Client> tableToList()
	{
		try {
			ArrayList<Client> clientList = new ArrayList<Client>(); 
			String sql = "SELECT ID, first, last, address, postalcode, phonenumber, type FROM CLIENTS";
			statement = jdbc_connection.prepareStatement(sql);
			ResultSet clients = statement.executeQuery();
			
			while(clients.next())
			{
				Client theClient = new Client(clients.getInt("ID"), clients.getString("first")
						, clients.getString("last"), clients.getString("address"), clients.getString("postalcode")
						, clients.getString("phonenumber"), clients.getString("type").charAt(0));
				clientList.add(theClient);
			}
			clients.close();
			return clientList; 
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
