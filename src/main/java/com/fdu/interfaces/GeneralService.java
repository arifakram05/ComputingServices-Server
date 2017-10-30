package com.fdu.interfaces;

import java.util.List;

import com.fdu.database.DBConnection;
import com.fdu.impl.GeneralServiceImpl;
import com.fdu.model.Wiki;

public interface GeneralService {

	List<Wiki> getWikis();

	boolean deleteWiki(String fileId);

	void sendEmail(String emailDetails);

	class Factory {
		private static final GeneralServiceImpl INSTANCE = new GeneralServiceImpl(DBConnection.getConnection());

		public static GeneralServiceImpl getInstance() {
			return INSTANCE;
		}
	}

}
