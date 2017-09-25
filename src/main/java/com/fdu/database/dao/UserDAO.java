package com.fdu.database.dao;

import java.util.List;

import com.fdu.model.User;

public interface UserDAO {

	List<User> get();

	boolean blockUser(String id);

	boolean unblockUser(String id);

	boolean deleteUser(String id);

	void addUser(User user);

}
