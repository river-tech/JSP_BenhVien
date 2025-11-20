-- Bảng ADMIN cho quản trị viên
CREATE TABLE IF NOT EXISTS ADMIN (
    Username VARCHAR(50) PRIMARY KEY,
    Password VARCHAR(255) NOT NULL,
    HoTen VARCHAR(100),
    Email VARCHAR(100)
);

-- Thêm cột username và password vào bảng KHACHHANG
ALTER TABLE KHACHHANG ADD COLUMN IF NOT EXISTS Username VARCHAR(50) UNIQUE;
ALTER TABLE KHACHHANG ADD COLUMN IF NOT EXISTS Password VARCHAR(255);

-- Tạo index cho username
CREATE INDEX IF NOT EXISTS idx_khachhang_username ON KHACHHANG(Username);

-- Dữ liệu mẫu cho admin
-- Username: admin, Password: admin
INSERT INTO ADMIN (Username, Password, HoTen, Email) VALUES
('admin', 'admin', 'Quản trị viên', 'admin@benhvien.vn')
ON CONFLICT (Username) DO UPDATE SET Password = 'admin';

-- Cập nhật password cho khách hàng (mã hóa đơn giản - trong thực tế nên dùng BCrypt)
UPDATE KHACHHANG SET Username = 'kh001', Password = '123456' WHERE MaKH = 'KH001';
UPDATE KHACHHANG SET Username = 'kh002', Password = '123456' WHERE MaKH = 'KH002';
UPDATE KHACHHANG SET Username = 'kh003', Password = '123456' WHERE MaKH = 'KH003';
UPDATE KHACHHANG SET Username = 'kh004', Password = '123456' WHERE MaKH = 'KH004';
UPDATE KHACHHANG SET Username = 'kh005', Password = '123456' WHERE MaKH = 'KH005';

