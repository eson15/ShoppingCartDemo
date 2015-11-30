package service;

import java.util.Map;

import dao.BookDao;
import domain.Book;
import domain.Cart;
import domain.CartItem;
import exception.CartNotFoundException;

/**
 * @author Ni Shengwu
 * @description This is service layer. Service layer is to operate business
 */
public class BusinessService {
	BookDao dao = new BookDao();
	
	//display all books
	public Map<String, Book> getAllBook(){
		return null;
	}
	
	//add a certain book to cart
	public void addToCart(String bookid, Cart cart){
		Book book = dao.findBook(bookid);
		cart.addToCart(book);
	}
	
	//delete a certain book
	public void deleteBook(String bookid, Cart cart) throws CartNotFoundException{
		if(cart == null){
			throw new CartNotFoundException("sorry, empty shopping cart!");//self-defining exception.
		}
		cart.getMap().remove(bookid);
	}
	
	//clear cart
	public void clearCart(Cart cart) throws CartNotFoundException{
		if(cart == null){
			throw new CartNotFoundException("sorry, empty shopping cart!");
		}
		cart.getMap().clear();
	}
	
	//update cart data: change the book count
	public void updateCart(String bookid, Cart cart, int count) throws CartNotFoundException{
		if(cart == null){
			throw new CartNotFoundException("sorry, empty shopping cart!");
		}
		CartItem item = cart.getMap().get(bookid);
		item.setCount(count);
	}
}
