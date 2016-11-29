package site.blmdz.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Objects;

import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthUtils {
	static final ObjectMapper mapper = new ObjectMapper();
	static {
	}

	/**
	 * shiro auth
	 */
	public static Map<String, Auth> readAuths() {
		AuthFile auth = readFile();
		return Objects.isNull(auth) ? null : auth.getAuths();
	}
	/**
	 * roles auth
	 */
	public static Map<String, Auth> readRolesAuths() {
		AuthFile auth = readFile();
		return Objects.isNull(auth) ? null : auth.getRoles();
	}
	/**
	 * auth tree Map
	 */
	public static Map<String, Node> readAuthsTreeMap() {
		AuthFile auth = readFile();
		if (Objects.isNull(auth))
			return null;
		
		Map<String, Node> mapTree = Maps.newHashMap();
		auth.getTree().keySet().forEach(role_key -> {
			build(mapTree, auth.getTree().get(role_key));
		});
		
		return mapTree;
	}
	/**
	 * auth tree 
	 */
	public static Map<String, Node> readAuthsTree(String role) {
		AuthFile auth = readFile();
		if (Objects.isNull(auth))
			return null;
		return auth.getTree().get(role);
	}

	public final static String FILE = "auth.yaml";
	protected static AuthFile readFile() {
		try {
			return new Yaml().loadAs(
					new FileInputStream(
							new File(
									Resources.getResource(FILE).getFile())),
									AuthFile.class);
		} catch (FileNotFoundException e) {
			log.error("auth file'config:{} not found." + FILE);
			return null;
		}
	}
	protected static void build(Map<String, Node> mapTree, Map<String, Node> map) {
		map.keySet().forEach(auth_key -> {
			Node node = null;
			try {
				node = new Yaml().loadAs(mapper.writeValueAsString(map.get(auth_key)), Node.class);
			} catch (JsonProcessingException e) {
				return ;
			}
			mapTree.put(auth_key, new Node(node.getName(), node.getResources()));
			if (Objects.nonNull(node.getChildren()))
				build(mapTree, node.getChildren());
		});
	}
	
	
}
