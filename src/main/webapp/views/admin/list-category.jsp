<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý danh mục</title>
</head>
<body>
    <h2>Danh sách danh mục</h2>
    
    <a href="<c:url value='/admin/category/add'/>">Thêm danh mục mới</a>
    <a href="${pageContext.request.contextPath}/home">← Về Trang chủ</a>
    <br><br>

    <table border="1" cellspacing="0" cellpadding="10">
        <thead>
            <tr>
                <th>STT</th>
                <th>Hình ảnh</th>
                <th>Tên danh mục</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${cateList}" var="cate" varStatus="STT">
                <tr>
                    <td>${STT.index + 1}</td>
                    <c:url value="/image?fname=${cate.icon}" var="imgUrl"/>
                    <td><img height="100" width="150" src="${imgUrl}" alt="Icon" /></td>
                    <td>${cate.name}</td>
                    <td>
                        <a href="<c:url value='/admin/category/edit?id=${cate.id}'/>">Sửa</a> | 
                        <a href="<c:url value='/admin/category/delete?id=${cate.id}'/>">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>