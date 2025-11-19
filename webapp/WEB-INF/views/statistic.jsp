<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout.jsp">
    <jsp:param name="section" value="header"/>
    <jsp:param name="title" value="Thống kê chi phí"/>
</jsp:include>
<div class="card">
    <h2>Thống kê tổng tiền khách hàng đã trả</h2>
    <table>
        <thead>
        <tr>
            <th>Mã KH</th>
            <th>Tên KH</th>
            <th>Địa chỉ</th>
            <th>Tổng tiền</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="stat" items="${statistics}">
            <tr>
                <td>${stat.maKH}</td>
                <td>${stat.tenKH}</td>
                <td>${stat.diaChi}</td>
                <td><c:out value="${stat.tongTien}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="layout.jsp">
    <jsp:param name="section" value="footer"/>
</jsp:include>
