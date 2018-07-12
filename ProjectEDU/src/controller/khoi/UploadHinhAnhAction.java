package controller.khoi;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UploadHinhAnhAction extends ActionSupport {
	private File hinhAnh;
	private String hinhAnhFileName;
	public HttpServletRequest request;

	public File getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(File hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getHinhAnhFileName() {
		return hinhAnhFileName;
	}

	public void setHinhAnhFileName(String hinhAnhFileName) {
		this.hinhAnhFileName = hinhAnhFileName;
	}

	@Override
	public String execute() throws Exception {
		String filePath = "";
		try {
			ServletContext context = ServletActionContext.getServletContext();
			filePath = context.getRealPath("/anhThanhVien");
			System.out.println("Context Path " + filePath);
			File fileToCreate = new File(filePath, hinhAnhFileName);
			FileUtils.copyFile(this.hinhAnh, fileToCreate);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}
}
