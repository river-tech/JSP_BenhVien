<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout.jsp">
    <jsp:param name="section" value="header"/>
    <jsp:param name="title" value="Quản lý Vaccine"/>
</jsp:include>
<div class="card">
    <h2>Tìm kiếm vaccine</h2>
    <form method="get" action="${pageContext.request.contextPath}/vaccines/search" class="form-grid">
        <div class="form-group">
            <label for="maVacxin">Mã vaccine</label>
            <input type="text" id="maVacxin" name="maVacxin" value="${param.maVacxin}">
        </div>
        <div class="form-group">
            <label for="tenVacxin">Tên vaccine</label>
            <input type="text" id="tenVacxin" name="tenVacxin" value="${param.tenVacxin}">
        </div>
        <div class="form-group">
            <label for="tenHangSX">Hãng sản xuất</label>
            <input type="text" id="tenHangSX" name="tenHangSX" value="${param.tenHangSX}">
        </div>
        <button type="submit">Tìm kiếm</button>
        <a class="btn" href="${pageContext.request.contextPath}/vaccines/create">+ Thêm mới</a>
    </form>
    <c:if test="${param.success eq 'true'}">
        <div class="alert alert-success">Thêm vaccine thành công.</div>
    </c:if>
    <c:if test="${param.updated eq 'true'}">
        <div class="alert alert-success">Cập nhật vaccine thành công.</div>
    </c:if>
    <c:if test="${param.deleted eq 'true'}">
        <div class="alert alert-success">Đã xóa vaccine.</div>
    </c:if>
</div>
<jsp:include page="list_vaccine.jsp"/>
<jsp:include page="layout.jsp">
    <jsp:param name="section" value="footer"/>
</jsp:include>
