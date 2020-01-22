package com.cms.webpage.authentication

import com.cms.webpage.user.User
import lombok.extern.slf4j.Slf4j
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Slf4j
class UserPrincipal(
    private val user: User
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.systemRoles.map { SimpleGrantedAuthority(it.name) }.toMutableList()
    }

    override fun isEnabled(): Boolean {
        return user.isEnable
    }

    override fun getUsername(): String {
        return user.login
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

}