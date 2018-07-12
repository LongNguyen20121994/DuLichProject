package controller.dulich;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLChiTietTour;
import model.bean.Info;

@SuppressWarnings("serial")
public class TaoChiTietTourAction extends ActionSupport {
	private DLChiTietTour ctTour;
	private HashMap<String,String> listTour;
	private Info info;
}
