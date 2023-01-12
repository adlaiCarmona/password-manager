package com.passwordmanager.services

import com.passwordmanager.common.UserCreateRequest
import com.passwordmanager.common.UserRequest
import com.passwordmanager.common.convert
import com.passwordmanager.repository.IUserRepository
import com.passwordmanager.service.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import reactor.core.publisher.Mono
import java.time.LocalDateTime

class UserServiceTest {

    @Mock
    lateinit var userRepository: IUserRepository

    @Mock
    lateinit var credentialService: CredentialService

    @Mock
    lateinit var settingService: SettingService

    lateinit var userService: UserService

    private val userId = "12345-678-90"
    private val usercreateRequest = UserCreateRequest("test@email.com", "pass123", "Test", "Name")
    private val userRequest = UserRequest("test@email.com", "pass123", "Test", "Name", "id", "", 123, false, LocalDateTime.now(), LocalDateTime.now(), 60)

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        userService = UserService(userRepository, credentialService, settingService)
    }

    @Test
    fun testCreateUser() {
        runBlocking {
            `when`(userRepository.save(any())).then { Mono.just(usercreateRequest.convert()) }
            val postUserResponse = userService.createUser(usercreateRequest)!!.block()

            println("Test Create User:\n response = $postUserResponse")

            assert(postUserResponse != null)
        }
    }

    @Test
    fun testUpdateUser() {
        runBlocking {
            `when`(userRepository.save(any())).then { Mono.just(usercreateRequest.convert()) }
            val response = userService.createUser(usercreateRequest)!!.block()

            println("Test Update User:\n response = $response")

            assert(response != null)
        }
    }

    @Test
    fun testDeleteUser() {
        runBlocking {
            `when`(userRepository.delete(any())).then { Mono.just(0) }
            val response = userService.deleteUser(userRequest)

            println("Test Delete User:\n response = $response")

            assert(response != null)
        }
    }

    @Test
    fun testDeleteUserById() {
        runBlocking {
            `when`(userRepository.deleteById(anyString())).then { Mono.just(0) }
            val response = userService.deleteUser(userRequest)

            println("Test Delete User By Id:\n response = $response")

            assert(response == 0)
        }
    }

    @Test
    fun testGetUser() {
        runBlocking {
            `when`(userRepository.findById(anyString())).then { Mono.just(usercreateRequest.convert()) }
            val getUserResponse = userService.getUser(userId)

            println("Test Get User:\n getUserResponse = $getUserResponse")

            assert(getUserResponse != null)
        }
    }

    @Test
    fun testGetUserCredentials() {
        runBlocking {
            `when`(credentialService.getCredentialsByUserIdEquals(anyString())).then { Mono.just(arrayListOf("credential")) }
            `when`(credentialService.getCredentialsByUserIdEquals2(anyString())).then { Mono.just(arrayListOf("credential")) }
            `when`(credentialService.getCredentialsByUserIdEquals3(anyString())).then { Mono.just(arrayListOf("credential")) }
            val getUserResponse = userService.getCredentials(userId)

            println("Test Get User:\n getUserResponse = $getUserResponse")

            assert(getUserResponse != null)
        }
    }

    @Test
    fun testGetUserPasswordDuration() {
        runBlocking {
            `when`(userRepository.queryUserByIdEquals(anyString())).then {  }
            val response = userService.getSettingPasswordDuration(userId)

            println("Test Get User Password Duration:\n response = $response")

            assert(response != null)
        }
    }
}