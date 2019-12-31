package com.cms.webpage.user

enum class Permission(val description: String) {
    VIEW_PRODUCT("View product"),
    DELETE_PRODUCT("Delete product"),
    MODIFY_PRODUCT("Modify products"),
    ADD_PRODUCT("Add new product"),
    MODIFY_PERMISSION("Add permission for other user"),
    MANAGE_USER("Can manage users"),
    TEMPLATE_CHANGE("Can change template"),

}