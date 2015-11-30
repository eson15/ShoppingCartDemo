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
 * 
 */
public class BuyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	
		boolean b = isToken(request);//判断表单带过来的token是否与服务器端的一致
		if(b == true) {
			request.getSession().removeAttribute("token");//处理提交请求之前，需要先将session中保存的随机数移除掉
			
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
		else{
			request.getRequestDispatcher("/WEB-INF/jsp/listCart.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	
	private boolean isToken(HttpServletRequest request) {
		
		String clientToken = request.getParameter("token");//客户端token
		if(clientToken == null)
			return false;
		String serverToken = (String)request.getSession().getAttribute("token");//服务器端token
		if(serverToken == null)//如果已经提交过一次了，服务器会把随机数移除掉
			return false;		
		if(!clientToken.equals(serverToken))
			return false;			
		return true;
	}

}
