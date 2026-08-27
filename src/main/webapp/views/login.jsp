<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <!-- Bootstrap giống hệt register.jsp để đồng bộ giao diện -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">

    <div class="card shadow p-4" style="width: 450px;">
        <form action="${pageContext.request.contextPath}/login" method="post">
            <h2 class="text-center mb-4 text-primary">Đăng Nhập Vào Hệ Thống</h2>

            <!-- Hiển thị thông báo lỗi nếu có -->
            <c:if test="${alert != null}">
                <div class="alert alert-danger" role="alert">
                    ${alert}
                </div>
            </c:if>

            <div class="mb-3">
                <label class="form-label">Tài khoản</label>
                <input type="text" placeholder="Nhập tên tài khoản" name="username" class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Mật khẩu</label>
                <input type="password" placeholder="Nhập mật khẩu" name="password" class="form-control" required>
            </div>

            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" name="remember" id="rememberCheck">
                <label class="form-check-label" for="rememberCheck">Nhớ tôi</label>
            </div>

            <button type="submit" class="btn btn-primary w-100 mb-3">Đăng nhập</button>

            <div class="text-center">
                <p class="mb-0">Nếu bạn chưa có tài khoản trên hệ thống? <a href="${pageContext.request.contextPath}/register" class="text-decoration-none">Đăng ký</a></p>
            </div>
        </form>
    </div>

</body>
</html>
