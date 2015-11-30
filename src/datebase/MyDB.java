package datebase;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.Book;

/**
 * @author Ni Shengwu
 * @description This simple project does not use database, so we write a class to simulate a database
 */
public class MyDB {

	private static Map<String, Book> map = new LinkedHashMap<String, Book>(); //to store books where key=bookid and value=book
	//initiate some books
	static{
		map.put("1", new Book("1","javaweb开发","老张",20,"一本经典的书"));
		map.put("2", new Book("2","jdbc开发","李勇",30,"一本经典的书"));
		map.put("3", new Book("3","spring开发","老倪",40,"一本经典的书"));
		map.put("4", new Book("4","hibernate开发","老张",50,"一本经典的书"));
		map.put("5", new Book("5","ajax开发","老毕",20,"一本经典的书"));
	}
	//get the map which store all books
	public static Map<String, Book> getAll(){
		return map;
	}
}
