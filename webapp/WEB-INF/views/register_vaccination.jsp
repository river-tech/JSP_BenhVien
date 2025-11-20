<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout.jsp">
    <jsp:param name="section" value="header"/>
    <jsp:param name="title" value="Đăng ký Tiêm phòng"/>
</jsp:include>
<div class="card">
    <h2>Đăng ký Tiêm phòng cho Khách hàng</h2>
    <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/admin/register-vaccination">
        <div class="form-group">
            <label for="maKH">Khách hàng *</label>
            <select id="maKH" name="maKH" required>
                <option value="">-- Chọn khách hàng --</option>
                <c:forEach var="kh" items="${customers}">
                    <option value="${kh.maKH}">${kh.maKH} - ${kh.hoTenKH}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="maBenh">Bệnh cần tiêm phòng *</label>
            <select id="maBenh" name="maBenh" required onchange="loadVaccinesByDisease(this.value)">
                <option value="">-- Chọn bệnh trước --</option>
                <c:forEach var="b" items="${diseases}">
                    <option value="${b.maBenh}">${b.maBenh} - ${b.tenBenh}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="maVacxin">Vaccine *</label>
            <select id="maVacxin" name="maVacxin" required disabled>
                <option value="">-- Vui lòng chọn bệnh trước --</option>
            </select>
            <small id="vaccineHelp" style="color: #666; display: none;">Đang tải danh sách vaccine...</small>
        </div>
        <div class="form-group">
            <label for="sttMui">Số thứ tự mũi tiêm *</label>
            <input type="number" id="sttMui" name="sttMui" min="1" value="1" required>
        </div>
        <div class="form-group">
            <label for="ngayTiemPhong">Ngày tiêm phòng *</label>
            <input type="date" id="ngayTiemPhong" name="ngayTiemPhong" required>
        </div>
        <div class="form-group">
            <label for="ngayHenTiepTheo">Ngày hẹn tiếp theo</label>
            <input type="date" id="ngayHenTiepTheo" name="ngayHenTiepTheo">
            <small style="color: #666;">(Để trống nếu đã tiêm đủ mũi hoặc sẽ tự động tính)</small>
        </div>
        <button type="submit">Đăng ký tiêm phòng</button>
        <a class="btn" href="${pageContext.request.contextPath}/history">Xem lịch sử</a>
    </form>
    <script>
        // Set default value cho ngày tiêm phòng = hôm nay
        document.addEventListener('DOMContentLoaded', function() {
            const today = new Date().toISOString().split('T')[0];
            const ngayTiemInput = document.getElementById('ngayTiemPhong');
            if (!ngayTiemInput.value) {
                ngayTiemInput.value = today;
            }
        });
        
        // Load vaccine theo bệnh đã chọn
        function loadVaccinesByDisease(maBenh) {
            const vaccineSelect = document.getElementById('maVacxin');
            const vaccineHelp = document.getElementById('vaccineHelp');
            
            if (!maBenh) {
                vaccineSelect.innerHTML = '<option value="">-- Vui lòng chọn bệnh trước --</option>';
                vaccineSelect.disabled = true;
                return;
            }
            
            vaccineSelect.disabled = true;
            vaccineHelp.style.display = 'block';
            vaccineHelp.textContent = 'Đang tải danh sách vaccine...';
            
            // Gọi AJAX để lấy vaccine theo bệnh
            fetch('${pageContext.request.contextPath}/admin/get-vaccines-by-disease?maBenh=' + maBenh)
                .then(response => response.json())
                .then(data => {
                    vaccineSelect.innerHTML = '<option value="">-- Chọn vaccine --</option>';
                    if (data && data.length > 0) {
                        data.forEach(vc => {
                            const option = document.createElement('option');
                            option.value = vc.maVacxin;
                            option.textContent = vc.tenVacxin + ' (' + vc.tenHangSX + ') - ' + vc.soMui + ' mũi - ' + vc.giaVacxin + 'đ';
                            option.setAttribute('data-somui', vc.soMui);
                            vaccineSelect.appendChild(option);
                        });
                        vaccineSelect.disabled = false;
                        vaccineHelp.style.display = 'none';
                    } else {
                        vaccineSelect.innerHTML = '<option value="">-- Không có vaccine nào cho bệnh này --</option>';
                        vaccineHelp.textContent = 'Không có vaccine nào có thể phòng bệnh này. Vui lòng tạo vaccine mới.';
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    vaccineSelect.innerHTML = '<option value="">-- Lỗi khi tải vaccine --</option>';
                    vaccineHelp.textContent = 'Lỗi khi tải danh sách vaccine. Vui lòng thử lại.';
                });
        }
        
        // Tự động tính ngày hẹn tiếp theo khi chọn vaccine và ngày tiêm
        document.addEventListener('DOMContentLoaded', function() {
            const vaccineSelect = document.getElementById('maVacxin');
            const sttMuiInput = document.getElementById('sttMui');
            const ngayTiemInput = document.getElementById('ngayTiemPhong');
            const ngayHenInput = document.getElementById('ngayHenTiepTheo');
            
            function calculateNextDate() {
                const ngayTiem = ngayTiemInput.value;
                const sttMui = parseInt(sttMuiInput.value) || 1;
                
                if (ngayTiem && vaccineSelect.value) {
                    const selectedOption = vaccineSelect.options[vaccineSelect.selectedIndex];
                    const soMui = parseInt(selectedOption.getAttribute('data-somui')) || 0;
                    if (sttMui < soMui) {
                        // Tính ngày hẹn = ngày tiêm + 30 ngày (mặc định)
                        const ngayTiemDate = new Date(ngayTiem);
                        ngayTiemDate.setDate(ngayTiemDate.getDate() + 30);
                        ngayHenInput.value = ngayTiemDate.toISOString().split('T')[0];
                    } else {
                        ngayHenInput.value = '';
                    }
                }
            }
            
            ngayTiemInput.addEventListener('change', calculateNextDate);
            vaccineSelect.addEventListener('change', calculateNextDate);
            sttMuiInput.addEventListener('change', calculateNextDate);
        });
    </script>
</div>
<jsp:include page="layout.jsp">
    <jsp:param name="section" value="footer"/>
</jsp:include>

