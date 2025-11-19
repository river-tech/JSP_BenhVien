<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout.jsp">
    <jsp:param name="section" value="header"/>
    <jsp:param name="title" value="Thêm Vaccine"/>
</jsp:include>
<div class="card">
    <h2>Thêm mới vaccine</h2>
    <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/vaccines/create">
        <div class="form-group">
            <label for="maVacxin">Mã vaccine</label>
            <input type="text" id="maVacxin" name="maVacxin" required>
        </div>
        <div class="form-group">
            <label for="tenVacxin">Tên vaccine</label>
            <input type="text" id="tenVacxin" name="tenVacxin" required>
        </div>
        <div class="form-group">
            <label for="soMui">Số mũi</label>
            <input type="number" id="soMui" name="soMui" min="1" required>
        </div>
        <div class="form-group">
            <label for="giaVacxin">Giá vaccine</label>
            <input type="number" id="giaVacxin" name="giaVacxin" min="0" required>
        </div>
        <div class="form-group">
            <label for="tenHangSX">Hãng sản xuất</label>
            <input type="text" id="tenHangSX" name="tenHangSX" required>
        </div>
        <div class="form-group">
            <label for="moTa">Mô tả</label>
            <textarea id="moTa" name="moTa" rows="3"></textarea>
        </div>
        <button type="submit">Lưu vaccine</button>
        <a class="btn" href="${pageContext.request.contextPath}/vaccines/search">Hủy</a>
    </form>
</div>
<jsp:include page="layout.jsp">
    <jsp:param name="section" value="footer"/>
</jsp:include>
