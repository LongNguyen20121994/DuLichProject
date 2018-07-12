-- Khách hàng
Create table NhanVien(
	SoCMND varchar(9) primary key CHECK(SoCMND LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	HoTen nvarchar(100) not null,
	MatKhau varchar(max),
	NgaySinh date not null check(year(getdate()) - year(NgaySinh) >= 18),
	GioiTinh bit default ('false'),
	SoDT varchar(20)Check(SoDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' or SoDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	Email varchar(40)check (Email  like '%@%.%'),
	HinhAnh varchar(max),
	isAdmin bit default ('false')
)
--Tỉnh
Create table Tinh(
	MaTinh varchar(20) primary key,
	TenTinh nvarchar(50) not null
)
-- Tour
Create table Tour(
	MaTour varchar(10) primary key,
	TieuDe varchar(max) not null,
	AnhDaiDien varchar(max),
	MoTaTongQuan nvarchar(max),
	LichTrinh nvarchar(max),
	DiaDiemKhoiHanh nvarchar(500),
	SoNgay int,
	SoDem int,
	GhiChu nvarchar(max),
	MaTinh varchar(20) foreign key references Tinh(MaTinh)
)
-- Chi tiết Tour
Create table ChiTietTour(
	MaChiTiet int,
	MaTour varchar(10) foreign key references Tour(MaTour),
	NgayKhoiHanh date,
	DacDiem nvarchar(500),
	GiaVe decimal,
	SoCho int,
	SoChoDaDat int,
	primary key (MaChiTiet,MaTour)
)