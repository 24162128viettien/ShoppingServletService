<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Chi tiết sản phẩm</title>
</head>
<body>
    <c:if test="${product == null}">
        <p class="text-danger">Không tìm thấy sản phẩm.</p>
    </c:if>

    <c:if test="${product != null}">
        <div class="row">
            <div class="col-md-5">
                <c:choose>
                    <c:when test="${product.image != null}">
                        <img src="${pageContext.request.contextPath}/image?fname=${product.image}"
                             class="img-fluid rounded" alt="${product.name}">
                    </c:when>
                    <c:otherwise>
                        <div style="height:300px;background:#e9ecef;display:flex;align-items:center;justify-content:center;color:#999;">
                            Chưa có ảnh
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-md-7">
                <h2>${product.name}</h2>
                <p class="text-muted">Danh mục: ${product.category.name}</p>
                <h3 class="text-danger">
                    <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true"/> đ
                </h3>
                <p>Số lượng còn: ${product.quantity}</p>
                <p>${product.description}</p>
                <a href="${pageContext.request.contextPath}/product" class="btn btn-secondary">← Quay lại danh sách</a>
            </div>
        </div>
    </c:if>
</body>
</html>
