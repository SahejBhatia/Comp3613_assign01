/**
 * Project: A00766737Assignment1
 * File: Ado.java
 * Date: Feb 14, 2017
 * Time: 4:45:40 PM
 */

package a00766737.ado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import a00766737.data.Book;


/**
 * @author sehaj A00766737
 *Ado class class for handling all database activity
 */
public class Ado {
	
	private final static String tableName = "Book_A00766737";
	
	private static String driver, url,user,password, database ;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	List<Book> books ;

	public Ado(){	}
	
	//public constructor getting init values from controller
	public Ado(HashMap<String, String> cred) {
		// TODO Auto-generated constructor stub
		url = cred.get("url");
		user = cred.get("user");
		password= cred.get("password");
		driver = cred.get("driver");
		database = cred.get("database");
		
	}

	public void getConnected(){
		
	}

	
	//connecting to the database and creating a table
	public void connect (){
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);			
			stmt = con.createStatement();
			
			stmt.execute("IF OBJECT_ID('"+ tableName+ "') IS NOT NULL DROP TABLE "+ tableName +"");
			
			String createTable = " CREATE TABLE "+tableName+" (BookId INT PRIMARY KEY IDENTITY, TITLE VARCHAR(100) NOT NULL ,AUTHOR VARCHAR(100), ISBN VARCHAR(100)"
					+ " , PUBLISHER VARCHAR(100) , YEARPUB VARCHAR(100) , EDITION VARCHAR(100) )";
			stmt.execute(createTable);		
			
			
			stmt.close();
			con.close();

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		
	}
	
	//deleting a record with id = #
	public void delete(int rowNumber){
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);			
			stmt = con.createStatement();
			String delete = "DELETE FROM "+tableName+" WHERE BookId = "+ rowNumber+";";
			stmt.executeUpdate(delete);
			stmt.close();
			con.close();
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//method to update a tuple , as modified by the user.
	public void update(Book book){
		
		try{
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,user,password);
		stmt = con.createStatement();
		
		String update = "UPDATE "+tableName+" SET TITLE = '"+book.getTitle()+"' , AUTHOR = '"+book.getAuthor()+"', ISBN = '"+book.getIsbn()+"', YEARPUB = '"+book.getYearPub()+"', "
						+ "PUBLISHER = '"+book.getPublisher()+"' , EDITION = '"+book.getEdition()+ "' WHERE BookId = '" + book.getId() +"' ;" ;
		
		stmt.executeUpdate(update);
		
		stmt.close();
		con.close();
	}catch(ClassNotFoundException e){
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
	}
	
	//inserting a new record with id #
	//should try and call it with an object book 
	//or possibly use :
	//insert(int id, string fname, string lname etc)
	public void insert(Book book){
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			stmt = con.createStatement();
			
			
			String insert = "INSERT INTO "+tableName+" (BookId, TITLE,AUTHOR, ISBN, YEARPUB, PUBLISHER,EDITION) "
					+ "VALUES ('"+ book.getId()+"','"+ book.getAuthor()+"','"+ book.getIsbn()+"','"+ book.getYearPub()+"','"+ book.getPublisher()+"','"+ book.getEdition()+"'); ";
			
			stmt.execute(insert);
			
			stmt.close();
			con.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//creating a new record with id #
	public void create(Book book){
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			stmt = con.createStatement();
			
			String create = "INSERT INTO "+tableName+" (TITLE,AUTHOR, ISBN, YEARPUB, PUBLISHER,EDITION) "
					+ "VALUES ('"+ book.getTitle()+"','"+ book.getAuthor()+"','"+ book.getIsbn()+"','"+ book.getYearPub()+"','"+ book.getPublisher()+"','"+ book.getEdition()+"'); ";
			
			stmt.executeUpdate(create);
			
			stmt.close();
			con.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//reading all the records
	public List<Book> readAll(){
		
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			stmt = con.createStatement();
			
			ResultSet resultSet = stmt.executeQuery("Select * from "+ tableName);
			
			books = new ArrayList<Book>();
			int id = 1; 
			while(resultSet.next()){
				Book book = new Book();
				book.setId(id);
				book.setTitle(resultSet.getString("TITLE"));
				book.setAuthor(resultSet.getString("AUTHOR"));
				book.setEdition(resultSet.getString("EDITION"));
				book.setIsbn(resultSet.getString("ISBN"));
				book.setPublisher(resultSet.getString("PUBLISHER"));
				book.setYearPub(resultSet.getString("YEARPUB"));
				
				books.add(book);
				id++;
			}	
			
			stmt.close();
			con.close();
			
			//return books;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	
	
	
	
}
