package com.fdu.controller.interfaces;

import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.User;

public interface UserController {

	ComputingServicesResponse<User> get();

	ComputingServicesResponse<Void> blockUser(String id);

	ComputingServicesResponse<Void> unblockUser(String id);

	ComputingServicesResponse<Void> deleteUser(String id);

	ComputingServicesResponse<Void> addUser(String user);

}
