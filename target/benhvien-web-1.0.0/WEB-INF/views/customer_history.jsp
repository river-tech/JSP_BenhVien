<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout.jsp">
    <jsp:param name="section" value="header"/>
    <jsp:param name="title" value="Lịch sử tiêm phòng của tôi"/>
</jsp:include>
<div class="card">
    <h2>Lịch sử tiêm phòng của bạn</h2>
    <p>Xin chào, <strong>${customer.hoTenKH}</strong> (${customer.maKH})</p>
    <table>
        <thead>
        <tr>
            <th>Mã KH</th>
            <th>Tên KH</th>
            <th>Tên bệnh</th>
            <th>Mã vaccine</th>
            <th>Tên vaccine</th>
            <th>Tổng số mũi cần tiêm</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${history}">
            <tr>
                <td>${item.maKH}</td>
                <td>${item.tenKH}</td>
                <td>${item.tenBenh}</td>
                <td>${item.maVacxin}</td>
                <td>${item.tenVacxin}</td>
                <td>${item.tongSoMui}</td>
            </tr>
        </c:forEach>
        <c:if test="${empty history}">
            <tr>
                <td colspan="6" style="text-align: center;">Chưa có lịch sử tiêm phòng</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
<jsp:include page="layout.jsp">
    <jsp:param name="section" value="footer"/>
</jsp:include>

