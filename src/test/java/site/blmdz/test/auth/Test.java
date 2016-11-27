package site.blmdz.test.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Resources;

public class Test {
	
	public static void main(String[] args) throws FileNotFoundException {
		Auths a = new Yaml().loadAs(new FileInputStream(new File(Resources.getResource("auth.yaml").getFile())), Auths.class);
		System.out.println(a);
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
		
		System.out.println("================tree================");
		System.out.println(a.getTree().keySet());
//		Map<String, Node> map = Maps.newHashMap();
//		a.getTree().keySet().forEach(item -> {
//			//角色
//			map.putAll(a.getTree().get(item));
//			a.getTree().get(item).keySet().forEach(key -> {
//				build(map, a.getTree().get(item));
//			});
//		});
//		System.out.println(map);
	}
//	public static Map<String, Node> build(Map<String, Node> mapnew, Map<String, Node> mapold) {
//		mapold.keySet().forEach(item -> {
//			mapnew.putAll(mapold);
//			if (!mapold.get(item).getChildren().isEmpty())
//				build(mapnew, mapold);
//		});
//		return mapnew;
//	}
//	public static Map<String>
}
//auth Map<String, Map<String, Node>> tree;
//node
//
//private String name;
//private String resources;
//private Map<String, Node> children;
//tree:
//	  admin:
//	    admin_one:
//	      name: 管理员菜单栏A
//	      resources: /admin/one
//	      children:
//	        admin_one_one:
//	          name: 管理员菜单栏A_A
//	          resources: /admin/one/one
//	    admin_two:
//	      name: 管理员菜单栏B
//	      resources: /admin/two
//	      children:
//	        admin_two_two:
//	          name: 管理员菜单栏B_B
//	          resources: /admin/two/two
//	          children:
//	            admin_two_two_two:
//	              name: 管理员菜单栏B_B_B
//	              resources: /admin/two/two/two
