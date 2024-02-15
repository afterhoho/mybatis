<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String context = request.getContextPath();
%>
<body>
<h2>회원정보(JPA)  수정</h2>
<form action="<%=context%>/memberJpa/memberUpdate" method="post">
  <input type="hidden" name="id" value="${member.id }">
         
  <table>  
	<tr><th>회원번호</th><td>${member.id  }</td></tr>
	<tr><th>이름</th><td>
	    <input type="text" name="name" 
		required="required" value="${member.name }" ></td></tr>
	<%-- <tr><th>비밀번호</th><td>  <input type="text" name="password"  value="${member.password }" ></td></tr> --%>
	<tr><td colspan="2">
	   <input type="submit" value="확인">
	   </td>
	</tr>
  </table>
</form>
</body>
</html>