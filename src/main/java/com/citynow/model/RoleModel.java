package com.citynow.model;


/**
 * Represents Role of user
 * @author VuKhanh
 */
public class RoleModel {

    private Long id;

    /**
     * Represent name of role (ADMIN, USER,...)
     */
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
