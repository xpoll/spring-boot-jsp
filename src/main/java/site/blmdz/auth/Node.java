package site.blmdz.auth;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class Node implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String resources;
	private Map<String, Node> children;

	public Node(String name, String resources) {
		this.name = name;
		this.resources = resources;
	}
	public Node(){}
}
