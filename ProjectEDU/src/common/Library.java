package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

public class Library {
	public static String formatDate(String s) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date dates;
		try {
			dates = format.parse(s);
		} catch (ParseException e) {
			dates = null;
		}
		SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yyyy");
		return fm.format(dates);
	}

	public static boolean checkDate(String s) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
		Date dates;
		try {
			dates = format.parse(s);
		} catch (ParseException e) {
			dates = null;
		}
		if (dates != null)
			return true;
		return false;
	}

	public static Date pasreDate(String s) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date dates = null;
		try {
			dates = format.parse(s);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return dates;
	}

	public static String dateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(date);
	}

	public Date stringtoDate(String day, String fm) {
		Date d = null;
		SimpleDateFormat f = new SimpleDateFormat(fm);
		try {
			d = f.parse(day);
		} catch (ParseException e) {
			System.err.println("Error input ! ");
		}
		return d;
	}

	public static String xoaDau(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
	}

	public static String md5(String msg) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(msg.getBytes());
			byte byteData[] = md.digest();
			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (Exception ex) {
			return "";
		}
	}

	public static String renameFile(String folder,String fileName, String soCMND) {
		ServletContext context = ServletActionContext.getServletContext();
		String filePath = context.getRealPath(folder);
		String arr[] = fileName.split(Pattern.quote("."));
		String fileName2 = soCMND + "." + arr[arr.length - 1];
		if (!fileName.equals(fileName2)) {
			File file1 = new File(filePath, fileName);
			File file2 = new File(filePath, fileName2);
			if (file2.exists()) {
				file2.delete();
			}
			System.out.println("File rename : " + file1.renameTo(file2));
		} else {
			System.out.println("File exists!");
		}
		return fileName2;
	}
	public static java.sql.Date untilToSql(java.util.Date until) {
		java.sql.Date sql = new java.sql.Date(until.getTime());
		return sql;
	}

	public Date stringToDate(String day, String fm) {
		Date d = null;
		SimpleDateFormat f = new SimpleDateFormat(fm);
		try {
			d = f.parse(day);
		} catch (ParseException e) {
			System.err.println("Error input ! ");
		}
		return d;
	}


	public static String getExensionFile(String fileName) {
		String[] part;
		if (fileName == null)
			return null;
		else {
			part = fileName.split(Pattern.quote("."));
			return part[part.length - 1];
		}
	}

	public void copyFile(File input, File output) {

		InputStream inStream = null;
		OutputStream outStream = null;
		try {
			inStream = new FileInputStream(input);
			outStream = new FileOutputStream(output);

			byte[] buffer = new byte[1024];
			int length;

			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}

			inStream.close();
			outStream.close();
			System.out.println("File is copied successful!");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public java.util.Date sqlToUntil(java.sql.Date until) {
		java.util.Date date = new java.util.Date(until.getTime());
		return date;
	}

	public static String trimString(Object s) {
		if (s != null) {
			String st = String.valueOf(s).trim();
			return st;
		} else {
			return null;
		}
	}
	
	public static void main(String[] args){
		Random random = new Random();
		System.out.println(String.valueOf(Math.abs(random.nextInt())));
	}
}
