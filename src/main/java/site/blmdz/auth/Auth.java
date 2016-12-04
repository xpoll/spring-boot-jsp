package site.blmdz.auth;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class Auth implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Set<String> requests;
	private Set<String> resources;
}