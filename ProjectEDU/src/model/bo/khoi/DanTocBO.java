package model.bo.khoi;

import java.util.List;

import model.bean.DanToc;
import model.dao.khoi.DanTocDAO;

public class DanTocBO {

	public boolean addListDanToc(List<DanToc> listDanToc) {
		// TODO Auto-generated method stub
		return new DanTocDAO().addListDanToc(listDanToc);
	}

}
