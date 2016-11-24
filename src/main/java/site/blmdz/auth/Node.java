package site.blmdz.auth;

import java.util.Map;
import java.util.Set;

import lombok.Data;

@Data
public class Node {
	private String name;
	private String resources;
	private Map<String, Set<Node>> children;

}
