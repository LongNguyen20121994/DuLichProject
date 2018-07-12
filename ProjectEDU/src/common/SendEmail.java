package common;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail extends Thread {

	private String email;
	private String subject;
	private String content;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean sendGmail() {

		String to = email;
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("dct.edu.2015@gmail.com", "sieungoc");
			}
		});

		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("dct.edu.2015@gmail.com","High School Exam"));// change
																			// accordingly
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(content,"text/html; charset=utf-8" );  

			// send message
			Transport.send(message);

			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public SendEmail(String email, String subject, String content) {
		this.email = email;
		this.subject = subject;
		this.content = content;
	}

	public void run(){
		this.sendGmail();
	}

	public static void main(String[] args) {
		String content = "Sau khi học viên tốt nghiệp khóa đào tạo, Công ty sẽ ký Hợp đồng Lao động. Mức lương chính thức của học viên sau khi tốt nghiệp sẽ nằm trong khoảng từ 4,900,000 đến 6,900,000 VNĐ, tùy thuộc vào kết quả học tập. Trong quá trình đào tạo, các học viên có kết quả kém hoăc vi phạm kỷ luật mức thôi học sẽ bị chấm dứt Hợp đồng.";
		SendEmail em;
		for(int i = 109; true;i++){
			em = new SendEmail("pmq1995@gmail.com", "tiêu đề mail " + i, content);
			em.start();
			try {
				SendEmail.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Send..." + i);
		}
	}
}
