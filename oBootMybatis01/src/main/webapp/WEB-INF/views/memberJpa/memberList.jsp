<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>JPA 직원(member) 목록</h2><a href="memberJpa/new">입력</a>
	<c:set var="num" value="1"></c:set>
	
	<table>
		<tr><th >번호</th><th>사번</th><th>이름</th><th>비번</th></tr>
		<c:forEach var="member" items="${members }">
			<tr><td>${num }</td><td>${member.id }</td>
			<td><a href="memberJpa/memberUpdateForm?id=${member.id}">${member.name}</a></td>
			<td>${member.password }</td>
			<c:set var="num" value="${num + 1 }"></c:set>
		</c:forEach>
</table>
</body>
</html>