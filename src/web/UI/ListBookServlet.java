package web.UI;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Book;
import service.BusinessService;
import sun.misc.BASE64Encoder;

//display all books
public class ListBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BusinessService service = new BusinessService();
		Map<String, Book> map = service.getAllBook();
		request.setAttribute("map", map);
		String token = TokenProcessor.getInstance().makeToken();//get random number
		request.getSession().setAttribute("token", token);//store random number in session
		request.getRequestDispatcher("/WEB-INF/jsp/listBook.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}

class TokenProcessor {		
	/*
	 * 随机数应该要保证唯一性，所以最好用一个对象来生成。所以该类需要设计成单态的。
	 * 单态设计模式：(保证类的对象在内存中只有一个)
	 * 1. 把类的构造函数私有
	 * 2. 自己创建一个类的对象
	 * 3. 对外提供一个公共方法，返回类的对象
	 */		
	private TokenProcessor(){}
	private static final TokenProcessor instance = new TokenProcessor();
	
	public static TokenProcessor getInstance() {
		return instance;
	}
	
	//create a random number
	public String makeToken() {
					
		String token = (System.currentTimeMillis() + new Random().nextInt(9999999)) + "";	
		/*
		 * 数据指纹：不管数据多大，得到的数据指纹都是相同大小，128位长-16字节
		 * 拿到数据指纹再反推原始数据是不可能的。
		 * 我们可以使用md5算法可以得到数据指纹。
		 */
		try {
			MessageDigest md = MessageDigest.getInstance("md5");//获得md5算法
			byte md5[] = md.digest(token.getBytes());//将上面的token转成字节后，获得其指纹，存到字节数组中
			/*
			 * 不能像下面这样return，可能会出现乱码
			 * 因为md5中保存的是字节，new String(md5)的时候会将字节转成字符串，这样就必然要去查码表，如果没指定就查gb2312
			 * 如果相应的字节在码表中没有那就出现乱码
			 */
			//return new String(md5);
			
			/*
			 * base64编码：将任意二进制编码成明文字符。
			 * 如：把3字节变成4字节，并且把每一个字节的数据变成一个字符串
			 * 假设有三字节数据： 01010000    01010011    01100101  总共24位
			 * 变成4字节的话，每个字节存储该数据的6位，剩下2位补0，
			 * 所以base64编码后的数据为： 00010100 00000101 00001101 00100101
			 * 编码后的数据，最小值为0，最大值为63，共有64个数据，故称为base64编码
			 * 在0-63之间都有字符与之对应，这些字符都是键盘上可以看到的某个字符。
			 */
			BASE64Encoder encoder = new BASE64Encoder();//第一次使用的时候可能无法import包，解决方案如下：
			/*
			 * 解决方案1（推荐）：
			 * 	只需要在project build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
			 * 解决方案2：
			 * 	Windows -> Preferences -> Java -> Compiler -> Errors/Warnings ->
			 * 	Deprecated and trstricted API -> Forbidden reference (access rules): -> 改为warning
			 */
			return encoder.encode(md5);						
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);//转成运行时异常，这样上面就不需要处理了
			//一般情况下，都是将异常转成运行时异常往上抛；
			//有一种情况：希望上面处理这个异常，那么我们抛编译时异常。
		}
	}	
}
