package com.example.running.user.service.dto

import com.example.running.user.controller.dto.UserCreationRequest

class UserCreationDto(
    val email: String,
    val password: String? = null,
    val nickname: String
) {
    constructor(userCreationRequest: UserCreationRequest): this(
        email = userCreationRequest.email,
        password = userCreationRequest.password,
        nickname = userCreationRequest.nickname
    )
}