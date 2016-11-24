package site.blmdz.auth;

import java.util.Map;

import lombok.Data;

@Data
public class Auths {
	private Map<String, Auth> auths;
	private Map<String, Auth> roles;
	private Map<String, Map<String, Node>> tree;
}