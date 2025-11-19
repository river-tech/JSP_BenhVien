<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout.jsp">
    <jsp:param name="section" value="header"/>
    <jsp:param name="title" value="Lịch sử tiêm phòng"/>
</jsp:include>
<div class="card">
    <h2>Lịch sử tiêm phòng khách hàng</h2>
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
        </tbody>
    </table>
</div>
<jsp:include page="layout.jsp">
    <jsp:param name="section" value="footer"/>
</jsp:include>
