package com.cms.webpage.user

import javax.persistence.*

@Entity
@Table(name = "app_user")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(unique = true)
    var login: String = "",

    val isEnable: Boolean = true,
    var username: String = "",
    var password: String = "",

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER, targetClass = SystemRole::class)
    @CollectionTable(name = "app_user_roles", joinColumns = [JoinColumn(name = "user_id")])
    @Column(name = "role", nullable = false)
    var systemRoles: Set<SystemRole> = emptySet()
)