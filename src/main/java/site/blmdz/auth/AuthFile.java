package site.blmdz.auth;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class AuthFile implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Map<String, Auth> auths;
	private Map<String, Auth> roles;
	private Map<String, Map<String, Node>> tree;
}
