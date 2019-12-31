package com.cms.webpage.user

import com.cms.webpage.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByLogin(login: String): Optional<User>
}