package model.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DLHopDongChiTiet {
	public String createHopDong(DLTour tour, DLKhachHang kh, DLTourTrangChu tourTC, DLHopDong hdt) {
		String hd ="<table border=\"0\">\r\n" + 
				"	<tbody>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"			<p><strong>C&Ocirc;NG TY DU LỊCH VIỆT</strong></p>\r\n" + 
				"\r\n" + 
				"			<p>&nbsp;</p>\r\n" + 
				"			</td>\r\n" + 
				"			<td>\r\n" + 
				"			<p><strong>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; CỘNG HO&Agrave; X&Atilde; HỘI CHỦ NGHĨA VIỆT NAM</strong><br />\r\n" + 
				"			<strong>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Độc lập &ndash; Tự do &ndash; Hạnh ph&uacute;c</strong><br />\r\n" + 
				"			<strong>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;-------------------------</strong></p>\r\n" + 
				"\r\n" + 
				"			<p><em>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ";

		hd += dateCreate();

		hd+="</em></p>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</tbody>\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<p style=\"text-align:center\"><strong>HỢP ĐỒNG KINH TẾ</strong></p>\r\n" + 
				"\r\n" + 
				"<p>(";
		hd += tour.getTieuDe();
		hd += ")</p>\r\n" + 
				"\r\n" + 
				"<p><em>- Căn cứ Luật thương mại được Quốc hội nước Cộng ho&agrave; x&atilde; hội chủ nghĩa Việt Nam kh&oacute;a XI, kỳ họp thứ VII th&ocirc;ng qua ng&agrave;y 14 th&aacute;ng 06 năm 2005.</em></p>\r\n" + 
				"\r\n" + 
				"<p><em>- Căn cứ Bộ luật d&acirc;n sự được Quốc hội nước cộng ho&agrave; x&atilde; hội chủ nghĩa Việt Nam kho&aacute; XIII th&ocirc;ng qua ng&agrave;y 24 th&aacute;ng 11 năm 2015.</em></p>\r\n" + 
				"\r\n" + 
				"<p><em>- Căn cứ nhu cầu v&agrave; khả năng của hai b&ecirc;n.</em></p>\r\n" + 
				"\r\n" + 
				"<p>H&ocirc;m nay,";
		hd += dateCreate();
		hd += "\r\n" + 
				"\r\n" + 
				"<p>Ch&uacute;ng t&ocirc;i gồm c&oacute;:</p>\r\n" + 
				"\r\n" + 
				"<p><strong>B&ecirc;n A:......................................................................................................................................</strong></p>\r\n" + 
				"\r\n" + 
				"<p>Người đại diện:";
		hd += kh.getHoTen();
		hd += "</p>\r\n" + 
				"\r\n" + 
				"<p>Địa chỉ:";
		hd += kh.getDiaChi();
		hd += "</p>\r\n" + 
				"\r\n" + 
				"<p>Điện thoại:";
		hd += kh.getSoDT();
		hd += "</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;<strong>B&ecirc;n B: C&Ocirc;NG TY TNHH DU LỊCH VIỆT</strong></p>\r\n" + 
				"\r\n" + 
				"<p>Địa chỉ: 95B-97-99 Trần Hưng Đạo, Phường Cầu &Ocirc;ng L&atilde;nh, Quận 1, TP HCM</p>\r\n" + 
				"\r\n" + 
				"<p>Người đại diện: &Ocirc;ng Trần Văn Long&nbsp;&ndash; Chức Vụ: Tổng Gi&aacute;m đốc C&ocirc;ng ty Du lịch Việt</p>\r\n" + 
				"\r\n" + 
				"<p>Điện thoại: (+84 4) 3512 3388&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &ndash; Fax: (+84 24) 3512 3388&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><strong>Hai b&ecirc;n thống nhất k&yacute; một số điều khoản phục vụ kh&aacute;ch du lịch như sau:</strong></p>\r\n" + 
				"\r\n" + 
				"<p><strong>Điều 1:</strong>&nbsp;<strong>Chương tr&igrave;nh tham quan du lịch &ndash; ";
		hd += tour.getTieuDe();
		hd += "</strong></p>\r\n" + 
				"\r\n" + 
				"<p><strong>B&ecirc;n B tổ chức cho b&ecirc;n A chương tr&igrave;nh:</strong></p>\r\n" + 
				"\r\n" + 
				"<p>(C&oacute; chương tr&igrave;nh chi tiết v&agrave; l&agrave; một phần kh&ocirc;ng thể t&aacute;ch rời của hợp đồng)</p>\r\n" + 
				"\r\n" + 
				"<ul>\r\n" + 
				"	<li>Phương Tiện: Xe &ocirc; t&ocirc; đời mới c&oacute; m&aacute;y lạnh hiện đại, tivi, ghế ngả..., L&aacute;i xe nhiệt t&igrave;nh vui vẻ</li>\r\n" + 
				"	<li>Mức ăn ch&iacute;nh: 120.000đ/bữa ch&iacute;nh Theo chương tr&igrave;nh + Ăn s&aacute;ng 35.000đ/bữa</li>\r\n" + 
				"	<li>Ph&ograve;ng Nghỉ ti&ecirc;u chuẩn 3 sao, Nghỉ từ 2 - 4 người/ph&ograve;ng</li>\r\n" + 
				"	<li>Hướng Dẫn Vi&ecirc;n: Chuy&ecirc;n nghiệp, phục vụ nhiệt t&igrave;nh, th&agrave;nh thạo,chu đ&aacute;o suốt tuyến</li>\r\n" + 
				"	<li>V&eacute; thăm quan: Kh&aacute;ch được mua tiền v&eacute; v&agrave;o cửa c&aacute;c thắng cảnh c&oacute; trong tour</li>\r\n" + 
				"	<li>T&agrave;u thuyền tham quan theo chương tr&igrave;nh</li>\r\n" + 
				"	<li>Bảo hiểm du lịch theo quy định của Tổng Cục Du Lịch</li>\r\n" + 
				"	<li>Nước uống tr&ecirc;n xe, thuốc chống say...</li>\r\n" + 
				"</ul>\r\n" + 
				"\r\n" + 
				"<p><strong>Điều 2: Thời gian thực hiện</strong></p>\r\n" + 
				"\r\n" + 
				"<p>1. Thời gian thực hiện: ";
		hd += tourTC.getSoNgayDem() + " từ ngày: " +tourTC.getNgayKhoiHanh();
		hd += "</p>\r\n" + 
				"\r\n" + 
				"<p>2. Điểm đ&oacute;n, cụ thể tại:";
		hd += tourTC.getDiaDiem();
		hd += "</p>\r\n" + 
				"\r\n" + 
				"<p><em><strong>Để đảm bảo t&agrave;i sản v&agrave; sự an to&agrave;n của Qu&yacute; Kh&aacute;ch, l&aacute;i xe của c&ocirc;ng ty sẽ trả kh&aacute;ch tại điểm m&agrave; xe đ&oacute;n kh&aacute;ch l&uacute;c đầu.</strong></em></p>\r\n" + 
				"\r\n" + 
				"<p><strong>Điều 3: Gi&aacute; trị hợp đồng</strong></p>\r\n" + 
				"\r\n" + 
				"<p>Gi&aacute; cho 01 kh&aacute;ch:";
		hd += tourTC.getGiaVe();
		hd += " VNĐ. Tổng số kh&aacute;ch theo hợp đồng: ";
		hd += sumPerson(tourTC);
		hd += " người</p>\r\n" + 
				"\r\n" + 
				"<p>Tổng gi&aacute; trị hợp đồng: (gồm 10% VAT):";
		hd += sumPerson(tourTC);
		hd += "</p>\r\n" + 
				"\r\n" + 
				"<p>(Bằng chữ: ";
		hd += docTien(String.valueOf(hdt.getGiaTien()));
		hd += ")</p>\r\n" + 
				"\r\n" + 
				"<p>Bảo hiểm du lịch: Mức đền b&ugrave; tối đa 2,000,000 đ/ người/ vụ.</p>\r\n" + 
				"\r\n" + 
				"<p><strong>Điều 5: Phương thức thanh to&aacute;n: </strong></p>\r\n";
		if(tourTC.getPtThanhToan() == true) {
			hd += "<p><strong>Thanh toán online:</strong>&nbsp;B&ecirc;n A đã chuyển cho b&ecirc;n B số tiền:";
			hd += tourTC.getGiaTien();
			hd += "VNĐ</p>\r\n" + 
					"\r\n" + 
					"<p>(Bằng chữ:";
			hd += docTien(String.valueOf(hdt.getGiaTien()));
		}
		if(tourTC.getPtThanhToan() == false) {
			hd += "<p><strong>Lần 01: Thanh toán online:</strong>&nbsp;B&ecirc;n A đã chuyển cho b&ecirc;n B số tiền:";
			hd += Math.round(tourTC.getGiaTien()* 0.3);
			hd += "VNĐ</p>\r\n" + 
					"\r\n" + 
					"<p>(Bằng chữ:";
			hd += docTien(String.valueOf(Math.round(hdt.getGiaTien() * 0.3)));
			hd += ")</p>\r\n" + 
					"\r\n" + 
					"<p><strong>Lần 02: Thanh toán trực tiếp:</strong>&nbsp;B&ecirc;n A phải thanh toán trước thời điểm bắt đầu tour cho b&ecirc;n B số tiền:";
			hd += tourTC.getGiaTien() - Math.round(tourTC.getGiaTien()* 0.3);
			hd += "VNĐ</p>\r\n" + 
					"\r\n" + 
					"<p>(Bằng chữ:";
			hd += docTien(String.valueOf(tourTC.getGiaTien() - Math.round(tourTC.getGiaTien()* 0.3)));
		}
		hd += ")</p>";
		hd += "<p>- Sau khi b&ecirc;n B thực hiện xong hợp đồng. B&ecirc;n A c&oacute; tr&aacute;ch nhiệm thanh to&aacute;n đầy đủ số tiền c&ograve;n lại theo số lượng thực tế cho b&ecirc;n B.</p>\r\n" + 
				"\r\n" + 
				"<p><strong>Điều 6: Điều kiện phạt hủy</strong></p>\r\n" + 
				"\r\n" + 
				"<p>1. Hai b&ecirc;n cam kết thực hiện hợp đồng, nếu một trong hai b&ecirc;n c&oacute; thay đổi, hủy bỏ phải b&aacute;o trước cho b&ecirc;n kia 10 ng&agrave;y trước khi khởi h&agrave;nh.</p>\r\n" + 
				"\r\n" + 
				"<p>2. Trong trường hợp b&aacute;o huỷ trước 8 đến 10 ng&agrave;y trước khi khởi h&agrave;nh, b&ecirc;n b&aacute;o hủy phải chịu phạt 30% tổng gi&aacute; trị hợp đồng; b&aacute;o hủy trước 5 đến 7 ng&agrave;y, th&igrave; phải chịu phạt 50% tổng gi&aacute; trị hợp đồng; B&aacute;o hủy trước 2 đến 4 ng&agrave;y th&igrave; phải chịu phạt 70% tổng gi&aacute; trị hợp đồng; B&aacute;o hủy trong v&ograve;ng 24h trước giờ khởi h&agrave;nh th&igrave; phải chịu phạt 100% tổng gi&aacute; trị hợp đồng. Mọi thay đổi, b&aacute;o hủy phải được th&ocirc;ng b&aacute;o bằng văn bản v&agrave; được sự chấp thuận của b&ecirc;n kia.</p>\r\n" + 
				"\r\n" + 
				"<p>3. Trong trường hợp v&igrave; một l&yacute; do bất khả kh&aacute;ng n&agrave;o đ&oacute; (b&atilde;o lụt, hoả hoạn, thi&ecirc;n tai, chiến tranh,...) hợp đồng kh&ocirc;ng thể thực hiện th&igrave; c&aacute;c b&ecirc;n c&ugrave;ng nhau b&agrave;n bạc giải quyết tr&ecirc;n tinh thần b&igrave;nh đẳng giữa hai b&ecirc;n.</p>\r\n" + 
				"\r\n" + 
				"<p><strong>Điều 7: Tr&aacute;ch nhiệm của c&aacute;c b&ecirc;n</strong></p>\r\n" + 
				"\r\n" + 
				"<p>1. B&ecirc;n A c&oacute; tr&aacute;ch nhiệm th&ocirc;ng b&aacute;o chi tiết v&agrave; x&aacute;c nhận về lượng kh&aacute;ch k&egrave;m theo danh s&aacute;ch tr&iacute;ch ngang, địa điểm, thời gian, v&agrave; th&ocirc;ng tin li&ecirc;n quan của đo&agrave;n kh&aacute;ch trước 03 ng&agrave;y khởi h&agrave;nh cho b&ecirc;n B. B&ecirc;n B c&oacute; tr&aacute;ch nhiệm đưa đ&oacute;n, phục vụ đo&agrave;n kh&aacute;ch của b&ecirc;n A đ&uacute;ng như trong lộ tr&igrave;nh chi tiết của phụ lục k&egrave;m theo hợp đồng, bảo đảo chất lượng dịch vụ theo hợp đồng.</p>\r\n" + 
				"\r\n" + 
				"<p>2. B&ecirc;n A thanh to&aacute;n đầy đủ, đ&uacute;ng hạn cho b&ecirc;n B tổng gi&aacute; trị hợp đồng theo phương thức đ&atilde; n&ecirc;u tr&ecirc;n. Nếu ph&aacute;t sinh chi ph&iacute; cho việc l&agrave;m hay y&ecirc;u cầu của b&ecirc;n A th&igrave; b&ecirc;n A phải thanh to&aacute;n th&ecirc;m khoản chi ph&iacute; đ&oacute; cho b&ecirc;n B.</p>\r\n" + 
				"\r\n" + 
				"<p>3. Trong qu&aacute; tr&igrave;nh thực hiện hợp đồng, mọi ph&aacute;t sinh tranh chấp đều được hai b&ecirc;n c&ugrave;ng nhau b&agrave;n bạc v&agrave; giải quyết tr&ecirc;n tinh thần b&igrave;nh đẳng hai b&ecirc;n đều c&oacute; lợi.</p>\r\n" + 
				"\r\n" + 
				"<p>4. Những phụ lục hợp đồng k&egrave;m theo c&oacute; gi&aacute; trị ph&aacute;p l&yacute; như bản hợp đồng n&agrave;y.</p>\r\n" + 
				"\r\n" + 
				"<p>5. Hai b&ecirc;n cam kết thực hiện đ&uacute;ng những điều khoản như trong hợp đồng, b&ecirc;n n&agrave;o thực hiện sai g&acirc;y tổn hại về thời gian, vật chất cho b&ecirc;n kia th&igrave; phải chịu tr&aacute;ch nhiệm bồi ho&agrave;n phần tổn hại đ&oacute; cho b&ecirc;n kia theo quy định trước ph&aacute;p luật.</p>\r\n" + 
				"\r\n" + 
				"<p>6. Hợp đồng n&agrave;y gồm c&oacute; 03 trang v&agrave; được lập 02 bản, mỗi b&ecirc;n giữ 01 bản c&oacute; gi&aacute; trị ph&aacute;p l&yacute; như nhau v&agrave; c&oacute; hiệu lực kể từ ng&agrave;y k&yacute;.</p>\r\n" + 
				"\r\n" + 
				"<table border=\"0\">\r\n" + 
				"	<tbody>\r\n" + 
				"		<tr>\r\n" + 
				"			<td><strong>ĐẠI DIỆN B&Ecirc;N A&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</strong></td>\r\n" + 
				"			<td><strong>ĐẠI DIỆN B&Ecirc;N B</strong></td>\r\n" + 
				"		</tr>\r\n" + 
				"	</tbody>\r\n" + 
				"</table>";
		return hd;
	}
	
	// Đọc số tiền
	public String docTien(String tien) {
		DLDocSoTien doc = new DLDocSoTien();
        String kq = DLDocSoTien.ChuyenSangChu(tien);
        //doc.currencyFormat(tien);
        return kq;
	}
	
	// Số người
	public int sumPerson(DLTourTrangChu tour) {
		return tour.getSoNguoiLon() + tour.getSoTreEm() + tour.getSoTreNho() + tour.getSoSoSinh();
	}
	
	// Ngày tạo hợp đồng
	public String dateCreate() {
		String ngay = "";
		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
		String date = timeFormat.format(today.getTime());
		String so = date.substring(date.length() - 10, date.length()-8);
		ngay = "ngày "+so;
		so = date.substring(date.length() - 7, date.length() - 5);
		ngay += " tháng " + so;
		so = date.substring(date.length() - 4, date.length());
		ngay += " năm " + so;
		return ngay;
	}

}
