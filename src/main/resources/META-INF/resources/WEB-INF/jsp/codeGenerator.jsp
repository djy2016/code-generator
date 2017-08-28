<!DOCTYPE HTML>
<html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>codeGenerator</title>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<!--this is for normal upload 如果用到原生态的，请避免引入ace样式-->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript"
	src="<%=path%>/generator/jquery-1.10.2.min.js"></script>
<style type="text/css">
div {
	position: absolute;
	width: 400px; height : 200px;
	left: 50%;
	top: 30%;
	margin-left: -200px;
	margin-top: -100px;
	height: 200px;
}
</style>
</head>
<body>
	<div>
		<h2>CodeGenerator</h2>
		<h4>生成后自动打开输出路径目录，详细生成信息请查看控制台</h4>
		<form action="${ctx}/codeGenerator/generate" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<table>
				<tr>
					<td>包名</td>
					<td><input type="text" name="projectPackage" size="40" placeholder="com.ouyeel.xplat"/></td>
				</tr>
				<tr>
					<td>数据库url</td>
					<td><input type="text" name="url" size="40" placeholder="jdbc:mysql://localhost:3306/xplat"/></td>
				</tr>
				<tr>
					<td>数据库用户名</td>
					<td><input type="text" name="user" size="40" placeholder="name"/></td>
				</tr>
				<tr>
					<td>数据库密码</td>
					<td><input type="text" name="pwd" size="40" placeholder="password"/></td>
				</tr>
				<tr>
					<td>表名(%为全选)</td>
					<td><input type="text" name="tables" size="40" placeholder="tables"/></td>
				</tr>
				<tr>
					<td>schema</td>
					<td><input type="text" name="schema" size="40" placeholder="不填默认为mysql"/></td>
				</tr>
				<tr>
					<td>输出路径</td>
					<td><input type="text" name="outputPath" size="40" placeholder="D:/temp"/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:right"><input type="submit" value="生成" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>