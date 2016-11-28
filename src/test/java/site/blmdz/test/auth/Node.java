package site.blmdz.test.auth;

import java.util.Map;

import lombok.Data;

@Data
public class Node {
	private String name;
	private String resources;
	private Map<String, Node> children;

	public Node(String name, String resources) {
		this.name = name;
		this.resources = resources;
	}
	public Node(){}
}
