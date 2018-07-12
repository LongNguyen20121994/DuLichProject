package model.bo.khoi;

import java.util.List;

import model.bean.TruongTHPT;
import model.dao.khoi.TruongTHPTDAO;

public class TruongTHPTBO {

	public boolean addListTruongTHPT(List<TruongTHPT> list) {
		// TODO Auto-generated method stub
		return new TruongTHPTDAO().addListTruongTHPT(list);
	}

	public boolean insertTruongTHPT(TruongTHPT truong) {
		// TODO Auto-generated method stub
		return new TruongTHPTDAO().insertTruongTHPT(truong);
	}

}
