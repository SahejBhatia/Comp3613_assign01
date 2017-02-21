package a00766737.assign1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import a00766737.ado.Ado;
import a00766737.data.Book;

/*
 * 
 * Author: Sahej Bhatia, A00766737
 * Controller class for Book inventory  
 * 
 */

/**
 * Servlet implementation class Controller
 */

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String driver, url, user, password, database;
	private HashMap<String, String> cred;
	private List<Book> books;
	private Ado ado;
	private boolean errorFlag = false;
	private ArrayList<String> errorMsg = new ArrayList<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public void init() {
		ServletConfig config = getServletConfig();
		// grab database connectivity details from web.xml inludig database connection strings
		driver = config.getInitParameter("Driver");
		url = config.getInitParameter("URL");
		user = config.getInitParameter("User");
		password = config.getInitParameter("Password");
		database = config.getInitParameter("DatabaseName");

		cred = new HashMap<>();
		cred.put("driver", driver);
		cred.put("url", url);
		cred.put("user", user);
		cred.put("password", password);
		cred.put("database", database);

		ado = new Ado(cred);
		// initializes the dao and creates the table.
		ado.connect();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		//checking to see what action was performed 
		String insert = request.getParameter("insert");
		String update = request.getParameter("update");
		String delete = request.getParameter("delete");
		
			
		if(insert!= null && insert.equalsIgnoreCase("insert")){
			//error validation 
	
			
			if(request.getParameter("title").length()== 0){
				errorFlag = true;
				errorMsg.add("<h3>Please Provide Title</h3>");
			}else if(request.getParameter("author").length()== 0){
				errorFlag = true;
				errorMsg.add("<h3>Please Provide Author</h3>");
			}else if(request.getParameter("publisher").length()== 0){
				errorFlag = true;
				errorMsg.add("<h3>Please Provide Publisher</h3>");
			}else if(request.getParameter("edition").length()== 0){
				errorFlag = true;
				errorMsg.add("<h3>Please Provide Edition</h3>");
			}else if(request.getParameter("isbn").length()== 0){
				errorFlag = true;
				errorMsg.add("<h3>Please Provide Isbn</h3>");
			}else if(request.getParameter("year").length()== 0){
				errorFlag = true;
				errorMsg.add("<h3>Please Provide Year</h3>");
			}
			
			if(!errorFlag){
				//so no errors were found
				
				Book book = new Book();
				book.setTitle(request.getParameter("title").trim());
				book.setAuthor(request.getParameter("author").trim());
				book.setPublisher(request.getParameter("publisher").trim());
				book.setEdition(request.getParameter("edition").trim());
				book.setIsbn(request.getParameter("isbn").trim());
				book.setYearPub(request.getParameter("year").trim());
				
				ado.create(book);
			}
			
			
			
			
		}else if(update!= null && update.equalsIgnoreCase("update")){
			
//error validation 
			
			if(request.getParameter("title").length()== 0){
				errorFlag = true;
				errorMsg.add("<h3>Please Provide Title</h3>");
			}else if(request.getParameter("author").length()== 0){
				errorFlag = true;
				errorMsg.add("<h3>Please Provide Author</h3>");
			}else if(request.getParameter("publisher").length()== 0){
				errorFlag = true;
				errorMsg.add("<h3>Please Provide Publisher</h3>");
			}else if(request.getParameter("edition").length()== 0){
				errorFlag = true;
				errorMsg.add("<h3>Please Provide Edition</h3>");
			}else if(request.getParameter("isbn").length()== 0){
				errorFlag = true;
				errorMsg.add("<h3>Please Provide Isbn</h3>");
			}else if(request.getParameter("year").length()== 0){
				errorFlag = true;
				errorMsg.add("<h3>Please Provide Year</h3>");
			}
			
			if(!errorFlag){
				//so no errors were found
				
				Book book = new Book();
				
				book.setId(Integer.parseInt(request.getParameter("id").trim()));
				book.setTitle(request.getParameter("title").trim());
				book.setAuthor(request.getParameter("author").trim());
				book.setPublisher(request.getParameter("publisher").trim());
				book.setEdition(request.getParameter("edition").trim());
				book.setIsbn(request.getParameter("isbn").trim());
				book.setYearPub(request.getParameter("year").trim());
				
				ado.update(book);
			}		
			
		}else if(delete!= null && delete.equalsIgnoreCase("delete")){
		
			int rowNumber = Integer.parseInt(request.getParameter("id"));
			ado.delete(rowNumber);			
		}		

		// show jsp page with blank forms using redirect
		books = ado.readAll();
		if (books.size() == 0) {
			request.setAttribute("books", "0");
			
		} else {
			request.setAttribute("books", books);			
		}
		
		request.setAttribute("errors", errorMsg);
		request.setAttribute("errorFlag", errorFlag);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/library.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
