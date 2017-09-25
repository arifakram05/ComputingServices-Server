package com.fdu.database.factory;

import com.fdu.database.dao.UserDAO;

public abstract class Factory {

	public abstract UserDAO getUser();

}
