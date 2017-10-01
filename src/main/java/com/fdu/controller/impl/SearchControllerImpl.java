package com.fdu.controller.impl;

import java.util.List;

import com.fdu.controller.interfaces.SearchController;
import com.fdu.database.dao.SearchDAO;
import com.fdu.model.User;

public class SearchControllerImpl implements SearchController {

	@Override
	public List<User> searchUsers(String searchText) {
		List<User> users = SearchDAO.Factory.getInstance().getUsers(searchText);
		return users;
	}

	@Override
	public List<User> searchLabAssistants(String searchText) {
		List<User> labAssistants = SearchDAO.Factory.getInstance().getLabAssistants(searchText);
		return labAssistants;
	}

}
