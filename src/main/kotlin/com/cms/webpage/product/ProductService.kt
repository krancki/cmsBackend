package com.cms.webpage.product

import com.cms.webpage.user.Permission
import com.cms.webpage.user.PermissionChecker
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val permissionChecker: PermissionChecker

) {
    fun getAllProduct(): List<Product> {
        permissionChecker.hasPermissionOrThrow(Permission.VIEW_PRODUCT)
        return productRepository.findAll()
    }

    fun getProduct(id: Long): Product {
        permissionChecker.hasPermissionOrThrow(Permission.VIEW_PRODUCT)
        return productRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found") }
    }

    fun createProduct(product: Product): Product {
        permissionChecker.hasPermissionOrThrow(Permission.ADD_PRODUCT)
        return productRepository.save(product)
    }

    fun editProduct( editProduct: Product) {
        permissionChecker.hasPermissionOrThrow(Permission.MODIFY_PRODUCT)
        productRepository.findById(editProduct.id!!).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
        productRepository.save(editProduct)
    }

    fun deleteProduct(id: Long) {
        permissionChecker.hasPermissionOrThrow(Permission.DELETE_PRODUCT)
        productRepository.findById(id).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
        productRepository.deleteById(id)
    }

}