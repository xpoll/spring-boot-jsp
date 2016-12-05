package site.blmdz.model;

import java.util.Set;

import lombok.Data;
import site.blmdz.enums.UserStatus;

@Data
public class JspUser {
    private Long id;

    private String username;

    private String roles;
    
    private Set<String> roleSet;

	/**
     * {@link UserStatus}
     */
    private Integer status;

    private String name;

    private Integer age;
}
