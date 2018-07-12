package model.bo.khoi;

import java.util.List;

import model.bean.KhuVucUT;
import model.dao.khoi.KhuVucUTDAO;

public class KhuVucUTBO {

	public List<KhuVucUT> getAllSelect() {
		return new KhuVucUTDAO().getAllSelect();
	}

	public boolean updateKhuVucUT(KhuVucUT kv) {
		return new KhuVucUTDAO().updateKhuVucUT(kv);
	}

	public boolean insertKhuVucUT(KhuVucUT kv) {
		return new KhuVucUTDAO().insertKhuVucUT(kv);
	}

	public boolean deleteListKhuVucUT(List<String> listMaKV) {
		return new KhuVucUTDAO().deleteListKhuVucUT(listMaKV);
	}
}
