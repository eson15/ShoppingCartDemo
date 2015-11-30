package domain;
/**
 * @author Ni Shengwu
 * @description This javaBean encapsulate informations about a certain cart item
 */

//a cart item represent one book
public class CartItem {

	private Book book;
	
	@SuppressWarnings("unused")
	private double price; // price of a book
	
	private int count; // count(s) of book
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public double getPrice() {
		return book.getPrice() * count; // return total price of one kind of book
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}	
}
