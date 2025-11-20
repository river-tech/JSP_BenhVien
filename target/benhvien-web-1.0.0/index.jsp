<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ - Hệ thống tiêm phòng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
</head>
<body>
    <header class="site-header">
        <h1>Hệ thống tiêm phòng Bệnh viện</h1>
    </header>
    <main class="container">
        <div class="card">
            <h2>Chào mừng đến với Hệ thống tiêm phòng</h2>
            <div style="display: flex; gap: 2rem; margin-top: 2rem;">
                <div style="flex: 1; padding: 1.5rem; border: 2px solid var(--primary); border-radius: 8px;">
                    <h3>Đăng nhập Admin</h3>
                    <p>Quản lý vaccine, đăng ký tiêm phòng, xem thống kê</p>
                    <a href="${pageContext.request.contextPath}/login?type=admin" class="btn">Đăng nhập Admin</a>
                </div>
                <div style="flex: 1; padding: 1.5rem; border: 2px solid var(--primary); border-radius: 8px;">
                    <h3>Đăng ký Khách hàng</h3>
                    <p>Khách hàng đến trung tâm đăng ký thông tin để theo dõi lịch sử tiêm phòng</p>
                    <a href="${pageContext.request.contextPath}/register-customer" class="btn">Đăng ký thông tin</a>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
