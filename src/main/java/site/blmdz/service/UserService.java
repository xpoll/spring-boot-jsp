package site.blmdz.service;

import java.util.List;

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

	public User findUserById(Long id) {
		return tUserMapper.findById(id);
	}
	public User findUserByUserName(String username) {
		return tUserMapper.findByUserName(username);
	}
	
	public List<User> findAll() {
		return tUserMapper.list();
	}
	public Page<User> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize, "id");
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
