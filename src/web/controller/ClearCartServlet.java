package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BusinessService;
import domain.Cart;
import exception.CartNotFoundException;
/**
 * @author Ni Shengwu
 * @description clear shopping cart
 */
public class ClearCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			BusinessService service = new BusinessService();
			service.clearCart(cart);
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
