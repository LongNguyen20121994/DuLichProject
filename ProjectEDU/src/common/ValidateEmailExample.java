package common;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ValidateEmailExample {
	public boolean validMail(String email){
		boolean isValid = false;
		try {
			//
			// Create InternetAddress object and validated the supplied
			// address which is this case is an email address.
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			isValid = true;
		} catch (AddressException e) {
			System.out.println("You are in catch block -- Exception Occurred for: " + email);
		}
		return isValid;
	}
	public static void main(String[] args){
		System.out.println(new ValidateEmailExample().validMail("daikho.90@gmail.commmm"));
	}
}