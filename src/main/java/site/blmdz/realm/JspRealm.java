package site.blmdz.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.google.common.collect.Sets;

public class JspRealm extends AuthorizingRealm {

	//授权验证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection paramPrincipalCollection) {
		String username = (String) paramPrincipalCollection.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = Sets.newHashSet();
		Set<String> permissions = Sets.newHashSet();
		if ("admin".equals(username)) {
			roles.add("admin");
			permissions.add("admin_permissions");
		} else if ("normal".equals(username)) {
			roles.add("normal");
			permissions.add("normal_permissions");
		}
		info.setRoles(roles);
		info.setStringPermissions(permissions);
		System.out.println("-----------------------授权验证");
		return info;
	}

	//登录验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken paramAuthenticationToken) throws AuthenticationException {
		String username = (String) paramAuthenticationToken.getPrincipal();
		//账号是否存在、账户是否冻结等 抛异常处理
		if (!"admin".equals(username)
				&& !"normal".equals(username)
				&& !"locked".equals(username)
				&& !"disable".equals(username))
			return null;
		if ("locked".equals(username))
			throw new LockedAccountException();
		if ("disable".equals(username))
			throw new DisabledAccountException();
		System.out.println("-----------------------登陆验证");
		return new SimpleAuthenticationInfo(username, "111", username);
	}

}
