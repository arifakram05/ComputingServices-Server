package com.fdu.database.dao;

import java.util.Map;

import com.fdu.database.DBConnection;
import com.fdu.database.daoimpl.SettingsDAOImpl;

public interface SettingsDAO {

	Map<String, String> getSettings();

	boolean updateEmail(String email);

	class Factory {
		private static final SettingsDAOImpl INSTANCE = new SettingsDAOImpl(DBConnection.getConnection());

		public static SettingsDAOImpl getInstance() {
			return INSTANCE;
		}
	}
}
