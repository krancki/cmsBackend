package com.cms.webpage

import com.cms.webpage.product.Product
import com.cms.webpage.product.ProductRepository
import com.cms.webpage.user.SystemRole
import com.cms.webpage.user.User
import com.cms.webpage.user.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.math.BigDecimal


@Component
@ConditionalOnProperty(name = ["app.db-init"], havingValue = "true")
class DbInitializer(
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder) : CommandLineRunner {
    override fun run(vararg args: String?) {

        createUsers()
        createProducts()
    }

    fun createUsers() {
        val password = bCryptPasswordEncoder.encode("xxxx")
        userRepository.save(User(null, "user", true, "xxxx", password, setOf(SystemRole.USER)))
        userRepository.save(User(null, "admin", true, "xxxx", password, setOf(SystemRole.ADMIN)))
        userRepository.save(User(null, "moderator", true, "xxxx", password, setOf(SystemRole.MODERATOR)))
        userRepository.save(User(null, "superAdmin", true, "xxxx", password, setOf(SystemRole.SUPER_ADMIN)))

    }

    fun createProducts() {

        productRepository.save(Product(null, "Głośnik", "Mały duży glośnik", BigDecimal.valueOf(120)))
        productRepository.save(Product(null, "Telewizior", "Telewizja polska", BigDecimal.valueOf(1299.99)))
        productRepository.save(Product(null, "Telefon", "Telefonik", BigDecimal.valueOf(99)))
        productRepository.save(Product(null, "Lampa", "Lambionek", BigDecimal.valueOf(60)))

    }
}