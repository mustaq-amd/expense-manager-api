package in.mustaq.expensetrackerapi.service;

import in.mustaq.expensetrackerapi.entity.User;
import in.mustaq.expensetrackerapi.entity.UserModel;

public interface UserService {

	public User createUser(UserModel user);

	public User readUser();

	public User updateUser(UserModel user);

	public void deleteUser();
	
	public User getLoggedInUser();

}
