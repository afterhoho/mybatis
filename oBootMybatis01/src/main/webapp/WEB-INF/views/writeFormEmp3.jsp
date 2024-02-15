<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!-- Validation check -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>직원정보 입력(Validation check용도)</h2>
	<c:if test="${msg!=null}">${msg}</c:if>
	<form:form action="writeEmp3" method="post" name="frm" modelAttribute="emp">
		<table>
			<tr><th>사번</th><td><input type="number" name="empno" required="required" maxlength="4"
										value="${empno}">
								<input type="button" value="중복확인" onclick="chk()">
							</td>
			</tr>
			<tr><th>이름</th><td><input type="text" name="ename" >
								<form:errors path="ename"/>
							
							</td></tr>
			
			<tr><th>업무</th><td><input type="text" name="job" required="required"></td></tr>
			<tr><th>급여</th><td><input type="number" name="sal" required="required"></td></tr>
			<tr><th>입사일</th><td><input type="date" name="hiredate" required="required"></td></tr>
			<tr><th>보너스</th><td><input type="number" name="comm" required="required"></td></tr>
			<tr><th>관리자사번</th><td>
				<select name="mgr">
					<c:forEach var="emp" items="${empMngList}">
						<option value="${emp.empno}">${emp.ename}</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr><th>부서코드</th><td>
				<select name="deptno">
					<c:forEach var="dept" items="${deptList}">
						<option value="${dept.deptno}">${dept.dname}</option>
					</c:forEach>
				</select></td>
			</tr>
			
			<tr><td colspan="2"><input type="submit" value="확인"></td></tr>
			
		</table>
	</form:form>
</body>
</html>