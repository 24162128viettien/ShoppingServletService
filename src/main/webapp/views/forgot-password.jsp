<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Quên mật khẩu</title>
</head>
<body>
    <div class="card shadow p-4" style="max-width: 450px; margin: 0 auto;">
        <h2 class="text-center mb-4 text-primary">Quên mật khẩu</h2>
        <p class="text-center text-muted">
            Nhập tài khoản hoặc email đã đăng ký, chúng tôi sẽ gửi mã OTP để đặt lại mật khẩu.
        </p>

        <c:if test="${alert != null}">
            <div class="alert alert-danger">${alert}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/forgot-password" method="post">
            <div class="mb-3">
                <label class="form-label">Tài khoản hoặc Email</label>
                <input type="text" name="usernameOrEmail" class="form-control" required autofocus>
            </div>
            <button type="submit" class="btn btn-primary w-100 mb-3">Gửi mã OTP</button>
            <div class="text-center">
                <a href="${pageContext.request.contextPath}/login" class="text-decoration-none">← Quay lại đăng nhập</a>
            </div>
        </form>
    </div>
</body>
</html>
