package site.blmdz.test;

import java.util.Map;
import java.util.Set;

import lombok.Data;

@Data
public class Auths {
	private Map<String, Set<String>> auths;
	private Map<String, Set<String>> roles;
	private Map<String, Set<String>> perms;
}
