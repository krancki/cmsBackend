package com.cms.webpage.user

enum class SystemRole(
    val label: String,
    val permissions: List<Permission> = emptyList()
) {
    USER("User", listOf(
        Permission.VIEW_PRODUCT
    )),
    MODERATOR("Moderator", listOf(
        Permission.VIEW_PRODUCT,
        Permission.ADD_PRODUCT
    )),
    ADMIN("Admin", listOf(
        Permission.VIEW_PRODUCT,
        Permission.MODIFY_PRODUCT,
        Permission.MANAGE_USER
    )),
    SUPER_ADMIN("Super admin", listOf(*Permission.values())),
}