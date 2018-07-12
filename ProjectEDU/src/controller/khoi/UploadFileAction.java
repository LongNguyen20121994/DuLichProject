package controller.khoi;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UploadFileAction extends ActionSupport implements ServletRequestAware {
	private File file;
	private String fileFileName;
	public HttpServletRequest request;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	@Override
	public String execute() {
		String filePath = "";
		try {
			ServletContext context = ServletActionContext.getServletContext();
			filePath = context.getRealPath("/upload");
		    System.out.println("Context Path " + filePath);
			File fileToCreate = new File(filePath, fileFileName);
			FileUtils.copyFile(this.file, fileToCreate);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
