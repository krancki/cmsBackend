package com.cms.webpage.user.dto

import com.cms.webpage.user.SystemRole

class UserDTO(
    val id: Long?,
    val isEnable: Boolean,
    val login: String,
    val username: String,
    val systemRole: Set<SystemRole>
)
