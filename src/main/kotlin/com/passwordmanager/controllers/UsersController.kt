package com.passwordmanager.controllers

import com.passwordmanager.common.UserCreateRequest
import com.passwordmanager.common.UserRequest
import com.passwordmanager.service.IUserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/users"])
class UsersController(private val userService: IUserService) {

    @GetMapping(value = ["{userId}"])
    suspend fun getUserById(@PathVariable userId: String) = userService.getUser(userId)

    @PostMapping(value = ["/"])
    suspend fun createUser(@RequestBody user: UserCreateRequest) = userService.createUser(user)

    @PatchMapping(value = ["{userId}"])
    suspend fun patchUserById(@RequestBody user: UserRequest){
        userService.modifyUser(user)
    }
}