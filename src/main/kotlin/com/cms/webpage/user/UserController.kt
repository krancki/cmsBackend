package com.cms.webpage.user


import com.cms.webpage.user.dto.CreateUserDTO
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/user")
@RestController
class UserController(
    val userService: UserService,
    val userMapper: UserMapper,
    val permissionChecker: PermissionChecker
) {

    @GetMapping
    fun getCurrentUser() = userMapper.mapUserToUserDTO(permissionChecker.getCurrentLoggedUser())

    @GetMapping("{id}")
    fun getUser(
        @PathVariable("id") id: Long
    ) = userMapper.mapUserToUserDTO(
        userService.getUserById(id)
    )

    @PostMapping
    fun createUser(@RequestBody createUserDTO: CreateUserDTO) =
        userService.createUser(
            login = createUserDTO.login,
            userName = createUserDTO.username,
            password = createUserDTO.password)


    @PostMapping("/{id}/change-role")
    fun changeUserRole(
        @PathVariable("id") id: Long,
        @RequestBody systemRoles: Set<SystemRole>){
        userService.changeUserRoles(id, systemRoles)
    }
}