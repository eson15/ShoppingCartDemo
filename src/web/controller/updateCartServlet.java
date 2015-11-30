package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cart;
import exception.CartNotFoundException;
import service.BusinessService;

public class updateCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
				String bookid = request.getParameter("bookid");
				int count = Integer.parseInt(request.getParameter("count"));
				Cart cart = (Cart) request.getSession().getAttribute("cart");
				BusinessService service = new BusinessService();
				service.updateCart(bookid, cart, count);
				request.getRequestDispatcher("/WEB-INF/jsp/listCart.jsp").forward(request, response);
			} catch (CartNotFoundException e) {
				e.printStackTrace();
				request.setAttribute("message", "对不起，清空失败！");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}
			
	}	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
