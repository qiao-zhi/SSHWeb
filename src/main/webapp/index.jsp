<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseurl" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>首页
<form action = "${baseurl}/cus/saveCus.do" method="post">
	<input type="text" name="c.cust_name" title="姓名">
	<br/>
	<input type="text" name="c.cust_phone" title="电话">
	<br/>
	<input type="text" name="c.cust_industry" title="行业">
	<br/>
	<input type="submit" value="提交">
</form> 
</body>
</html>