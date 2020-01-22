package com.cms.webpage.product

import com.cms.webpage.product.dto.CreateProductDTO
import com.cms.webpage.product.dto.EditProductDTO
import com.cms.webpage.product.dto.ProductDTO
import org.springframework.stereotype.Component

@Component
class ProductMapper {

    fun mapProductToProductDTO(product: Product): ProductDTO {
        return ProductDTO(product.id, product.name, product.description, product.price)
    }

    fun mapDtoToProduct(createProductDTO: CreateProductDTO): Product{
        return Product(
            id = null,
            name = createProductDTO.name,
            description = createProductDTO.description,
            price = createProductDTO.price
        )
    }

    fun mapDtoToProduct(id:Long ,editProductDTO: EditProductDTO): Product{
        return Product(
            id = id,
            name = editProductDTO.name,
            description = editProductDTO.description,
            price = editProductDTO.price
        )
    }
}
