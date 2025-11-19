<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <h3>Danh sách vaccine</h3>
    <c:if test="${empty vaccines}">
        <p>Chưa có dữ liệu.</p>
    </c:if>
    <c:if test="${not empty vaccines}">
        <table>
            <thead>
            <tr>
                <th>Mã</th>
                <th>Tên</th>
                <th>Số mũi</th>
                <th>Giá</th>
                <th>Hãng sản xuất</th>
                <th>Mô tả</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="vc" items="${vaccines}">
                <tr>
                    <td>${vc.maVacxin}</td>
                    <td>${vc.tenVacxin}</td>
                    <td>${vc.soMui}</td>
                    <td>${vc.giaVacxin}</td>
                    <td>${vc.tenHangSX}</td>
                    <td>${vc.moTa}</td>
                    <td>
                        <a class="btn" href="${pageContext.request.contextPath}/vaccines/edit?maVacxin=${vc.maVacxin}">Sửa</a>
                        <form method="post" action="${pageContext.request.contextPath}/vaccines/delete" style="display:inline" onsubmit="return confirm('Xóa vaccine?');">
                            <input type="hidden" name="maVacxin" value="${vc.maVacxin}">
                            <button type="submit">Xóa</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
