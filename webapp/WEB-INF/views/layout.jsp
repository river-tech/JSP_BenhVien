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
                <a href="${pageContext.request.contextPath}/vaccines/search">Vaccine</a>
                <a href="${pageContext.request.contextPath}/vaccines/create">Thêm vaccine</a>
                <a href="${pageContext.request.contextPath}/history">Lịch sử tiêm</a>
                <a href="${pageContext.request.contextPath}/statistics">Thống kê</a>
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
