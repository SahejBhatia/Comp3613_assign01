/**
 * Project: A00766737Assignment1
 * File: Book.java
 * Date: Feb 19, 2017
 * Time: 1:14:44 AM
 */

package a00766737.data;

/**
 * @author sehaj A00766737
 *
 */
public class Book {
	
	private int id;
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", Author=" + Author + ", isbn=" + isbn + ", publisher="
				+ publisher + ", edition=" + edition + ", yearPub=" + yearPub + "]";
	}

	private String title;
	private String Author;
	private String isbn;
	private String publisher;
	private String edition;
	private String yearPub;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getYearPub() {
		return yearPub;
	}

	public void setYearPub(String yearPub) {
		this.yearPub = yearPub;
	}


	
	
	public Book(){}
	
	public Book(int id, String title, String author, String isbn, String publisher, String edition, String yearPub) {
		super();
		this.id = id;
		this.title = title;
		Author = author;
		this.isbn = isbn;
		this.publisher = publisher;
		this.edition = edition;
		this.yearPub = yearPub;
	}
	

}
