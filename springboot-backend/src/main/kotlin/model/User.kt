package com.example.springboot.model

import javax.persistence.*

@Table(name = "users")
@Entity
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "first_name", nullable = false)
    var firstName: String? = null,

    @Column(name = "last_name", nullable = false)
    var lastName: String? = null,

    @Column(name = "email", nullable = false)
    var email: String? = null
)