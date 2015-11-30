package domain;
/**
 * @author Ni Shengwu
 * @description This javaBean encapsulate informations about a book
 */
public class Book {
	
	private String id; // identification of book
	private String name; // book name
	private String author; // author of book
	private double price; // price of one book
	private String description; // description about book

	// Constructor using fields
	public Book(String id, String name, String author, double price, String description) { 
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
