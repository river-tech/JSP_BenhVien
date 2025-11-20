-- Cập nhật schema: Thêm thời gian chờ giữa các mũi tiêm
-- Thêm cột ThoiGianCho (số ngày) vào bảng VACXIN
ALTER TABLE VACXIN ADD COLUMN IF NOT EXISTS ThoiGianCho INT DEFAULT 30;

-- Cập nhật thời gian chờ cho các vaccine mẫu (đơn vị: ngày)
UPDATE VACXIN SET ThoiGianCho = 30 WHERE MaVacxin = 'VX001'; -- MMR II: 30 ngày
UPDATE VACXIN SET ThoiGianCho = 28 WHERE MaVacxin = 'VX002'; -- Imovax: 28 ngày
UPDATE VACXIN SET ThoiGianCho = 0 WHERE MaVacxin = 'VX003';  -- Influvac: 1 mũi
UPDATE VACXIN SET ThoiGianCho = 30 WHERE MaVacxin = 'VX004'; -- Varivax: 30 ngày
UPDATE VACXIN SET ThoiGianCho = 60 WHERE MaVacxin = 'VX005'; -- Pentaxim: 60 ngày
UPDATE VACXIN SET ThoiGianCho = 28 WHERE MaVacxin = 'VX006'; -- Synflorix: 28 ngày
UPDATE VACXIN SET ThoiGianCho = 60 WHERE MaVacxin = 'VX007'; -- Gardasil: 60 ngày
UPDATE VACXIN SET ThoiGianCho = 30 WHERE MaVacxin = 'VX008'; -- ComBE Five: 30 ngày
UPDATE VACXIN SET ThoiGianCho = 30 WHERE MaVacxin = 'VX009'; -- Abdala: 30 ngày
UPDATE VACXIN SET ThoiGianCho = 21 WHERE MaVacxin = 'VX010'; -- Pfizer: 21 ngày

-- Xóa cột Username và Password khỏi KHACHHANG (không cần đăng nhập)
-- ALTER TABLE KHACHHANG DROP COLUMN IF EXISTS Username;
-- ALTER TABLE KHACHHANG DROP COLUMN IF EXISTS Password;

