<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Quản lý sản phẩm</title>
</head>
<body>
    <h2>Danh sách sản phẩm</h2>
    <a href="${pageContext.request.contextPath}/admin/product/add" class="btn btn-primary mb-3">Thêm sản phẩm mới</a>
    <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary mb-3">← Về Trang chủ</a>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>STT</th>
                <th>Hình ảnh</th>
                <th>Tên sản phẩm</th>
                <th>Danh mục</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${productList}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1 + currentPage * 5}</td>
                    <td>
                        <c:if test="${p.image != null}">
                            <img src="${pageContext.request.contextPath}/image?fname=${p.image}" style="width:60px;height:60px;object-fit:cover;">
                        </c:if>
                    </td>
                    <td>${p.name}</td>
                    <td>${p.category.name}</td>
                    <td><fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/> đ</td>
                    <td>${p.quantity}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/product/edit?id=${p.id}">Sửa</a> |
                        <a href="${pageContext.request.contextPath}/admin/product/delete?id=${p.id}"
                           onclick="return confirm('Xác nhận xóa sản phẩm này?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <nav>
        <ul class="pagination">
            <c:if test="${totalPages > 0}">
                <c:forEach begin="0" end="${totalPages - 1}" var="i">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="${pageContext.request.contextPath}/admin/product/list?page=${i}">${i + 1}</a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
    </nav>
</body>
</html>
