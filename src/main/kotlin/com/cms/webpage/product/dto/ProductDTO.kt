package com.cms.webpage.product.dto

import java.math.BigDecimal

data class ProductDTO(
    val name: String,
    val description: String,
    val price: BigDecimal
)