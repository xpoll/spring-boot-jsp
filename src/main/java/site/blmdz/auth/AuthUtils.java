package site.blmdz.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Resources;

import lombok.extern.slf4j.Slf4j;

/**
 * 权限读取工具类
 * @author yangyz
 * @date 2016年12月2日下午5:16:51
 */
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
	 * tree
	 */
	public static Map<String, Map<String, Node>> readTrees() {
		AuthFile auth = readFile();
		return Objects.isNull(auth) ? null : auth.getTree();
	}
	/**
	 * roles
	 */
	public static Set<String> readRoles() {
		AuthFile auth = readFile();
		return Objects.isNull(auth) ? null : auth.getTree().keySet();
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
	 * auth tree Map
	 */
	public static Map<String, Node> readAuthsRolesTreeMap(String roles) {
		AuthFile auth = readFile();
		if (Objects.isNull(auth))
			return null;
		
		Map<String, Node> mapTree = Maps.newHashMap();
		build(mapTree, auth.getTree().get(roles));
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
	/**
	 * requests
	 */
	public static Set<String> readRequests() {
		Set<String> requests = Sets.newHashSet();
		Map<String, Auth> auth = AuthUtils.readAuths();
		auth.keySet().forEach(item -> {
			requests.addAll(auth.get(item).getRequests());
		});
		Map<String, Auth> roles_auth = AuthUtils.readRolesAuths();
		roles_auth.keySet().forEach(item -> {
			requests.addAll(roles_auth.get(item).getRequests());
		});
		Map<String, Node> auth_tree = AuthUtils.readAuthsTreeMap();
		auth_tree.keySet().forEach(item -> {
			requests.add(auth_tree.get(item).getResources());
		});
		return requests;
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
