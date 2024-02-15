<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html><html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	   <h2>부서 정보 Procedure Cursor List</h2>
	   <table>
		<tr><th>부서명</th><th>부서이름</th><th>근무지</th></tr>
		<c:forEach var="listDept" items="${deptLists}">
			<tr><td>${listDept.deptno }</td>
			    <td>${listDept.dname }</td>
				<td>${listDept.loc }</td>
			</tr>
		</c:forEach>
	   </table>     
	
	
</body>
</html>