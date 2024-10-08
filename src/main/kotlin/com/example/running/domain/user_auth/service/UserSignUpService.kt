package com.example.running.user.service

import com.example.running.exception.ApiError
import com.example.running.security.service.TokenService
import com.example.running.user.controller.dto.TokenResponse
import com.example.running.user.entity.UserAccount
import com.example.running.user.service.dto.OAuthAccountInfo
import com.example.running.user.service.dto.UserCreationDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserSignUpService(
    private val userService: UserService,
    private val userAccountService: UserAccountService,
    private val tokenService: TokenService
) {

    @Transactional(rollbackFor = [Exception::class])
    fun signup(oAuthAccountInfo: OAuthAccountInfo): TokenResponse {
        return (userAccountService.getByEmail(oAuthAccountInfo.email)
            ?: createUserAndGetUserAccount(oAuthAccountInfo))
            .run {
                tokenService.generateTokens(this.user.id, this.email, this.user.authorityType)
            }
    }

    private fun createUserAndGetUserAccount(oAuthAccountInfo: OAuthAccountInfo): UserAccount {
        return oAuthAccountInfo.let {
            UserCreationDto(
                email = it.email,
                nickname = it.name
            )
        }.let {
            userService.save(it)
            userAccountService.getByEmail(it.email)
                ?: run { throw RuntimeException(ApiError.NOT_FOUND_USER_ACCOUNT.message) }
        }
    }
}