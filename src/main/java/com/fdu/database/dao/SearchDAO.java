package com.fdu.database.dao;

import java.util.List;

import com.fdu.database.DBConnection;
import com.fdu.database.daoimpl.SearchDAOImpl;
import com.fdu.model.User;

public interface SearchDAO {

	List<User> getUsers(String searchText);

	List<User> getLabAssistants(String searchText);
	
	class Factory {
		private static final SearchDAOImpl INSTANCE = new SearchDAOImpl(DBConnection.getConnection());

		public static SearchDAOImpl getInstance() {
			return INSTANCE;
		}
	}
}
