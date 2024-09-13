package com.example.running.domain.avatar.entity

import com.example.running.common.entity.CreateDateTime
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Item(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_type_id", nullable = false, columnDefinition = "TINYINT UNSIGNED", referencedColumnName = "id")
    val itemType: ItemType,

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(128)")
    val name: String,

    @Column(name = "file_path", nullable = false, columnDefinition = "VARCHAR(64)")
    val filePath: String

): CreateDateTime()