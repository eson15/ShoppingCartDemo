<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>list of shopping cart</title>
    <script type="text/javascript">
    	function deleteBook(bookid){
    		var b = window.confirm("您确定要删除此商品？");
    		if(b){
    			window.location.href = "${pageContext.request.contextPath }/servlet/DeleteServlet?bookid=" + bookid;
    		}
    	}
    	
    	function clearCart(){
    		var b = window.confirm("您确定要清空购物车？");
    		if(b){
    			window.location.href = "${pageContext.request.contextPath }/servlet/ClearCartServlet";
    		}
    	}
    	
    	function updateCart(input,bookid,oldValue){
    		var count = input.value;
    		//需要判断用户输入的是否合法，比如输入a,-1等都不合法
    		//需要用到javascript的正则表达式，这里就不写了，到时候再学习一下
    		var b = window.confirm("您确定修改为" + count);
    		if(b){
    			window.location.href = "${pageContext.request.contextPath }/servlet/updateCartServlet?bookid=" + bookid + "&count=" + count;
    		}
    		else{
    			input.value = oldValue;//如果发现用户取消的话，将值改为原始值
    		}
    	}
    </script>
  </head>
  
  <body style="text-align:center;">
  <h2>我的购物车</h2>
  	<c:if test="${!empty(cart.map)}">
	    <table style="text-align:center; margin:0 auto;" border="1" width="50%"> 
	    	<tr>
	    		<td>书籍编号</td>
	    		<td>书名</td>
	    		<td>作者</td>
	    		<td>价格</td>
	    		<td>数量</td>
	    		<td>小计</td>
	    		<td>操作</td>
	    	</tr>
	    	<c:forEach var="me" items="${cart.map }">
	    		<tr>
	    			<td>${me.key }</td>
	    			<td>${me.value.book.name }</td>
	    			<td>${me.value.book.author }</td>
	    			<td>${me.value.book.price }</td>
	    			<td>
	    				<input type="text" name="count" value="${me.value.count }" style="text-align:center; width=50%" onchange="updateCart(this,${me.value.book.id},${me.value.count })"/>
	    			</td>
	    			<td>${me.value.price }</td>
	    			<td>
	    				<a href="javascript:deleteBook(${me.key})">删除</a>
	    			</td>
	    		</tr>
	    	</c:forEach>
	    	<tr>
	    		<td colspan="2">总额</td>
	    		<td colspan="2">${cart.allPrice }</td>
	    		<td colspan="3">
					<a href="javascript:clearCart()">清空购物车</a>
				</td>
	    	</tr>
	    </table>
    </c:if>
    <c:if test="${empty(cart.map)}">
    	对不起，购物车为空！
    </c:if>
  </body>
</html>
