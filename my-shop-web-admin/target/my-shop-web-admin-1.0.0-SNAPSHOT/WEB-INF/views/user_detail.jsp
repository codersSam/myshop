<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的商城 | 查看详情</title>
    <jsp:include page="../includes/header.jsp"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="box-body table-responsive">
    <table class="table">
        <tbody>
        <tr>
            <th>姓名：</th>
            <th>${tbUser.username}</th>
        </tr>
        <tr>
            <th>手机：</th>
            <th>${tbUser.phone}</th>
        </tr>
        <tr>
            <th>邮箱：</th>
            <th>${tbUser.email}</th>
        </tr>
        <tr>
            <th>创建时间：</th>
            <th><fmt:formatDate value="${tbUser.created}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></th>
        </tr>
        <tr>
            <th>更新时间：</th>
            <th><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></th>
        </tr>
        </tbody>
    </table>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
</body>
</html>
