package site.blmdz.realm;

import java.util.Objects;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import site.blmdz.auth.AuthUtils;
import site.blmdz.enums.ErrorEnums;
import site.blmdz.enums.UserStatus;
import site.blmdz.exception.AuthenticationJspException;
import site.blmdz.model.JspUser;
import site.blmdz.model.User;
import site.blmdz.service.UserService;

/**
 * extends AuthorizingRealm
 * 登录验证 和 授权验证
 * @author yangyz
 * @date 2016年12月2日下午5:09:30
 */
@Slf4j
public class JspRealm extends AuthorizingRealm {
	
	@Autowired UserService userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken paramAuthenticationToken) {
		
		String username = (String) paramAuthenticationToken.getPrincipal();
		log.debug("-----------------------登陆验证:{}", username);
		
		User user = userService.findUserByUserName(username);
		JspUser jspUser = new JspUser();
		BeanUtils.copyProperties(user, jspUser);
		if (Objects.isNull(user))
			throw new AuthenticationJspException(ErrorEnums.ERROR_000004);
		if (Objects.equals(UserStatus.NORMAL.value(), user.getStatus()))
			;
		else if (Objects.equals(UserStatus.NOT_ACTIVATE.value(), user.getStatus()))
			throw new AuthenticationJspException(ErrorEnums.ERROR_000008);
		else if (Objects.equals(UserStatus.LOCKED.value(), user.getStatus()))
			throw new AuthenticationJspException(ErrorEnums.ERROR_000005);
		else if (Objects.equals(UserStatus.FROZEN.value(), user.getStatus()))
			throw new AuthenticationJspException(ErrorEnums.ERROR_000006);
		else
			throw new AuthenticationJspException(ErrorEnums.ERROR_000010);
		
		return new SimpleAuthenticationInfo(jspUser, user.getPassword(), user.getName());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection paramPrincipalCollection) {
		
		JspUser user = (JspUser) paramPrincipalCollection.getPrimaryPrincipal();
		log.debug("-----------------------授权验证:{}", user.getUsername());
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> permissions = AuthUtils.readAuthsRolesTreeMap(user.getRoles()).keySet();
		log.debug("{} 's roles:{}", user.getUsername(), user.getRoleSet());
		log.debug("{} 's permissions:{}", user.getUsername(), permissions);
		info.setRoles(user.getRoleSet());
		info.setStringPermissions(permissions);
		return info;
	}
}
