package com.cms.webpage.authentication

import org.springframework.security.core.Authentication

interface AuthenticationFacade {
    fun authentication(): Authentication
}