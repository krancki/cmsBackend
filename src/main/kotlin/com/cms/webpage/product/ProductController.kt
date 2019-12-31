package com.cms.webpage.product

import com.cms.webpage.product.dto.CreateProductDTO
import com.cms.webpage.product.dto.EditProductDTO
import com.cms.webpage.product.dto.ProductDTO
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/product")
@RestController
class ProductController(
    val productService: ProductService,
    val productMapper: ProductMapper
) {

    @GetMapping
    fun getAllProduct(): List<ProductDTO> {
        return productService.getAllProduct().map { productMapper.mapProductToProductDTO(it) }
    }

    @GetMapping("/{id}")
    fun getProduct(
        @PathVariable("id") id: Long
    ): ProductDTO {
        return productMapper.mapProductToProductDTO(productService.getProduct(id))
    }

    @PostMapping
    fun createProduct(
        @RequestBody createProductDTO: CreateProductDTO
    ) {
        productService.createProduct(productMapper.mapDtoToProduct(createProductDTO))
    }

    @PutMapping("/{id}")
    fun editProduct(
        @PathVariable("id") id: Long,
        @RequestBody editProductDTO: EditProductDTO
    ) {
        productService.editProduct(productMapper.mapDtoToProduct(id, editProductDTO))
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(
        @PathVariable("id") id: Long
    ) {
        productService.deleteProduct(id)
    }

}