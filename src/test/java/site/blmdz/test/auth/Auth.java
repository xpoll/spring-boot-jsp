package site.blmdz.test.auth;

import java.util.Set;

import lombok.Data;

@Data
public class Auth {
	private Set<String> requests;
	private Set<String> resources;
}