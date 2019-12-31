package com.cms.webpage

import com.cms.webpage.user.SystemRole
import com.cms.webpage.user.User
import com.cms.webpage.user.UserRepository
import com.cms.webpage.user.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component


@Component
@ConditionalOnProperty(name = ["app.db-init"], havingValue = "true")
class DbInitializer(
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder): CommandLineRunner {
    override fun run(vararg args: String?) {

       createUsers()
    }

    fun createUsers(){
        val password =  bCryptPasswordEncoder.encode("xxxx")
        userRepository.save(User(null,"user",true ,"xxxx", password,setOf(SystemRole.USER)))
        userRepository.save(User(null,"admin",true ,"xxxx", password,setOf(SystemRole.ADMIN)))
        userRepository.save(User(null,"moderator",true ,"xxxx", password,setOf(SystemRole.MODERATOR)))
        userRepository.save(User(null,"superAdmin",true ,"xxxx", password,setOf(SystemRole.SUPER_ADMIN)))

    }
}