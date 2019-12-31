package com.cms.webpage.user

import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Transactional
@Service
class UserService(
    private val userRepository: UserRepository,
    private val permissionChecker: PermissionChecker,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun getUserById(id: Long): User {
        permissionChecker.hasPermissionOrThrow(Permission.MANAGE_USER)
        return userRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User not exist")
        }
    }

    fun getUserByLogin(login: String): User {
        permissionChecker.hasPermissionOrThrow(Permission.MANAGE_USER)
        return userRepository.findByLogin(login).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User not exist")
        }
    }

    fun createUser(userName: String, login: String, password: String): User {

        userRepository.findByLogin(login).ifPresent { throw ResponseStatusException(HttpStatus.CONFLICT, "User already exist") }
        return userRepository.save(User(isEnable = true,
            username = userName,
            login = login,
            password = passwordEncoder.encode(password),
            systemRoles = setOf(SystemRole.USER)))
    }

    fun changeUserRoles(userId: Long, systemRoles: Set<SystemRole>) {
        permissionChecker.hasPermissionOrThrow(Permission.MODIFY_PERMISSION)
        val user = userRepository.findById(userId).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
        user.systemRoles = systemRoles
    }


}
