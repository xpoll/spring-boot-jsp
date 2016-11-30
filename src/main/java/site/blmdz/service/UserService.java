package site.blmdz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import site.blmdz.dao.UserMapper;
import site.blmdz.model.User;

@Service
public class UserService {

	@Autowired UserMapper tUserMapper;
	
	public User findUser(Long id) {
		return tUserMapper.findById(id);
	}
	public Page<User> findAll() {
		PageHelper.startPage(1, 1);
		return tUserMapper.page();
	}
	@Transactional(
			propagation = Propagation.REQUIRED
			)
	public boolean create(User user) {
		int i = tUserMapper.create(user);
		return i == 1;
	}
}
