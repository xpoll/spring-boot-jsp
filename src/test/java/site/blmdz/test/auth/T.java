package site.blmdz.test.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import site.blmdz.auth.AuthUtils;

public class T {
	static final ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws JsonProcessingException {
		System.out.println(mapper.writeValueAsString(AuthUtils.readAuths()));
		System.out.println(mapper.writeValueAsString(AuthUtils.readRolesAuths()));
		System.out.println(mapper.writeValueAsString(AuthUtils.readAuthsTreeMap()));
		System.out.println(mapper.writeValueAsString(AuthUtils.readAuthsTree("admin")));
		
	}
}
