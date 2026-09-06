<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Thêm sản phẩm</title>
</head>
<body>
    <div class="card shadow p-4" style="max-width: 600px; margin: 0 auto;">
        <h2 class="text-center mb-4 text-primary">Thêm sản phẩm mới</h2>

        <form action="${pageContext.request.contextPath}/admin/product/add" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label class="form-label">Tên sản phẩm</label>
                <input type="text" name="name" class="form-control" value="${product.name}" required>
                <c:if test="${errors.name != null}"><div class="text-danger small">${errors.name}</div></c:if>
            </div>
            <div class="mb-3">
                <label class="form-label">Danh mục</label>
                <select name="categoryId" class="form-control" required>
                    <c:forEach var="c" items="${cateList}">
                        <option value="${c.id}">${c.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Giá</label>
                <input type="number" step="0.01" name="price" class="form-control" value="${product.price}" required>
                <c:if test="${errors.price != null}"><div class="text-danger small">${errors.price}</div></c:if>
            </div>
            <div class="mb-3">
                <label class="form-label">Số lượng</label>
                <input type="number" name="quantity" class="form-control" value="${empty product ? 0 : product.quantity}" required>
                <c:if test="${errors.quantity != null}"><div class="text-danger small">${errors.quantity}</div></c:if>
            </div>
            <div class="mb-3">
                <label class="form-label">Mô tả</label>
                <textarea name="description" class="form-control" rows="4"></textarea>
            </div>
            <div class="mb-3">
                <label class="form-label">Hình ảnh</label>
                <input type="file" name="image" class="form-control" accept="image/*">
            </div>
            <button type="submit" class="btn btn-primary w-100">Thêm sản phẩm</button>
        </form>
    </div>
</body>
</html>
