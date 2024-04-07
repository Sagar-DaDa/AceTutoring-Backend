package com.acetutoring.api.enums;

public enum UserRoleEnum {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_OPERATOR("ROLE_OPERATOR"),
    ROLE_CUSTOMER("ROLE_CUSTOMER"),
    ROLE_TUTOR("ROLE_TUTOR");

    private final String roleName;

    UserRoleEnum(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
