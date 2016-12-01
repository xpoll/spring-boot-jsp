package site.blmdz.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;

import site.blmdz.JspApplication;
import site.blmdz.model.User;
import site.blmdz.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JspApplication.class)
@WebAppConfiguration
@Transactional
@Rollback
@ActiveProfiles("dev")
public class TestServicesTest {

	@Autowired UserService userService;

//	@Test
	public void adduser() {
		for (int i = 0; i < 100; i++) {
			User user = new User();
			user.setAge(12 + i);
			user.setName("name" + i);
			user.setPassword("password" + i);
			user.setUsername("username" + i);
			user.setRoles("roles");
			userService.create(user);
		}
	}
	@Test
	public void page() {
		int pageNum = 1;
		int pageSize = 10;
		Page<User> page = userService.findPage(pageNum, pageSize);
		page.forEach(item -> {
			System.out.println(item.toString());
		});
	}
}
