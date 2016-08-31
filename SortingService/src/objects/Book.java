package objects;

public class Book {
	String title;
	String author;
	Integer year;
	
	public Book(String title, String author, Integer year) {
		this.title = title;
		this.author = author;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public Integer getYear() {
		return year;
	}
}