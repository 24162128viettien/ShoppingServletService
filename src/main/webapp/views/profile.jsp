<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Tài khoản của tôi</title>
</head>
<body>
    <!-- Không cần <html>/<head>/<body> đầy đủ với Sitemesh - decorator sẽ tự bọc ngoài -->
    <div class="card shadow p-4" style="max-width: 500px; margin: 0 auto;">
        <h2 class="text-center mb-4 text-primary">Thông tin tài khoản</h2>

        <c:if test="${error != null}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <c:if test="${success != null}">
            <div class="alert alert-success">${success}</div>
        </c:if>

        <div class="text-center mb-3">
            <c:choose>
                <c:when test="${user.images != null}">
                    <img src="${pageContext.request.contextPath}/image?fname=${user.images}"
                         alt="Avatar" style="width:120px;height:120px;object-fit:cover;border-radius:50%;">
                </c:when>
                <c:otherwise>
                    <div style="width:120px;height:120px;border-radius:50%;background:#e9ecef;
                                display:inline-flex;align-items:center;justify-content:center;color:#999;">
                        Chưa có ảnh
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label class="form-label">Tài khoản</label>
                <input type="text" class="form-control" value="${user.username}" disabled>
            </div>
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="text" class="form-control" value="${user.email}" disabled>
            </div>
            <div class="mb-3">
                <label class="form-label">Họ tên</label>
                <input type="text" name="fullname" class="form-control" value="${user.fullname}" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Số điện thoại</label>
                <input type="text" name="phone" class="form-control" value="${user.phone}"
                       pattern="^0\d{9}$" title="Số điện thoại phải đủ 10 số, bắt đầu bằng 0" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Đổi ảnh đại diện (để trống nếu không đổi)</label>
                <input type="file" name="images" class="form-control" accept="image/*">
            </div>
            <button type="submit" class="btn btn-primary w-100">Cập nhật</button>
        </form>
    </div>
</body>
</html>
