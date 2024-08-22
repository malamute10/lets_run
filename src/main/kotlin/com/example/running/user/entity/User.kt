package com.example.running.user.entity

import com.example.running.common.entity.CreateDateTime
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault

@Entity
@Table(name = "user")
class User(

    id: Long = 0,

    @Column(name = "nickname", nullable = false, columnDefinition = "VARCHAR(128)")
    val nickname: String,

    @Column(name = "phone_number", nullable = false, columnDefinition = "CHAR(11)")
    val phoneNumber: String? = null,

    @ColumnDefault("1")
    @Column(name = "is_enabled", nullable = false, columnDefinition = "TINYINT(1)")
    val isEnabled: Boolean = true,

    @ColumnDefault("0")
    @Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1)")
    val isDeleted: Boolean = false
): CreateDateTime() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    val id = id

    constructor(id: Long): this(
        id = id,
        nickname = ""
    )
}