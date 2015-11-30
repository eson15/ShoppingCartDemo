<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>display all books</title>
  </head>
  
  <body style="text-align:center;">
    <h2>书籍列表</h2>
    <table border="1" width="40%" style="text-align:center; margin:0 auto;">
    	<tr>
    		<td>书籍编号</td>
    		<td>书名</td>
    		<td>作者</td>
    		<td>价格</td>
    		<td>描述</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach var="me" items="${map }">
    		<tr>
	    		<td>${me.key }</td>  <!--me.key拿到的是String,也是书的id -->
	    		<td>${me.value.name }</td>
	    		<td>${me.value.author }</td>
	    		<td>${me.value.price }</td>
	    		<td>${me.value.description }</td>
	    		<td>
	    			<a href="${pageContext.request.contextPath }/servlet/BuyServlet?bookid=${me.key}">加入购物车</a>
	    		</td>
    		</tr>
    	</c:forEach>
    </table>
  </body>
</html>
