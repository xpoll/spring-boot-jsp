package site.blmdz.model;

import java.util.Date;

import lombok.Data;
import site.blmdz.enums.UserStatus;

@Data
public class User {
    private Long id;

    private String username;

    private String password;

    private String roles;
    
    /**
     * {@link UserStatus}
     */
    private Integer status;

    private String name;

    private Integer age;

    private Date insertTime;

    private Date updateTime;
}