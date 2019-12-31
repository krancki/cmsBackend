package com.cms.webpage.website

import javax.persistence.*

@Entity
data class Website(

    @Id
    val id: Long = 1L,
    @Enumerated(EnumType.STRING)
    val pageTheme: PageTheme = PageTheme.WHITE
)