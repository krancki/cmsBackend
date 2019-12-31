package com.cms.webpage.authentication

import com.cms.webpage.user.UserRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Slf4j
@Service
class MyUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        val user = userRepository.findByLogin(login).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User not exist") }
        return UserPrincipal(user)
    }
}