package com.passwordmanager.controllers

import com.passwordmanager.common.UserCreateRequest
import com.passwordmanager.common.UserRequest
import com.passwordmanager.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping(value = ["/api/users"])
class UsersController() {

    @Autowired
    lateinit var userService: IUserService

    @GetMapping("{userId}")
    suspend fun getUserById(@PathVariable userId: String) = userService.getUser(userId)

    @GetMapping("email/{email}")
    suspend fun getUserByEmail(@PathVariable email: String) = userService.getUserByEmail(email)

    @GetMapping("userid/{email}")
    suspend fun getUserIdByEmail(@PathVariable email: String) = userService.getUserId(email)

    @PostMapping("/")
    suspend fun createUser(@RequestBody user: UserCreateRequest) = userService.createUser(user)

    @PatchMapping("/")
    suspend fun patchUser(@RequestBody user: UserRequest) = userService.modifyUser(user)

    @PatchMapping("{userId}")
    suspend fun patchUserById(@PathVariable userId:String, @RequestBody user: UserRequest) = userService.modifyUserById(userId, user)

    @GetMapping("{userId}/setting/password-duration")
    suspend fun getPasswordDuration(@PathVariable userId:String) = userService.getSettingPasswordDuration(userId)

    @GetMapping("/credentials/{userId}")
    suspend fun getCredentialsOfUser(@PathVariable userId: String) = userService.getCredentials(userId)

    @GetMapping("authenticated/name", produces = [MediaType.TEXT_PLAIN_VALUE])
    suspend fun getName(principal: Principal): String = principal.name

    @GetMapping("authenticated/all")
    suspend fun getPrincipal(principal: Principal) = principal
}