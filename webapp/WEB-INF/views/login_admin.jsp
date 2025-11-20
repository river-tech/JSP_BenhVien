<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
    <style>
        .login-container {
            max-width: 400px;
            margin: 100px auto;
            padding: 2rem;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .login-container h2 {
            text-align: center;
            color: var(--primary);
            margin-bottom: 1.5rem;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Đăng nhập Admin</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/login">
            <input type="hidden" name="type" value="admin">
            <div class="form-group">
                <label for="username">Tên đăng nhập</label>
                <input type="text" id="username" name="username" placeholder="admin" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" placeholder="admin" required>
            </div>
            <button type="submit" style="width: 100%;">Đăng nhập</button>
        </form>
        <p style="text-align: center; margin-top: 1rem; font-size: 0.9em; color: #666;">
            <small>Username: <strong>admin</strong> | Password: <strong>admin</strong></small>
        </p>
        <p style="text-align: center; margin-top: 1rem;">
            <a href="${pageContext.request.contextPath}/">Về trang chủ</a>
        </p>
    </div>
</body>
</html>

