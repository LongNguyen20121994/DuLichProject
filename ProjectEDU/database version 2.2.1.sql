Create Database EDUProject
go
use EDUProject
--Tỉnh - thành phố
Create table Tinh_ThanhPho(
	MaTinh varchar(20) primary key,
	TenTinh nvarchar(50) not null
)
-- Huyện - quận
Create table Huyen_Quan(
	MaTinh varchar(20) foreign key references Tinh_ThanhPho(MaTinh),
	MaHuyen varchar(20),
	TenHuyen nvarchar(50) not null,
	primary key(MaTinh,MaHuyen)
)
-- Xã phường
create table Xa_Phuong(
	MaTinh varchar(20),
	MaHuyen varchar(20),
	MaXa varchar(20),
	TenXa nvarchar(50) not null,
	foreign key (MaTinh,MaHuyen) references Huyen_Quan(MaTinh,MaHuyen),
	primary key(MaTinh,MaHuyen,MaXa)	
)
-- dân tộc
Create table DanToc(
	MaDT varchar(20) primary key,
	TenDanToc nvarchar(50) not null,
	GhiChu nvarchar(max)
)
-- đối tượng ưu tiên
Create table DoiTuongUT(
	MaDT varchar(10) primary key,
	TenDT nvarchar(50) not null,
	DiemCong money,
	GhiChu nvarchar(max)
)
-- Ngành đào tạo
Create table Nganh(
	MaNganh varchar(10) primary key,
	TenNganh nvarchar(50) not null
)
-- Trường ĐH - CĐ
Create table TruongDH_CD(
	MaTruong varchar(10) primary key,
	TenTruong nvarchar(100) not null,
	DiaChi varchar(20) foreign key references Tinh_ThanhPho(MaTinh),
	GhiChu ntext
) 
-- Giảng viên
Create table GiangVien(
	SoCMND varchar(9) primary key CHECK(SoCMND LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	HoTen nvarchar(100) not null,
	MatKhau varchar(max),
	NgaySinh date not null check(year(getdate()) - year(NgaySinh) >= 18),
	MaXa varchar(20),
	MaHuyen varchar(20) not null,
	MaTinh varchar(20) not null,
	GioiTinh bit default ('false'),
	SoDT varchar(20)Check(SoDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' or SoDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	Email varchar(40)check (Email  like '%@%.%'),
	MaTruong varchar(10)foreign key references TruongDH_CD(MaTruong),
	HinhAnh varchar(max),
	Logined bit default('false'),
	TrangThai bit default ('false'),
	foreign key (MaTinh,MaHuyen) references Huyen_Quan(MaTinh,MaHuyen),
	foreign key (MaTinh,MaHuyen,MaXa) references Xa_Phuong(MaTinh,MaHuyen,MaXa)
)
-- Ngành - ĐHCĐ
Create table Nganh_DHCD(
	MaNganh varchar(10) foreign key references Nganh(MaNganh),
	MaTruong varchar(10)foreign key references TruongDH_CD(MaTruong),
	DaoTao char(4) check(DaoTao in ('DHCQ','DHLT','CDCQ','CDLT','TCCN')),
	ChiTieu int check(ChiTieu >0),
	GhiChu ntext,
	primary key(MaNganh,MaTruong,DaoTao)
)
-- Khu vực ưu tiên
Create table KhuVucUT(
	MaKV varchar(20) primary key,
	TenKhuVuc nvarchar(50),
	DiemCong money,
	GhiChu nvarchar(max)
)
-- Trường THPT
Create table TruongTHPT(
	MaTinh varchar(20) foreign key references Tinh_ThanhPho(MaTinh),
	MaTruong varchar(30),
	TenTruong nvarchar(100) not null,
	KhuVucUT varchar(20) foreign key references KhuVucUT(MaKV),
	DiaChi nvarchar(200),
	primary key (MaTinh,MaTruong)
)
-- Giáo viên
Create table GiaoVien(
	SoCMND varchar(9) primary key CHECK(SoCMND LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	HoTen nvarchar(100) not null,
	MatKhau varchar(max),
	NgaySinh date not null check(year(getdate()) - year(NgaySinh) >= 18),
	MaXa varchar(20),
	MaHuyen varchar(20) not null,
	MaTinh varchar(20) not null,
	GioiTinh bit default ('false'),
	SoDT varchar(20)Check(SoDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' or SoDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	Email varchar(40)check (Email  like '%@%.%'),
	MaTruongTHPT varchar(30),
	MaTinhTHPT varchar(20), 
	HinhAnh varchar(max),
	Logined bit default('false'),
	TrangThai bit default ('false'),
	foreign key (MaTinh,MaHuyen) references Huyen_Quan(MaTinh,MaHuyen),
	foreign key (MaTinh,MaHuyen,MaXa) references Xa_Phuong(MaTinh,MaHuyen,MaXa),
	foreign key (MaTinhTHPT,MaTruongTHPT) references TruongTHPT(MaTinh,MaTruong)
)
-- Thí sinh
create table ThiSinh(
	SoCMND varchar(9) primary key CHECK(SoCMND LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	HoTen nvarchar(100) not null,
	NgaySinh date not null check(year(getdate()) - year(NgaySinh) >= 17),
	GioiTinh bit default ('false'),
	DiaChi nvarchar(100) not null,
	MatKhau varchar(max),
	SoDT varchar(20)Check(SoDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' or SoDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	Email varchar(40)check (Email  like '%@%.%'),
	MaXa varchar(20),
	MaHuyen varchar(20) not null,
	MaTinh varchar(20) not null,
	DanToc varchar(20) foreign key references DanToc(MaDT),
	DoiTuongUT varchar(10) foreign key references DoiTuongUT(MaDT),
	NamTN int not null check(NamTN >1900),
	NoiSinh nvarchar(100),
	HinhAnh varchar(max),
	Logined bit default('false'),
	TrangThai bit default ('false'),
	NguoiDK varchar(9) foreign key references GiaoVien(SoCMND),
	foreign key (MaTinh,MaHuyen) references Huyen_Quan(MaTinh,MaHuyen),
	foreign key (MaTinh,MaHuyen,MaXa) references Xa_Phuong(MaTinh,MaHuyen,MaXa)
)
-- Thí sinh - trường THPT
Create table ThiSinh_THPT(
	SoCMND varchar(9)foreign key references ThiSinh(SoCMND),
	MaTruongTHPT varchar(30),
	MaTinhTHPT varchar(20),  
	Lop int not null check(Lop >=10 and Lop <=12),
	foreign key (MaTinhTHPT,MaTruongTHPT) references TruongTHPT(MaTinh,MaTruong),
	primary key (SoCMND,MaTruongTHPT,MaTinhTHPT,Lop)
)
-- Môn thi
Create table MonThi(
	MaMon varchar(5) primary key,
	TenMonThi nvarchar(30) not null,
)
-- Cụm thi
Create table CumThi(
	MaCT varchar(10) primary key,
	TenCumThi nvarchar(50) not null,
)
-- Hội đồng thi
Create table HoiDongThi(
	MaHDT varchar(20) primary key,
	MaCT varchar(10) foreign key references CumThi(MaCT), 
	TenHDT nvarchar(60)not null,
)
-- Địa điểm thi
Create table DiaDiemThi(
	MaDDT varchar(30) primary key,
	MaHDT varchar(20) foreign key references HoiDongThi(MaHDT),
	TenDDT nvarchar(50) not null,
	DiaChi nvarchar(100) not null,
)
-- Phòng thi
Create table PhongThi(
	MaPT varchar(30) primary key,
	MaDDT varchar(30) foreign key references DiaDiemThi(MaDDT),
	TenPT varchar(10) not null,
)
-- Phòng thi thực tế
Create table PhongThiThucTe(
	IDPhong varchar(20) primary key,
	MaMon varchar(5) foreign key references MonThi(MaMon),
	MaPT varchar(30) foreign key references PhongThi(MaPT),
	NgayGioThi datetime not null
)
-- Năm tuyển sinh
create table NamTuyenSinh(
	NamTS int primary key,
	GhiChu ntext
) 
-- Đăng ký dự thi
Create table DangKyDuThi(
	SoCMND varchar(9)foreign key references ThiSinh(SoCMND),
	MaCT varchar(10) foreign key references CumThi(MaCT),
	SBD varchar(40),
	NamTS int not null foreign key references NamTuyenSinh(NamTS),
	NgayDK datetime not null default(GetDate()),
	primary key(NamTS,SoCMND)
)
-- Chi tiết đăng ký dự thi
Create table ChiTietDKDT(
	SoCMND varchar(9),
	NamTS int foreign key references NamTuyenSinh(NamTS),
	MaMon varchar(5) foreign key references MonThi(MaMon),
	DiemThi money check (DiemThi >=0 and DiemThi <=10),
	IDPhong varchar(20) foreign key references PhongThiThucTe(IDPhong),
	foreign key (NamTS,SoCMND) references DangKyDuThi(NamTS,SoCMND),
	primary key(SoCMND,MaMon,NamTS)
)
-- Đợt xét tuyển
Create table DotXetTuyen(
	MaDotXT varchar(10) primary key,
	TenDotXT nvarchar(50) unique,
	TrangThai bit default('false')
)
-- Hồ sơ xét tuyển
Create table HoSoXetTuyen(
	MaHS varchar(30) primary key,
	SoCMND varchar(9)foreign key references ThiSinh(SoCMND),
	NamTS int foreign key references NamTuyenSinh(NamTS),
	MaDotXT varchar(10) foreign key references DotXetTuyen(MaDotXT),
	MaTruong varchar(10) foreign key references TruongDH_CD(MaTruong),
	NgayNop datetime default(getDate()),
	unique(SoCMND,NamTS,MaDotXT,MaTruong)
)
-- Khối thi
Create table KhoiThi(
	MaKhoi varchar(5) primary key,
	TenKhoi nvarchar(20) not null,
)
-- Khối thi - Ngành ĐHCĐ
Create Table KhoiThi_NganhDHCD(
	MaNganh varchar(10),
	MaTruong varchar(10),
	DaoTao char(4),
	MaKhoi varchar(5) foreign key references KhoiThi(MaKhoi),
	NamTuyenSinh int foreign key references NamTuyenSinh(NamTS),
	DiemChuan money check (DiemChuan>=0) default(0), 
	foreign key(MaNganh,MaTruong,DaoTao) references Nganh_DHCD(MaNganh,MaTruong,DaoTao),
	primary key(MaNganh,MaTruong,DaoTao, MaKhoi, NamTuyenSinh)
)
-- Chi tiết hồ sơ xét tuyển
Create table ChiTietHoSo(
	MaHS varchar(30) foreign key references HoSoXetTuyen(MaHS),
	MaNganh varchar(10),
	MaTruong varchar(10),
	DaoTao char(4),
	MaKhoi varchar(5),
	NamTuyenSinh int,
	TongDiem money,
	TrungTuyen bit default('false'),
	SendMail bit default('false')
	foreign key(MaNganh,MaTruong,DaoTao, MaKhoi, NamTuyenSinh) references KhoiThi_NganhDHCD(MaNganh,MaTruong,DaoTao, MaKhoi, NamTuyenSinh),
	primary key (MaHS,MaNganh,MaTruong,DaoTao, MaKhoi, NamTuyenSinh)
) 
-- Xét tuyển(Hệ số môn xét tuyển vào ngành)
Create table XetTuyen(
	MaNganh varchar(10),
	MaTruong varchar(10),
	DaoTao char(4),
	MaKhoi varchar(5),
	NamTuyenSinh int,
	MaMon varchar(5) foreign key references MonThi(MaMon),
	HeSo int check (HeSo >= 1),
	foreign key(MaNganh,MaTruong,DaoTao, MaKhoi, NamTuyenSinh) references KhoiThi_NganhDHCD(MaNganh,MaTruong,DaoTao, MaKhoi, NamTuyenSinh),
	primary key (MaNganh,MaTruong,DaoTao, MaKhoi, NamTuyenSinh, MaMon)
)
-- Chi tiết khối thi
Create table ChiTietKhoiThi(
	MaMon varchar(5) foreign key references MonThi(MaMon),
	MaKhoi varchar(5) foreign key references KhoiThi(MaKhoi),
	primary key(MaMon, MaKhoi)
)
-- Quản lý cụm thi
Create table QuanLyCumThi(
	SoCMND varchar(9)primary key CHECK(SoCMND LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	HoTen nvarchar(100) not null,
	MatKhau varchar(max),
	NgaySinh date not null check(year(getdate()) - year(NgaySinh) >= 18),
	MaXa varchar(20),
	MaHuyen varchar(20) not null,
	MaTinh varchar(20) not null,
	GioiTinh bit default ('false'),
	SoDT varchar(20)Check(SoDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' or SoDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	Email varchar(40)check (Email  like '%@%.%'),
	DonViThi varchar(10) foreign key references CumThi(MaCT),
	HinhAnh varchar(max),
	Logined bit default('false'),
	TrangThai bit default ('false'),
	foreign key (MaTinh,MaHuyen) references Huyen_Quan(MaTinh,MaHuyen),
	foreign key (MaTinh,MaHuyen,MaXa) references Xa_Phuong(MaTinh,MaHuyen,MaXa)
)
-- Quản trị viên
Create table QuanTriVien(
	SoCMND varchar(9)primary key CHECK(SoCMND LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	HoTen nvarchar(100) not null,
	MatKhau varchar(max),
	NgaySinh date not null check(year(getdate()) - year(NgaySinh) >= 18),
	MaXa varchar(20),
	MaHuyen varchar(20) not null,
	MaTinh varchar(20) not null,
	GioiTinh bit default ('false'),
	SoDT varchar(20)Check(SoDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' or SoDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	Email varchar(40)check (Email  like '%@%.%'),
	HinhAnh varchar(max),
	Logined bit default('false'),
	TrangThai bit default ('false'),
	foreign key (MaTinh,MaHuyen) references Huyen_Quan(MaTinh,MaHuyen),
	foreign key (MaTinh,MaHuyen,MaXa) references Xa_Phuong(MaTinh,MaHuyen,MaXa)
)

go

-- Hàm tìm ID tiếp theo
CREATE function fn_NextID (@lastid varchar(10),@prefix varchar(10),@size int)
  returns varchar(10)
as
    BEGIN
        IF(@lastid = '')
            set @lastid = @prefix + REPLICATE (0,@size - LEN(@prefix)) 
        declare @num_nextid int, @nextid varchar(10)
        set @lastid = LTRIM(RTRIM(@lastid))
        -- number next id
        set @num_nextid = replace(@lastid,@prefix,'') + 1
        -- bo di so luong ky tu tien to
        set @size = @size - len(@prefix)
        -- replicate số lượng số 0 REPLICATE(0,3) => 000
        set @nextid = @prefix + REPLICATE (0,@size - LEN(@prefix)) 
        set @nextid = @prefix + RIGHT(REPLICATE(0, @size) + CONVERT (VARCHAR(MAX), @num_nextid), @size)
        return @nextid
    END;
go

-- Hàm tính tổng điểm
create function tongDiem(@maKhoi as varchar(5), @soCMND as varchar(9)) 
returns money
as
	begin
		declare @diem1 money, @diem2 money
		select @diem1=(kv.DiemCong+dt.DiemCong)
		from ThiSinh as ts inner join ThiSinh_THPT as tst on ts.SoCMND=tst.SoCMND
		inner join TruongTHPT as t on tst.MaTruongTHPT =t.MaTruong and tst.MaTinhTHPT = t.MaTinh
		inner join KhuVucUT as kv on t.KhuVucUT = kv.MaKV
		left join DoiTuongUT as dt on ts.DoiTuongUT = dt.MaDT
		where Lop = 12 and ts.SoCMND=@soCMND

		select @diem2=sum(DiemThi) from ChiTietDKDT where SoCMND=@soCMND and MaMon in(select MaMon from ChiTietKhoiThi where MaKhoi=@maKhoi)
		return @diem1+@diem2
	end
	
go

-- Trigger tự động tạo mã hồ sơ
create trigger updatePrimaryKeyHoSoXetTuyen
on HoSoXetTuyen
for insert
as
	begin
		DECLARE @lastid nvarchar(10)
        SELECT TOP 1 @lastid = MaHS from HoSoXetTuyen order by MaHS desc
        UPDATE HoSoXetTuyen set MaHS = dbo.fn_NextID (@lastid,'HS',10) where MaHS = ''
	end
go

-- Trigger tự động tính tổng điểm khi đăng ký xét tuyển
create trigger updateTongDiem
on ChiTietHoSo
for insert
as
	begin
		declare @diem1 money,@diem2 money,@soCMND varchar(9),@maHS varchar(30),@maNganh varchar(10)
		declare @maTruong varchar(10),@daoTao char(4),@maKhoi varchar(5),@namTuyenSinh int
		select @maHS=MaHS,@maNganh=MaNganh,@maTruong=MaTruong,@daoTao=DaoTao,@maKhoi=MaKhoi,@namTuyenSinh=NamTuyenSinh 
		from inserted
		
		select @soCMND=SoCMND from HoSoXetTuyen where MaHS=@maHS
		
		select @diem1=(kv.DiemCong+dt.DiemCong)
		from ThiSinh as ts inner join ThiSinh_THPT as tst on ts.SoCMND=tst.SoCMND
		inner join TruongTHPT as t on tst.MaTruongTHPT =t.MaTruong and tst.MaTinhTHPT = t.MaTinh
		inner join KhuVucUT as kv on t.KhuVucUT = kv.MaKV
		left join DoiTuongUT as dt on ts.DoiTuongUT = dt.MaDT
		where Lop = 12 and ts.SoCMND=@soCMND

		select @diem2=sum(DiemThi*HeSo) from ChiTietDKDT as ct
		inner join XetTuyen as xt on ct.MaMon=xt.MaMon where SoCMND=@soCMND and MaNganh=@maNganh 
			and MaTruong=@maTruong and DaoTao=@daoTao and MaKhoi=@maKhoi and NamTuyenSinh=@namTuyenSinh
		
		update ChiTietHoSo set TongDiem=@diem1+@diem2 
		where MaHS=@maHS and MaNganh=@maNganh and MaTruong=@maTruong and DaoTao=@daoTao and MaKhoi=@maKhoi and NamTuyenSinh=@namTuyenSinh 
	end
	
go

-- Trigger cập nhật trúng tuyển khi công bố điểm chuẩn
create trigger updateTrungTuyen
on KhoiThi_NganhDHCD
for update
as
	begin
		declare @diem money,@soCMND varchar(9),@maNganh varchar(10)
		declare @maTruong varchar(10),@daoTao char(4),@maKhoi varchar(5),@namTuyenSinh int
		select @maNganh=MaNganh,@maTruong=MaTruong,@daoTao=DaoTao,@maKhoi=MaKhoi,@namTuyenSinh=NamTuyenSinh,@diem=DiemChuan
		from inserted
		update ChiTietHoSo set TrungTuyen='true'
		where MaNganh=@maNganh and MaTruong=@maTruong and DaoTao=@daoTao and MaKhoi=@maKhoi and NamTuyenSinh=@namTuyenSinh and TongDiem>=@diem 
	end