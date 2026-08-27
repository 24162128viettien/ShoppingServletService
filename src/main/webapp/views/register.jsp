<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản</title>
    <!-- Thêm Bootstrap để form hiển thị đẹp và trực quan hơn -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">

    <div class="card shadow p-4" style="width: 450px;">
        <form action="${pageContext.request.contextPath}/register" method="post">
            <h2 class="text-center mb-4 text-primary">Tạo tài khoản mới</h2>
            
            <!-- Hiển thị thông báo lỗi nếu có (ví dụ: Tài khoản hoặc Email đã tồn tại) -->
            <c:if test="${alert != null}">
                <div class="alert alert-danger" role="alert">
                    ${alert}
                </div>
            </c:if>
            
            <div class="mb-3">
                <label class="form-label">Họ tên</label>
                <input type="text" placeholder="Nhập họ và tên" name="fullname" class="form-control" required>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" placeholder="Nhập địa chỉ Email" name="email" class="form-control" required>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Tài khoản</label>
                <input type="text" placeholder="Nhập tên tài khoản" name="username" class="form-control" required>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Số điện thoại</label>
                <input type="text" placeholder="Nhập số điện thoại" name="phone" class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Mật khẩu</label>
                <input type="password" placeholder="Nhập mật khẩu" name="password" class="form-control" required>
            </div>
            
            <button type="submit" class="btn btn-primary w-100 mb-3">Tạo tài khoản</button>
            
            <div class="text-center">
                <p class="mb-0">Nếu bạn đã có tài khoản? <a href="${pageContext.request.contextPath}/login" class="text-decoration-none">Đăng nhập</a></p>
            </div>
        </form>
    </div>

</body>
</html>