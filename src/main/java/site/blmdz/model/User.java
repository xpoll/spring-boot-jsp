package site.blmdz.model;

import java.util.Date;
import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import site.blmdz.enums.UserStatus;

@Data
public class User {
    private Long id;

    private String username;

    private String password;

    @Setter(AccessLevel.NONE)
    private String roles;
    
    @Setter(AccessLevel.NONE)
    private Set<String> roleSet;

	/**
     * {@link UserStatus}
     */
    private Integer status;

    private String name;

    private Integer age;

    private Date insertTime;

    private Date updateTime;
    
    
    public void setRoles(String roles) {
		this.roles = roles.toLowerCase();
		this.roleSet = Sets.newHashSet(Splitter.on(",").trimResults().splitToList(roles.toLowerCase()));
	}

	public void setRoleSet(Set<String> roleSet) {
		this.roleSet = roleSet;
		this.roles = Joiner.on(",").join(roleSet).toLowerCase();
	}
}