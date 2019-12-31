package com.cms.webpage.user

import com.cms.webpage.user.dto.UserDTO
import org.springframework.stereotype.Component

@Component
class UserMapper {

    fun mapUserToUserDTO(user: User) =
        UserDTO(id = user.id, isEnable = user.isEnable, login = user.login, username = user.username, systemRole = user.systemRoles)
}