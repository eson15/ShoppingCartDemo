package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BusinessService;
import domain.Cart;
/**
 * @author Ni Shengwu
 * @description add a certain book into shopping cart
 * 				without processing repeat submit issue! need to optimize later!
 */
public class BuyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookid = request.getParameter("bookid");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);//create a new cart and add it into session scope
		}
		BusinessService service = new BusinessService();
		service.addToCart(bookid, cart);
		request.getRequestDispatcher("/WEB-INF/jsp/listCart.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
