package com.fdu.controller.factory;

import com.fdu.controller.interfaces.UserController;

public abstract class Factory {

	public abstract UserController getUser();
}
