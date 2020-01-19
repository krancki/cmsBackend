package com.cms.webpage.configuration

import com.cms.webpage.authentication.AuthenticationFacade
import com.cms.webpage.authentication.CustomAuthenticationSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@ComponentScan
@EnableWebSecurity
class SecurityConfig(

    private val customAuthenticationSuccessHandler: CustomAuthenticationSuccessHandler,
    private val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:3000")
        configuration.allowedMethods = listOf("*")
        configuration.allowCredentials = true
        configuration.allowedHeaders = listOf("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
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
            ?.antMatchers(HttpMethod.POST, "/api/user")?.permitAll()
            ?.antMatchers(HttpMethod.GET, "/api/website")?.permitAll()
            ?.and()
            ?.formLogin()?.loginPage("/login")?.permitAll()
            ?.defaultSuccessUrl("/")
            ?.successHandler(customAuthenticationSuccessHandler)
            ?.and()
            ?.logout()?.permitAll()?.logoutSuccessUrl("/")
            ?.and()
            ?.httpBasic()
            ?.and()
            ?.cors()?.and()
            ?.csrf()?.disable()

    }

    @Bean
    fun authenticationFacade(): AuthenticationFacade = object :
        AuthenticationFacade {
        override fun authentication(): Authentication = SecurityContextHolder.getContext().authentication

    }
}