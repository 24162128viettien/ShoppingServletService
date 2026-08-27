<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Chủ - Shopping</title>
</head>
<body>
    <h1>Chào mừng bạn đã đăng nhập thành công vào hệ thống!</h1>
    <a href="${pageContext.request.contextPath}/admin/category/list">Quản lý danh mục</a> |
    <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
</body>
</html>
