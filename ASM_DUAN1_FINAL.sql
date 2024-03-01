create database shopgiay_Dung

go
USE [shopgiay_Dung]
GO


if OBJECT_ID ('AUTO_MaHD') is not null
	drop function AUTO_MaHD
go
CREATE FUNCTION AUTO_MaHD()
RETURNS VARCHAR(7)
AS
BEGIN
	DECLARE @ID VARCHAR(7)
	IF (SELECT COUNT(MAHDBan) FROM HoaDon) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MaHDBan, 5)) FROM HoaDon
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'HD0000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 and @ID <99 THEN 'HD000'+ CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 99 and @ID <999 THEN 'HD00'+ CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 999 and @ID <9999 THEN 'HD0'+ CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9999 THEN 'HD'+ CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END


Create Table HoaDon(
	MaHDBan varchar(10) PRIMARY KEY CONSTRAINT MaHDBan DEFAULT [dbo].[AUTO_MaHD](),
	MaNV varchar(10) not null,
	NgayBan date,
	MaKH varchar(10)
);

/* câu lệnh tạo bảng hóa đơn chi tiet*/
Create Table HoaDonChiTiet(
	MaHDCT int Identity(1,10),
	MaHDBan varchar(10) not null,
	MaSP varchar(10) not null,
	SoLuong int,
	GiamGia float,
	primary key  (maHDCT asc) 
);

CREATE FUNCTION AUTO_MaSP()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MASP) FROM SanPham) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MASP, 3)) FROM SanPham
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'SP00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 and @ID <99 THEN 'SP0'+ CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 99 THEN 'SP'+ CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
---

Create Table sanpham(
	MASP varchar(10)  PRIMARY KEY  not null,
	TENSP NVARCHAR(50),
	GIA FLOAT,
	SOLUONG INT,
	MOTA NVARCHAR(250),
	HINH NVARCHAR(50) Null,
	HINH1 NVARCHAR(50) Null,
	HINH2 NVARCHAR(50) Null,
	HINH3 NVARCHAR(50) Null,
	MaDM int,
	Size nvarchar(50)
);


CREATE TABLE khachhang
(
	[MaKH] [char](5) NOT NULL,
	[Hoten] [varchar](50) NOT NULL,
	[Gioitinh] [bit] NOT NULL,
	[Diachi] [varchar](50) NOT NULL,
	[SDT] [varchar](10) NOT NULL,
	[email] [nvarchar](50) NULL,
	[hinh] [nvarchar](50) NULL,
	
)

Create table DanhMucSP(
	MaDM int not null Identity (1,1),
	TenDanhMuc nvarchar(50) not null,
	primary key  (MaDM asc) 
);

CREATE TABLE nguoidung(
	[MaND] [char](5) NOT NULL,
	[Hoten] [varchar](50) NOT NULL,
	[Matkhau] [varchar](50) NOT NULL,
	[Gioitinh] [bit] NOT NULL,
	[diachi] [varchar](50) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[SDT] [varchar](10) NOT NULL,
	[chucvu] [bit] NOT NULL,
)

insert into khachhang
values ('KH02','Nguyen Van A',0,'An Khe, Gia lai','054213154','Nam@fpt.edu.vn','1.png'),
		('KH03','Bui Anh Khoa',0,'An Khe, Gia lai','054213154','Nam@fpt.edu.vn','1.png'),
		('KH04','Truong Toan Thang',0,'An Khe, Gia lai','054213154','Nam@fpt.edu.vn','1.png'),
		('KH05','Nguyen Truong Vinh',0,'An Khe, Gia lai','054213154','Nam@fpt.edu.vn','1.png'),
		('KH06','Doan Nhat Tan',0,'An Khe, Gia lai','054213154','Nam@fpt.edu.vn','1.png');
go


go
insert into nguoidung
values('ND1','Bui Anh Khoa','123',1,'TP HCM','Khoa@fpt.edu.vn','012345678',1),
		('ND2','Nguyen Hoai Nam','123',1,'TP HCM','Nam@fpt.edu.vn','012345678',1),
		('ND3','Truong Toan Thang','123',1,'TP HCM','Thang@fpt.edu.vn','012345678',0),
		('ND4','Nguyen Truong Vinh','123',1,'TP HCM','Vinh@fpt.edu.vn','012345678',0),
		('ND5','Doan Nhat Tan','123',1,'TP HCM','Tan@fpt.edu.vn','012345678',0)
go
go

insert into SanPham values ([dbo].[AUTO_MaSP](),N'NIKE JORDAN 1',20000000,900,N'FULL SIZE', '1.png', '1a.jpg', '1b.jpg', '1C.jpg',1,N'36-42');
insert into SanPham values ([dbo].[AUTO_MaSP](),N'NIKE AIR MAX 90', 12000000,1000, N'FULL SIZE', '2.jpg', '2a.jpg', '2b.jpg', '2c.jpg',1,N'36-42');
insert into SanPham values ([dbo].[AUTO_MaSP](),N'NIKE AIR FORCE 1', 9000000,1000, N'FULL SIZE', '3.jpg', '3a.jpg', '3b.jpg', '3c.jpg',1,N'36-42');
insert into SanPham values ([dbo].[AUTO_MaSP](),N'NIKE SF AF1', 4000000,1000, N'FULL SIZE', '4.jpg', '4a.jpg', '4b.jpg', '4c.jpg',1,N'36-42');
insert into SanPham values ([dbo].[AUTO_MaSP](),N'NIKE UPTEMPO', 5000000,1000, N'FULL SIZE', '5.jpg', '5a.jpg', '5b.jpg', '5c.jpg',1,N'36-42');
insert into SanPham values ([dbo].[AUTO_MaSP](),N'NIKE CORTEZ', 3500000,1000, N'FULL SIZE', '6.jpg', '6a.jpg', '6b.jpg', '6c.jpg',1,N'36-42');
insert into SanPham values ([dbo].[AUTO_MaSP](),N'NIKE VAPORMAX ACE', 4500000,1000, N'FULL SIZE', '7.jpg', '7a.jpg', '7b.jpg', '7c.jpg',1,N'36-42');
insert into SanPham values ([dbo].[AUTO_MaSP](),N'ADIDAS YEEZY', 1450000,1000, N'FULL SIZE', '8.jpg', '8a.jpg', '8b.jpg', '8c.jpg',2,N'36-42');
insert into SanPham values ([dbo].[AUTO_MaSP](),N'ADIDAS ULTRA BOOST', 1450000,1000, N'FULL SIZE',  '9.jpg', '9a.jpg', '9b.jpg', '9c.jpg',2,N'36-42');
insert into SanPham values ([dbo].[AUTO_MaSP](),N'ADIDAS YEEZY 300', 1450000,1000, N'FULL SIZE',  '10.jpg', '10a.jpg', '10b.jpg', '10c.jpg',2,N'36-42');
insert into SanPham values ([dbo].[AUTO_MaSP](),N'ADIDAS YEEZY 700', 1450000,1000, N'FULL SIZE',  '11.jpg', '11a.jpg', '11b.jpg', '11c.jpg',2,N'36-42');
insert into SanPham values ([dbo].[AUTO_MaSP](),N'ADIDAS NMD', 1450000,1000, N'FULL SIZE',  '12.jpg', '12a.jpg', '12b.jpg', '12c.jpg',2,N'36-42');

/*Insert dữ liệu vào bảng hóa đơn */
insert into HoaDon values ([dbo].[AUTO_MaHD](),'NV002','20210113','KH001');
insert into HoaDon values ([dbo].[AUTO_MaHD](),'NV002','20210113','KH003');
insert into HoaDon values ([dbo].[AUTO_MaHD](),'NV002','20210116','KH009');
insert into HoaDon values ([dbo].[AUTO_MaHD](),'NV001','20210113','KH004');
insert into HoaDon values ([dbo].[AUTO_MaHD](),'NV001','20211013','KH005');
insert into HoaDon values ([dbo].[AUTO_MaHD](),'NV004','20211013','KH009');
insert into HoaDon values ([dbo].[AUTO_MaHD](),'NV004','20211113','KH007');
insert into HoaDon values ([dbo].[AUTO_MaHD](),'NV005','20211117','KH009');
insert into HoaDon values ([dbo].[AUTO_MaHD](),'NV003','20211119','KH008');
insert into HoaDon values ([dbo].[AUTO_MaHD](),'NV003','20211020','KH009');


/*Insert dữ liệu vào bảng hóa đơn chi tiết */
insert into HoaDonChiTiet values ('HD00001','SP001',2,0);
insert into HoaDonChiTiet values ('HD00002','SP001',1,0);
insert into HoaDonChiTiet values ('HD00003','SP002',1,0);;
insert into HoaDonChiTiet values ('HD00004','SP010',1,0)
insert into HoaDonChiTiet values ('HD00005','SP010',2,0);
insert into HoaDonChiTiet values ('HD00006','SP005',1,0);
insert into HoaDonChiTiet values ('HD00007','SP003',3,0);
insert into HoaDonChiTiet values ('HD00008','SP006',1,0);
insert into HoaDonChiTiet values ('HD00009','SP009',1,0);
insert into HoaDonChiTiet values ('HD00010','SP007',2,0);
insert into HoaDonChiTiet values ('HD00001','SP005',2,0);

insert into DanhMucSP values (N'Nike');
insert into DanhMucSP values (N'ADIDAS');


/*proc gọi ra hóa đơn chi tiết theo mã hóa đơn bán*/
if OBJECT_ID ('sp_HDCT_MaHD') is not null
	drop proc sp_HDCT_MaHD
go
CREATE  PROCEDURE [dbo].[sp_HDCT_MaHD] 
 @MaHDBan varchar(10)
 AS
	BEGIN
		select ct.MaHDCT,hd.MaHDBan, sp.TENSP, ct.SoLuong, sp.GIA, ct.GiamGia, ((sp.Gia*ct.SoLuong)*(100-ct.GiamGia)/100) as ThanhTien from HoaDonChiTiet ct 
		inner join HoaDon hd on ct.MaHDBan = hd.MaHDBan
		inner join SanPham sp on sp.MaSP = ct.MaSP
		where ct.MaHDBan = @MaHDBan
		GROUP BY ct.MaHDCT,hd.MaHDBan, sp.TenSP,ct.SoLuong,sp.GIA, ct.GiamGia
	END
GO
exec [dbo].[sp_HDCT_MaHD] 'HD00001'
/*proc gọi ra tất cả hóa đơn */
if OBJECT_ID ('sp_HoaDon') is not null
	drop proc sp_HoaDon
go
CREATE  PROCEDURE [dbo].[sp_HoaDon] 
 AS
	BEGIN
		select hd.MaHDBan, MaNV, NgayBan, MaKH, SUM(((sp.GIA*ct.SoLuong)*(100-ct.GiamGia)/100)) as TongTien from HoaDon hd 
		inner join HoaDonChiTiet ct 
		on ct.MaHDBan = hd.MaHDBan
		inner join SanPham sp 
		on sp.MaSP = ct.MaSP
		GROUP BY hd.MaHDBan,MaNV, NgayBan, MaKH
		ORDER BY NgayBan desc
	END
GO
exec sp_HoaDon

---------------------------------------------------------------------------------------
/*proc gọi ra tất cả hóa đơn theo ngày */
if OBJECT_ID ('sp_HoaDonTheoNgay') is not null
	drop proc sp_HoaDonTheoNgay
go
CREATE  PROCEDURE [dbo].[sp_HoaDonTheoNgay]
@Ngay date
 AS
	BEGIN
		select hd.MaHDBan, MaNV, NgayBan, MaKH, SUM(((sp.GIA*ct.SoLuong)*(100-ct.GiamGia)/100)) as TongTien from HoaDon hd 
		inner join HoaDonChiTiet ct 
		on ct.MaHDBan = hd.MaHDBan
		inner join SanPham sp 
		on sp.MaSP = ct.MaSP
		where hd.NgayBan = @Ngay
		GROUP BY hd.MaHDBan,MaNV, NgayBan, MaKH
		ORDER BY NgayBan desc
	END
GO

exec sp_HoaDonTheoNgay '20211020'


/* cập nhật số lượng sản phẩm trong kho sau khi thêm sản phẩm hoặc cập nhật hóa đơn chi tiết*/
if OBJECT_ID ('trg_ThemHDCT') is not null
	drop trigger trg_ThemHDCT
go

CREATE TRIGGER trg_ThemHDCT ON HoaDonChiTiet AFTER INSERT AS 
BEGIN
	UPDATE SanPham 
	SET SoLuong = sp.SoLuong -(
		SELECT SoLuong
		FROM inserted
		WHERE MaSP = sp.MaSP
	)
	FROM SanPham sp
	JOIN inserted ON sp.MaSP = inserted.MaSP
END
GO
-------------------------------------------------------------------------------------------------
/* cập nhật sản phẩm trong kho sau khi cập nhật hóa đơn chi tiết */
if OBJECT_ID ('trg_SuaHDCT') is not null
	drop trigger trg_SuaHDCT
go

CREATE TRIGGER trg_SuaHDCT on HoaDonChiTiet after update AS
BEGIN
   UPDATE SanPham SET SoLuong = sp.SoLuong -
	   (SELECT SoLuong FROM inserted WHERE MaSP = sp.MaSP) +
	   (SELECT soluong FROM deleted WHERE MaSP = sp.MaSP)
   FROM SanPham sp 
   JOIN deleted ON sp.MaSP = deleted.MaSP
end
GO
-------------------------------------------------------------------------------------------------
/* cập nhật hàng trong kho sản phẩm sau khi xóa hóa đơn chi tiết */
if OBJECT_ID ('trg_XoaHDCT') is not null
	drop trigger trg_XoaHDCT
go

create TRIGGER trg_XoaHDCT ON HoaDonChiTiet FOR DELETE AS 
BEGIN
	UPDATE SanPham
	SET SoLuong = sp.SoLuong + (SELECT SoLuong FROM deleted WHERE MaSP = sp.MaSP)
	FROM SanPham sp 
	JOIN deleted ON sp.MaSP = deleted.MaSP
END
GO

/*create view dùng để tính doanh thu*/

if OBJECT_ID ('DoanhThu_View') is not null
	drop view DoanhThu_View
go

CREATE VIEW DoanhThu_View As

select hd.MaHDBan,hd.NgayBan, SUM(((sp.Gia*ct.SoLuong)*(100-ct.GiamGia)/100)) as TongTien,
SUM(ct.SoLuong) as SPDaBan

from HoaDon hd 
		inner join HoaDonChiTiet ct on ct.MaHDBan = hd.MaHDBan
		inner join SanPham sp on sp.MaSP = ct.MaSP
GROUP BY hd.MaHDBan, hd.NgayBan

select * from HoaDon
--------------------------------------------------------------------------------------------------------------------
/*View xem doanh thu */

if OBJECT_ID ('test') is not null
	drop view test
go
CREATE VIEW test As

select hd.MaHDBan,hd.NgayBan,nd.MaND,nd.Hoten, SUM(((sp.Gia*ct.SoLuong)*(100-ct.GiamGia)/100)) as TongTien,
SUM(ct.SoLuong) as SPDaBan

from HoaDon hd 
		inner join HoaDonChiTiet ct on ct.MaHDBan = hd.MaHDBan
		inner join SanPham sp on sp.MaSP = ct.MaSP
		inner join nguoidung nd on nd.MaND = hd.MaNV
GROUP BY hd.MaHDBan, hd.NgayBan,nd.MaND,nd.Hoten


