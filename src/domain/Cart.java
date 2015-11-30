package domain;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ni Shengwu
 * @description This javaBean encapsulate informations of shopping cart
 */
public class Cart {
	
	//create a map for storing cart items. We cannot store book immediately because that will bring about same book in cart.
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>(); 
	
	@SuppressWarnings("unused")
	private double allPrice; // total price of books
	
	// Add a book to cart
	public void addToCart(Book book){
		CartItem item = map.get(book.getId()); //get cart item of one book by book id
		if(item != null){
			item.setCount(item.getCount() + 1); //increase count for one book with certain id
		}
		else{
			item = new CartItem();// if cart item is null, create a new item and set the corresponding fields.
			item.setBook(book);
			item.setPrice(book.getPrice());
			item.setCount(1);
			map.put(book.getId(), item); // add cart item into map where key=bookid value=cartItem
		}
	}
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public double getAllPrice() {
		
		double totalPrice = 0;
		for(Map.Entry<String, CartItem> me : map.entrySet()){
			CartItem item = me.getValue();
			totalPrice += item.getPrice();
		}
		return totalPrice;
	}
	public void setAllPrice(double allPrice) {
		this.allPrice = allPrice;
	}	
}
