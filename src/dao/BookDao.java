package dao;

import java.util.Map;
import datebase.MyDB;
import domain.Book;
/**
 * @author Ni Shengwu
 * @description this dao is to operation "database"(MyDB) 
 */
public class BookDao {
	//to get the map which stores all books
	public Map<String, Book> getAllBook(){
		return MyDB.getAll();
	}
	//to get a certain book with book id
	public Book findBook(String bookId){
		return MyDB.getAll().get(bookId);
	}
}
