<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Đặt lại mật khẩu</title>
</head>
<body>
    <div class="card shadow p-4" style="max-width: 450px; margin: 0 auto;">
        <h2 class="text-center mb-4 text-primary">Đặt lại mật khẩu</h2>
        <p class="text-center text-muted">
            Nhập mã OTP đã gửi tới email và mật khẩu mới của bạn.
        </p>

        <c:if test="${alert != null}">
            <div class="alert alert-danger">${alert}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/reset-password" method="post">
            <input type="hidden" name="username" value="${username}">
            <div class="mb-3">
                <label class="form-label">Mã OTP</label>
                <input type="text" name="otp" class="form-control" maxlength="6"
                       pattern="\d{6}" title="Nhập đúng 6 chữ số" required autofocus>
            </div>
            <div class="mb-3">
                <label class="form-label">Mật khẩu mới</label>
                <input type="password" name="newPassword" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Xác nhận mật khẩu mới</label>
                <input type="password" name="confirmPassword" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Đặt lại mật khẩu</button>
        </form>
    </div>
</body>
</html>
