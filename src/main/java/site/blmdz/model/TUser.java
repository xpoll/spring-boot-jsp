package site.blmdz.model;

import java.util.Date;

import lombok.Data;

@Data
public class TUser {
    private Long id;

    private String username;

    private String password;

    private String roles;

    private String name;

    private Integer age;

    private Date insertTime;

    private Date updateTime;
}