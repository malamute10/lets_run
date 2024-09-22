package com.example.running.domain.avatar.controller


import com.example.running.domain.avatar.controller.dto.AvatarResponse
import com.example.running.domain.avatar.controller.dto.PutAvatarRequest
import com.example.running.domain.avatar.service.AvatarService
import com.example.running.utils.JwtPayloadParser
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/avatars")
@RestController
class AvatarController(
    private val avatarService: AvatarService
) {

    @GetMapping("/main")
    fun getMain(): AvatarResponse {

        return AvatarResponse(
            avatarService.getMainAvatar(JwtPayloadParser.getUserId())
        )
    }

    @PutMapping("/{id}")
    fun put(@PathVariable id: Long, @RequestBody request: PutAvatarRequest): AvatarResponse {

        avatarService.verifyAvatarExists(
            userId = JwtPayloadParser.getUserId(),
            avatarId = id
        )

        return AvatarResponse(
            avatarService.put(id, request.itemIds)
        )
    }
}