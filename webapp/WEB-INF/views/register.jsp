<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký Khách hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
    <style>
        .register-container {
            max-width: 500px;
            margin: 50px auto;
            padding: 2rem;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .register-container h2 {
            text-align: center;
            color: var(--primary);
            margin-bottom: 1.5rem;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h2>Đăng ký Tài khoản Khách hàng</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/register">
            <div class="form-group">
                <label for="maKH">Mã khách hàng</label>
                <input type="text" id="maKH" name="maKH" required>
            </div>
            <div class="form-group">
                <label for="hoTenKH">Họ tên</label>
                <input type="text" id="hoTenKH" name="hoTenKH" required>
            </div>
            <div class="form-group">
                <label for="soDienThoai">Số điện thoại</label>
                <input type="text" id="soDienThoai" name="soDienThoai" required>
            </div>
            <div class="form-group">
                <label for="diaChiKH">Địa chỉ</label>
                <input type="text" id="diaChiKH" name="diaChiKH" required>
            </div>
            <div class="form-group">
                <label for="ngaySinh">Ngày sinh</label>
                <input type="date" id="ngaySinh" name="ngaySinh">
            </div>
            <div class="form-group">
                <label for="gioiTinh">Giới tính</label>
                <select id="gioiTinh" name="gioiTinh">
                    <option value="Nam">Nam</option>
                    <option value="Nu">Nữ</option>
                </select>
            </div>
            <div class="form-group">
                <label for="username">Tên đăng nhập</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" style="width: 100%;">Đăng ký</button>
        </form>
        <p style="text-align: center; margin-top: 1rem;">
            <a href="${pageContext.request.contextPath}/login">Đã có tài khoản? Đăng nhập</a>
        </p>
    </div>
</body>
</html>

