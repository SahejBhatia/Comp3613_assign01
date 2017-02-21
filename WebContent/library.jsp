<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Library</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
    </head>
<body>
<%@ page import =" java.util.ArrayList, java.util.List, a00766737.data.Book"%>
<% ArrayList<Book> booksC ; %>

<header>
	<h2><strong>Your Library!</strong></h2>
	</header>
	<table>
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Edition</th>
			<th>ISBN</th>
			<th>Year Published</th>
		</tr>

<% 
	if(request.getAttribute("books").equals("0")){
		%>
		<tr>
		<form action="/A00766737Assignment1/Assignment1" method = "post">
			
				<td><input type ="text" name = "id"></td>
				<td><input type ="text" name = "title"></td>
				<td><input type ="text" name = "author"></td>
				<td><input type ="text" name = "publisher"></td>
				<td><input type ="text" name = "edition"></td>
				<td><input type ="text" name = "Isbn"></td>
				<td><input type ="text" name = "year"></td>
				<td>
				<input type = "submit" name = "update" value = "Update">
				<input type = "submit" name = "delete" value = "Delete">
				</td>
		</form>	
			</tr>
		<%		
	}else{		
		//iterate through books array list
		booksC= new  ArrayList<Book>();
		booksC = (ArrayList) request.getAttribute("books");
			
		for(Book b: booksC){
			%>
			<tr>
			<form action="/A00766737Assignment1/Assignment1" method = "post">
			
					<td><input type ="text" name = "id" value = "<%= b.getId() %>"></td>
					<td><input type ="text" name = "title" value = "<%= b.getTitle() %>" ></td>
					<td><input type ="text" name = "author" value = " <%= b.getAuthor() %> "></td>
					<td><input type ="text" name = "publisher" value = " <%= b.getPublisher() %>"></td>
					<td><input type ="text" name = "edition" value = " <%= b.getEdition() %>"></td>
					<td><input type ="text" name = "isbn" value = " <%= b.getIsbn() %> "> </td>
					<td><input type ="text" name = "year" value = " <%= b.getYearPub() %>"></td>
					<td>
					<input type = "submit" name = "update" value = "update">
					<input type = "submit" name = "delete" value = "delete">
					</td>			
			</form>	
			
		</tr>
				
	<%	}		
	}

%>
<tr>
		<form action="/A00766737Assignment1/Assignment1" metho = "post">
			
				<td><strong>Autofill</strong></td>
				<td><input type="text" name="title" required></td>
				<td><input type="text" name="author" required></td>
				<td><input type="text" name="publisher" required></td>
				<td><input type="text" name="edition" required></td>
				<td><input type="text" name="isbn" required></td>
				<td><input type="text" name="year" required></td>
				<td><input name="insert" type="submit" value="insert"><input name="reset" type="reset" value="Clear"></td>
		</form>
			</tr>
		
</table>	


</body>
</html>