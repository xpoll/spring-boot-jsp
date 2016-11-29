package site.blmdz.test.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Resources;

public class Test {
	static final ObjectMapper mapper = new ObjectMapper();
	static {
	}
	
	public static void main(String[] args) throws FileNotFoundException, JsonProcessingException {
		AuthFile a = new Yaml().loadAs(new FileInputStream(new File(Resources.getResource("auth.yaml").getFile())), AuthFile.class);
		
		System.out.println(mapper.writeValueAsString(a));
		
		System.out.println("========权限========");
		Set<String> auths = Sets.newConcurrentHashSet(a.getAuths().keySet());
		auths.addAll(a.getRoles().keySet());
		System.out.println(auths);
		
		System.out.println("========权限URL========");
		Set<String> requests = Sets.newHashSet();
		a.getAuths().keySet().forEach(item -> {
			requests.addAll(a.getAuths().get(item).getRequests());
		});
		a.getRoles().keySet().forEach(item -> {
			requests.addAll(a.getRoles().get(item).getRequests());
		});
		System.out.println(requests);
		
		System.out.println("========资源key========");
		Set<String> resources = Sets.newHashSet();
		a.getRoles().keySet().forEach(item -> {
			resources.addAll(a.getRoles().get(item).getResources());
		});
		System.out.println(resources);
		
		System.out.println("================treeList================");

		List<Node> list = Lists.newArrayList();
		a.getTree().keySet().forEach(role_key -> {
			buildList(list, a.getTree().get(role_key));
		});
		System.out.println(mapper.writeValueAsString(list));
		
		System.out.println("================tree================");
		Map<String, Node> mapTree = Maps.newHashMap();
		a.getTree().keySet().forEach(role_key -> {
			build(mapTree, a.getTree().get(role_key));
		});
		System.out.println(mapper.writeValueAsString(mapTree));
	}
	public static void build(Map<String, Node> mapTree, Map<String, Node> map) {
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
	public static void buildList(List<Node> list, Map<String, Node> map) {
		map.keySet().forEach(auth_key -> {
			Node node = null;
			try {
				node = new Yaml().loadAs(mapper.writeValueAsString(map.get(auth_key)), Node.class);
			} catch (JsonProcessingException e) {
				return ;
			}
			list.add(new Node(node.getName(), node.getResources()));
			if (Objects.nonNull(node.getChildren()))
				buildList(list, node.getChildren());
		});
	}
}
