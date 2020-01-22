package com.cms.webpage.website

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
data class Website(

    @Id
    val id: Long = 1L,
    @Enumerated(EnumType.STRING)
    val pageTheme: PageTheme = PageTheme.WHITE
)