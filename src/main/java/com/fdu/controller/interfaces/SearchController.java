package com.fdu.controller.interfaces;

import java.util.List;

import com.fdu.controller.impl.SearchControllerImpl;
import com.fdu.model.User;

public interface SearchController {

	List<User> searchUsers(String searchText);

	List<User> searchLabAssistants(String searchText);

	class Factory {
		private static final SearchControllerImpl INSTANCE = new SearchControllerImpl();

		public static SearchControllerImpl getInstance() {
			return INSTANCE;
		}
	}
}
