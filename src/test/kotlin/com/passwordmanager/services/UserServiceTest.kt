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
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doThrow
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

    @InjectMocks
    lateinit var userService: UserService

    private val userId = "12345-678-90"
    private val usercreateRequest = UserCreateRequest("test@email.com", "pass123", "Test", "Name")
    private val userRequest = UserRequest(
        "test@email.com",
        "pass123",
        "Test",
        "Name",
        "id",
        "",
        123,
        false,
        LocalDateTime.now(),
        LocalDateTime.now(),
        60
    )

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testCreateUser() {
        runBlocking {
            `when`(userRepository.save(any())).then { Mono.just(usercreateRequest.convert()) }
            val response = userService.createUser(usercreateRequest)!!.block()

            println("Test Create User:\n response = $response")

            assert(response != null)
        }
    }

    @Test
    fun testCreateUserInvalidRequest() {
        runBlocking {
            doThrow(IllegalArgumentException()).`when`(userRepository).save(any())
            val response = userService.createUser(usercreateRequest)

            println("Test Create User:\n response = $response")

            assert(response == null)
        }
    }

    @Test
    fun testUpdateUser() {
        runBlocking {
            `when`(userRepository.save(any())).then { Mono.just(usercreateRequest.convert()) }
            val response = userService.modifyUser(usercreateRequest)!!.block()

            println("Test Update User:\n response = $response")

            assert(response != null)
        }
    }

    @Test
    fun testUpdateUserInvalid() {
        runBlocking {
            doThrow(IllegalArgumentException()).`when`(userRepository).save(any())
            val response = userService.modifyUser(usercreateRequest)

            println("Test Update User:\n response = $response")

            assert(response == null)
        }
    }

    @Test
    fun testDeleteUser() {
        runBlocking {
            `when`(userRepository.delete(any())).then { Mono.just(0) }
            val response = userService.deleteUser(userRequest)

            println("Test Delete User:\n response = $response")

            assert(response == 0)
        }
    }

    @Test
    fun testDeleteUserInvalidUser() {
        runBlocking {
            doThrow(IllegalArgumentException()).`when`(userRepository).delete(any())
            val response = userService.deleteUser(userRequest)

            println("Test Delete User:\n response = $response")

            assert(response == 1)
        }
    }

    @Test
    fun testDeleteUserById() {
        runBlocking {
            `when`(userRepository.deleteById(anyString())).then { Mono.just(0) }
            val response = userService.deleteUserById(userRequest.id!!)

            println("Test Delete User By Id:\n response = $response")

            assert(response == 0)
        }
    }

    @Test
    fun testDeleteUserByIdInvalid() {
        runBlocking {
            doThrow(IllegalArgumentException()).`when`(userRepository).deleteById(anyString())
            val response = userService.deleteUserById(userRequest.id!!)

            println("Test Delete User By Id:\n response = $response")

            assert(response == 1)
        }
    }

    @Test
    fun testGetUser() {
        runBlocking {
            `when`(userRepository.findById(anyString())).then { Mono.just(usercreateRequest.convert()) }
            val response = userService.getUser(userId)

            println("Test Get User:\n getUserResponse = $response")

            assert(response != null)
        }

    }

    @Test
    fun testGetUserInvalid() {
        runBlocking {
            doThrow(IllegalArgumentException()).`when`(userRepository).findById(anyString())
            val response = userService.getUser("invalid")

            println("Test Get User:\n getUserResponse = $response")

            assert(response == null)
        }
    }

    @Test
    fun testGetUserCredentials() {
        runBlocking {
            `when`(credentialService.getCredentialsByUserIdEquals(anyString())).then { Mono.just(arrayListOf("credential")) }
            val response = userService.getCredentials(userId)

            println("Test Get User:\n getUserResponse = $response")

            assert(response != null)
        }
    }

    @Test
    fun testGetUserCredentialsInvalid() {
        runBlocking {
            doThrow(IllegalArgumentException()).`when`(credentialService).getCredentialsByUserIdEquals(anyString())
            val response = userService.getCredentials("invalid")

            println("Test Get User:\n getUserResponse = $response")

            assert(response == null)
        }
    }

    @Test
    fun testGetUserPasswordDuration() {
        runBlocking {
            `when`(userRepository.findById(anyString())).then { Mono.just(usercreateRequest.convert()) }
            val response = userService.getSettingPasswordDuration(userId)

            println("Test Get User Password Duration:\n response = $response")

            assert(response != null)
        }
    }

    @Test
    fun testGetUserPasswordDurationInvalid() {
        runBlocking {
            doThrow(IllegalArgumentException()).`when`(userRepository).findById(anyString())
            val response = userService.getSettingPasswordDuration("invalid")

            println("Test Get User Password Duration:\n response = $response")

            assert(response == null)
        }
    }
}