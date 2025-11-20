<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout.jsp">
    <jsp:param name="section" value="header"/>
    <jsp:param name="title" value="Cập nhật Vaccine"/>
</jsp:include>
<div class="card">
    <h2>Cập nhật vaccine</h2>
    <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/vaccines/edit">
        <input type="hidden" name="maVacxin" value="${vaccine.maVacxin}">
        <div class="form-group">
            <label>Mã vaccine</label>
            <input type="text" value="${vaccine.maVacxin}" disabled>
        </div>
        <div class="form-group">
            <label for="tenVacxin">Tên vaccine</label>
            <input type="text" id="tenVacxin" name="tenVacxin" value="${vaccine.tenVacxin}" required>
        </div>
        <div class="form-group">
            <label for="soMui">Số mũi</label>
            <input type="number" id="soMui" name="soMui" value="${vaccine.soMui}" min="1" required>
        </div>
        <div class="form-group">
            <label for="giaVacxin">Giá vaccine</label>
            <input type="number" id="giaVacxin" name="giaVacxin" value="${vaccine.giaVacxin}" min="0" required>
        </div>
        <div class="form-group">
            <label for="tenHangSX">Hãng sản xuất</label>
            <input type="text" id="tenHangSX" name="tenHangSX" value="${vaccine.tenHangSX}" required>
        </div>
        <div class="form-group">
            <label for="moTa">Mô tả</label>
            <textarea id="moTa" name="moTa" rows="3">${vaccine.moTa}</textarea>
        </div>
        <button type="submit">Cập nhật</button>
        <a class="btn" href="${pageContext.request.contextPath}/vaccines/search">Quay lại</a>
    </form>
</div>
<jsp:include page="layout.jsp">
    <jsp:param name="section" value="footer"/>
</jsp:include>
