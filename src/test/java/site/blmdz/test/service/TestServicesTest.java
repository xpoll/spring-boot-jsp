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
	
	@Test
	public void user() {
		User user = new User();
		user.setAge(12);
		user.setName("li");
		user.setPassword("fefe");
		user.setUsername("efefe");
		user.setRoles("roles");
		userService.create(user);
		System.out.println(user);
	}
}
