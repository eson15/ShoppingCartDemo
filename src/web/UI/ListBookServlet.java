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
	 * �����Ӧ��Ҫ��֤Ψһ�ԣ����������һ�����������ɡ����Ը�����Ҫ��Ƴɵ�̬�ġ�
	 * ��̬���ģʽ��(��֤��Ķ������ڴ���ֻ��һ��)
	 * 1. ����Ĺ��캯��˽��
	 * 2. �Լ�����һ����Ķ���
	 * 3. �����ṩһ������������������Ķ���
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
		 * ����ָ�ƣ��������ݶ�󣬵õ�������ָ�ƶ�����ͬ��С��128λ��-16�ֽ�
		 * �õ�����ָ���ٷ���ԭʼ�����ǲ����ܵġ�
		 * ���ǿ���ʹ��md5�㷨���Եõ�����ָ�ơ�
		 */
		try {
			MessageDigest md = MessageDigest.getInstance("md5");//���md5�㷨
			byte md5[] = md.digest(token.getBytes());//�������tokenת���ֽں󣬻����ָ�ƣ��浽�ֽ�������
			/*
			 * ��������������return�����ܻ��������
			 * ��Ϊmd5�б�������ֽڣ�new String(md5)��ʱ��Ὣ�ֽ�ת���ַ����������ͱ�ȻҪȥ��������ûָ���Ͳ�gb2312
			 * �����Ӧ���ֽ��������û���Ǿͳ�������
			 */
			//return new String(md5);
			
			/*
			 * base64���룺����������Ʊ���������ַ���
			 * �磺��3�ֽڱ��4�ֽڣ����Ұ�ÿһ���ֽڵ����ݱ��һ���ַ���
			 * ���������ֽ����ݣ� 01010000    01010011    01100101  �ܹ�24λ
			 * ���4�ֽڵĻ���ÿ���ֽڴ洢�����ݵ�6λ��ʣ��2λ��0��
			 * ����base64����������Ϊ�� 00010100 00000101 00001101 00100101
			 * ���������ݣ���СֵΪ0�����ֵΪ63������64�����ݣ��ʳ�Ϊbase64����
			 * ��0-63֮�䶼���ַ���֮��Ӧ����Щ�ַ����Ǽ����Ͽ��Կ�����ĳ���ַ���
			 */
			BASE64Encoder encoder = new BASE64Encoder();//��һ��ʹ�õ�ʱ������޷�import��������������£�
			/*
			 * �������1���Ƽ�����
			 * 	ֻ��Ҫ��project build path�����Ƴ�JRE System Library������ӿ�JRE System Library�����±�����һ�������ˡ�
			 * �������2��
			 * 	Windows -> Preferences -> Java -> Compiler -> Errors/Warnings ->
			 * 	Deprecated and trstricted API -> Forbidden reference (access rules): -> ��Ϊwarning
			 */
			return encoder.encode(md5);						
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);//ת������ʱ�쳣����������Ͳ���Ҫ������
			//һ������£����ǽ��쳣ת������ʱ�쳣�����ף�
			//��һ�������ϣ�����洦������쳣����ô�����ױ���ʱ�쳣��
		}
	}	
}
