﻿Create Database DuLichViet
go
use DuLichViet
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
-- Loai Tour
Create table LoaiTour(
	MaLoai varchar(20) primary key,
	TenLoai nvarchar(50) not null
)
-- Tour
Create table Tour(
	MaTour varchar(10) primary key,
	TieuDe nvarchar(max) not null,
	AnhDaiDien varchar(max),
	MoTaTongQuan nvarchar(max),
	LichTrinh nvarchar(max),
	SoNgay int,
	SoDem int,
	GhiChu nvarchar(max),
	MaLoai varchar(20) foreign key references LoaiTour(MaLoai)
)
-- Khach san
Create table KhachSan(
	MaKS varchar(10) primary key,
	TenKS nvarchar(250),
	HinhAnh varchar(max),
	MaTinh varchar(20) foreign key references Tinh(MaTinh),
	DiaChi nvarchar(500),
	Sao int,
	MoTa nvarchar(max)
)
-- Phương tiện
Create table PhuongTien(
	MaPT varchar(10) primary key,
	TenPT nvarchar(250),
	MaTinh varchar(20) foreign key references Tinh(MaTinh),
	MoTa nvarchar(max)
)
-- Chi tiết Tour
Create table ChiTietTour(
	MaChiTietTour varchar(10) primary key,
	MaTour varchar(10) foreign key references Tour(MaTour),
	MaKS varchar(10) foreign key references KhachSan(MaKS),
	MaPT varchar(10) foreign key references PhuongTien(MaPT),
	NgayKhoiHanh datetime,
	DiaDiemKhoiHanh nvarchar(500),
	DacDiem nvarchar(500),
	GiaVeNguoiLon decimal,
	SoCho int,
	SoChoDaDat int
)
-- Khach Hang
Create table KhachHang(
	MaKH varchar(10) primary key,
	HoTen nvarchar(100) not null,
	Email varchar(40) check (Email  like '%@%.%'),
	SoDT varchar(20) Check(SoDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' or SoDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	DiaChi nvarchar(500),
	GhiChu nvarchar(max),
	MatKhau varchar(max)
)
-- Chi Tiet Dat Tour
Create table ChiTietKhachHang(
	MaChiTiet varchar(10) primary key,
	MaKH varchar(10) foreign key references KhachHang(MaKH),
	HoTen nvarchar(100),
	NgaySinh date,
	GioiTinh bit default ('false'),
	LoaiKH int
)
Create table HopDong(
	MaHopDong varchar(10) primary key,
	TenHopDong nvarchar(500) not null,
	NgayKy datetime,
	MaKH varchar(10) foreign key references KhachHang(MaKH),
	MaNV varchar(9) foreign key references NhanVien(SoCMND),
	MaChiTietTour varchar(10) foreign key references ChiTietTour(MaChiTietTour),
	GiaTien decimal,
	SoTienDatCoc decimal,
	DieuKhoan nvarchar(max)
)