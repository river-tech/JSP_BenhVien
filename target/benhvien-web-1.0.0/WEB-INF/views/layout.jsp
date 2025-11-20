<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${param.section eq 'header'}">
        <!DOCTYPE html>
        <html lang="vi">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title><c:out value="${param.title != null ? param.title : 'Quản lý Vaccine'}"/></title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
        </head>
        <body>
        <header class="site-header">
            <h1>Hệ thống tiêm phòng Bệnh viện</h1>
            <nav>
                <c:choose>
                    <c:when test="${sessionScope.userType eq 'admin'}">
                        <a href="${pageContext.request.contextPath}/vaccines/search">Vaccine</a>
                        <a href="${pageContext.request.contextPath}/vaccines/create">Thêm vaccine</a>
                        <a href="${pageContext.request.contextPath}/admin/register-vaccination">Đăng ký tiêm</a>
                        <a href="${pageContext.request.contextPath}/history">Lịch sử tiêm</a>
                        <a href="${pageContext.request.contextPath}/statistics">Thống kê</a>
                        <span style="color: #e0f2fe; margin-left: 1rem;">
                            Admin: ${sessionScope.admin.username} | 
                            <a href="${pageContext.request.contextPath}/logout" style="color: var(--accent);">Đăng xuất</a>
                        </span>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/">Trang chủ</a>
                        <a href="${pageContext.request.contextPath}/register-customer">Đăng ký KH</a>
                        <a href="${pageContext.request.contextPath}/login?type=admin">Đăng nhập Admin</a>
                    </c:otherwise>
                </c:choose>
            </nav>
        </header>
        <main class="container">
    </c:when>
    <c:when test="${param.section eq 'footer'}">
        </main>
        <footer class="site-footer">
            <p>&copy; <c:out value="${pageContext.request.serverName}"/> - Ứng dụng JSP/Servlet</p>
        </footer>
        </body>
        </html>
    </c:when>
</c:choose>
