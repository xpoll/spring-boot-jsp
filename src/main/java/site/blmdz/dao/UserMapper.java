package site.blmdz.dao;

import site.blmdz.model.User;

public interface UserMapper extends BaseDao<User> {
	
	User findByUserName(String username);
}