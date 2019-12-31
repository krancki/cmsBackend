package com.cms.webpage.configuration

import com.cms.webpage.authentication.AuthenticationFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@ComponentScan
@EnableWebSecurity
class SecurityConfig(

    @Autowired
    private val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }

    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()
            ?.antMatchers("/*")?.authenticated()
            ?.antMatchers(HttpMethod.POST,"/api/user")?.permitAll()
            ?.antMatchers(HttpMethod.GET,"/api/website")?.permitAll()
            ?.and()
            ?.formLogin()?.loginPage("/login")?.permitAll()
            ?.and()
            ?.logout()?.permitAll()?.logoutSuccessUrl("/")
            ?.and()
            ?.httpBasic()
            ?.and()
            ?.csrf()?.disable()
    }

    @Bean
    fun authenticationFacade(): AuthenticationFacade = object :
    AuthenticationFacade{
        override fun authentication(): Authentication = SecurityContextHolder.getContext().authentication

    }
}