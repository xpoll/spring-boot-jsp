package site.blmdz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.blmdz.dao.TUserMapper;
import site.blmdz.model.TUser;

@Service
public class UserService {

	@Autowired TUserMapper tUserMapper;
	
	public TUser findUser(Long id) {
		return tUserMapper.findById(id);
	}
	public List<TUser> findAll() {
		return tUserMapper.list();
	}
}
