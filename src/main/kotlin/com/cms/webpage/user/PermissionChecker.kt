package com.cms.webpage.user

import com.cms.webpage.authentication.AuthenticationFacade
import com.cms.webpage.authentication.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class PermissionChecker (
    private val authenticationFacade: AuthenticationFacade,
    private val userRepository: UserRepository
){

    fun getCurrentLoggedUser(): User {
        val principal = authenticationFacade.authentication().principal
        if (principal is UserPrincipal) {
            return userRepository.findByLogin(principal.username).get()
        }
        throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
    }

    fun hasPermissionOrThrow(vararg permissions: Permission){
        if(!hasPermission(permissions)){
            throw ResponseStatusException(HttpStatus.FORBIDDEN,"You dont have permission")
        }
    }

    fun hasAuthorityOrThrow(vararg systemRoles: SystemRole){
        if(!hasAuthority(systemRoles)){
            throw ResponseStatusException(HttpStatus.FORBIDDEN,"You dont have role")
        }
    }

    private fun hasPermission(permissions: Array<out Permission>):Boolean{
        return getCurrentLoggedUser().systemRoles
            .flatMap{ it.permissions }
            .containsAll(permissions.toList())
    }

    private fun hasAuthority(systemRoles: Array<out SystemRole>):Boolean{
        return getCurrentLoggedUser().systemRoles
            .containsAll(systemRoles.toList())
    }

}