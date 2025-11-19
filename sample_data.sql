BEGIN;

TRUNCATE TABLE LICHSUTIEMPHONG CASCADE;
TRUNCATE TABLE PHONGBENH CASCADE;
TRUNCATE TABLE VACXIN CASCADE;
TRUNCATE TABLE BENH CASCADE;
TRUNCATE TABLE KHACHHANG CASCADE;

INSERT INTO KHACHHANG (MaKH, HoTenKH, SoDienThoai, DiaChiKH, NgaySinh, GioiTinh) VALUES
('KH001','Nguyen Van An','0901112233','Ha Noi','1990-01-15','Nam'),
('KH002','Tran Thi Binh','0902223344','Hai Phong','1988-05-20','Nu'),
('KH003','Le Hoang','0911223344','Da Nang','1995-10-09','Nam'),
('KH004','Pham Minh Chau','0933445566','Ho Chi Minh','1992-12-30','Nu'),
('KH005','Do Kim Dung','0977889900','Can Tho','1985-07-07','Nu');

INSERT INTO BENH (MaBenh, TenBenh, MoTa) VALUES
('B001','Sởi','Bệnh truyền nhiễm do virus sởi'),
('B002','Cúm mùa','Virus cúm mùa'),
('B003','Thủy đậu','Varicella'),
('B004','Bạch hầu','Bорut bacteria'),
('B005','Covid-19','SARS-CoV-2');

INSERT INTO VACXIN (MaVacxin, TenVacxin, SoMui, MoTa, GiaVacxin, TenHangSX) VALUES
('VX001','MMR II',2,'Sởi - Quai bị - Rubella',450000,'MSD'),
('VX002','Imovax',3,'Bại liệt',380000,'Sanofi'),
('VX003','Influvac',1,'Cúm mùa',320000,'Abbott'),
('VX004','Varivax',2,'Thủy đậu',500000,'MSD'),
('VX005','Pentaxim',4,'5 trong 1',650000,'Sanofi'),
('VX006','Synflorix',3,'Phế cầu',700000,'GSK'),
('VX007','Gardasil',3,'HPV',1200000,'MSD'),
('VX008','ComBE Five',5,'Bạch hầu - Ho gà - Uốn ván',160000,'Serum Institute'),
('VX009','Abdala',3,'Covid-19',350000,'Cuba Center'),
('VX010','Pfizer mRNA',2,'Covid-19',450000,'Pfizer');

INSERT INTO PHONGBENH (MaVacxin, MaBenh, GhiChu) VALUES
('VX001','B001','Phòng sởi'),
('VX002','B002','Phòng bại liệt liên quan cúm'),
('VX003','B002','Phòng cúm'),
('VX004','B003','Phòng thủy đậu'),
('VX005','B004','Phòng bạch hầu'),
('VX006','B002','Phòng phế cầu'),
('VX007','B001','Giảm nguy cơ ung thư cổ tử cung'),
('VX008','B004','Phòng bạch hầu ho gà'),
('VX009','B005','Phòng covid'),
('VX010','B005','Phòng covid mRNA');

INSERT INTO LICHSUTIEMPHONG (MaKH, MaVacxin, STTMui, NgayTiemPhong, NgayHenTiepTheo) VALUES
('KH001','VX001',1,'2024-01-05','2024-02-05'),
('KH001','VX001',2,'2024-02-10',NULL),
('KH002','VX003',1,'2024-03-12',NULL),
('KH002','VX008',1,'2024-02-15','2024-03-15'),
('KH003','VX004',1,'2024-04-20','2024-05-20'),
('KH003','VX004',2,'2024-05-25',NULL),
('KH004','VX007',1,'2024-01-18','2024-03-18'),
('KH004','VX007',2,'2024-03-25','2024-05-25'),
('KH005','VX009',1,'2024-06-12','2024-07-12'),
('KH005','VX010',1,'2024-08-22','2024-09-22');

COMMIT;
