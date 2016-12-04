package site.blmdz.test.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;

import site.blmdz.auth.AuthUtils;

public class T {
	static final ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws JsonProcessingException {
		System.out.print("01");
		System.out.println(mapper.writeValueAsString(AuthUtils.readAuths()));
		System.out.print("02");
		System.out.println(mapper.writeValueAsString(AuthUtils.readRolesAuths()));
		System.out.print("03");
		System.out.println(mapper.writeValueAsString(AuthUtils.readAuthsTreeMap()));
		System.out.print("04");
		System.out.println(mapper.writeValueAsString(AuthUtils.readAuthsTree("admin")));
		System.out.print("05");
		System.out.println(mapper.writeValueAsString(AuthUtils.readTrees()));
		System.out.print("06");
		System.out.println(mapper.writeValueAsString(AuthUtils.readRoles()));
		System.out.print("07");
		System.out.println(mapper.writeValueAsString(AuthUtils.readAuthsRolesTreeMap("admin")));
		System.out.print("08");
		System.out.println(mapper.writeValueAsString(AuthUtils.readRequests()));
		System.out.print("09");
		System.out.println(mapper.writeValueAsString(AuthUtils.readAuthsRolesTreeMap("admin, normal")));
		
		String sequence = " 234 ,   324 ";
		Splitter.on(",").trimResults().splitToList(sequence).forEach(item -> {
			System.out.println("A" + item + "A");
		});
	}
}
