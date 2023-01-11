package com.passwordmanager.controllers

import com.passwordmanager.common.UserCreateRequest
import com.passwordmanager.common.UserRequest
import com.passwordmanager.service.IUserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/users"])
class UsersController(private val userService: IUserService) {

    @GetMapping("{userId}")
    suspend fun getUserById(@PathVariable userId: String) = userService.getUser(userId)

    @PostMapping("/")
    suspend fun createUser(@RequestBody user: UserCreateRequest) = userService.createUser(user)

    @PatchMapping("{userId}")
    suspend fun patchUserById(@RequestBody user: UserCreateRequest) = userService.modifyUser(user)

    @GetMapping("{userId}/setting/password-duration")
    suspend fun getPasswordDuration(@PathVariable userId:String) = userService.getSettingPasswordDuration(userId)

    @GetMapping("/credentials/{userId}")
    suspend fun getCredentialsOfUser(@PathVariable userId: String) = userService.getCredentials(userId)
}