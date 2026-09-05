<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Tất cả sản phẩm</title>
</head>
<body>
    <h2>Tất cả sản phẩm</h2>

    <div class="row">
        <c:forEach var="p" items="${productList}">
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <c:choose>
                        <c:when test="${p.image != null}">
                            <img src="${pageContext.request.contextPath}/image?fname=${p.image}"
                                 class="card-img-top" style="height:200px;object-fit:cover;" alt="${p.name}">
                        </c:when>
                        <c:otherwise>
                            <div style="height:200px;background:#e9ecef;display:flex;align-items:center;justify-content:center;color:#999;">
                                Chưa có ảnh
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="card-body">
                        <h5 class="card-title">${p.name}</h5>
                        <p class="card-text text-muted small">${p.category.name}</p>
                        <p class="card-text text-danger fw-bold">
                            <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/> đ
                        </p>
                        <a href="${pageContext.request.contextPath}/product-detail?id=${p.id}"
                           class="btn btn-outline-primary btn-sm">Xem chi tiết</a>
                    </div>
                </div>
            </div>
        </c:forEach>
        <c:if test="${empty productList}">
            <p class="text-muted">Chưa có sản phẩm nào.</p>
        </c:if>
    </div>

    <nav>
        <ul class="pagination">
            <c:if test="${totalPages > 0}">
                <c:forEach begin="0" end="${totalPages - 1}" var="i">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="${pageContext.request.contextPath}/product?page=${i}">${i + 1}</a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
    </nav>
</body>
</html>
