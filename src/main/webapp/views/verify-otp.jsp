<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Xác minh OTP</title>
</head>
<body>
    <div class="card shadow p-4" style="max-width: 450px; margin: 0 auto;">
        <h2 class="text-center mb-4 text-primary">Xác minh tài khoản</h2>
        <p class="text-center text-muted">
            Chúng tôi đã gửi mã OTP gồm 6 chữ số tới email đăng ký của bạn.
            Vui lòng nhập mã để kích hoạt tài khoản.
        </p>

        <c:if test="${alert != null}">
            <div class="alert alert-danger">${alert}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/verify-otp" method="post">
            <input type="hidden" name="username" value="${username}">
            <div class="mb-3">
                <label class="form-label">Mã OTP</label>
                <input type="text" name="otp" class="form-control" maxlength="6"
                       pattern="\d{6}" title="Nhập đúng 6 chữ số" required autofocus>
            </div>
            <button type="submit" class="btn btn-primary w-100">Xác nhận</button>
        </form>
    </div>
</body>
</html>
