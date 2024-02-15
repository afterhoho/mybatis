<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Mail 전송 결과</h1>
	<c:if test="${check==1}">성공적으로 전송되었습니다</c:if>
    <c:if test="${check!=1}">메일전송이 실패되었습니다</c:if>
    <c:if test="${check==null}">메일전송이 실패되었습니다   Null</c:if>
</body>
</html>