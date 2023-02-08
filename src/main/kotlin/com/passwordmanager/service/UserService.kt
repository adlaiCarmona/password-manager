package com.passwordmanager.service

import com.passwordmanager.common.*
import com.passwordmanager.domain.Credential
import com.passwordmanager.domain.Setting
import com.passwordmanager.domain.User
import com.passwordmanager.repository.IUserRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.Flux

@Service
class UserService(): IUserService {

    @Autowired
    lateinit var userRepository: IUserRepository
    @Autowired
    lateinit var credentialService: CredentialService
    @Autowired
    lateinit var settingService: SettingService

    private val logger = KotlinLogging.logger {  }

    override suspend fun createUser(user: UserCreateRequest): Mono<User>? {

        try {
            return userRepository.save( user.convert() )
        } catch ( e: IllegalArgumentException  ){
            logger.error{ "Error creating User\n User to save was null\n $e" }
        }

        return null
    }

    override suspend fun modifyUser(user: UserRequest): Mono<User>? {
        try {
            return userRepository.save( user.convert() )
        } catch ( e: IllegalArgumentException  ){
            logger.error{ "Error modifying User\n User to save was null\n $e" }
        }

        return null
    }

    override suspend fun modifyUserById(userId: String, user: UserRequest): Mono<User>? {
        try {
            return userRepository.save( user.addUserId(userId).convert() )
        } catch ( e: IllegalArgumentException  ){
            logger.error{ "Error modifying User\n User to save was null\n $e" }
        }

        return null
    }

    override suspend fun deleteUser(user: UserRequest): Int {
        try {
            userRepository.delete( user.convert() )
            return 0
        } catch (e: IllegalArgumentException){
            logger.error{ "Error deleting User: \n$user \n $e" }
        }
        return 1
    }

    override suspend fun deleteUserById(userId: String): Int {
        try {
            userRepository.deleteById( userId )
            return 0
        } catch (e: IllegalArgumentException){
            logger.error { "Error deleting User with id: $userId \n $e" }
        }
        return 1
    }

    override suspend fun getUser(userId: String): Mono<User>? {
        try {
            return userRepository.findById(userId)
        } catch (e: IllegalArgumentException){
            logger.error { "Error getting User with id: $userId \n $e" }
        }
        return null
    }
    override suspend fun getUserByEmail(email: String): Mono<User>? {
        try {
            return userRepository.findByEmail(email)
        } catch (e: IllegalArgumentException){
            logger.error { "Error getting User with email: $email \n $e" }
        }
        return null
    }

    override suspend fun getUserId(email: String): String? {
        try {
            val user = userRepository.findByEmail(email).block()
            return user!!.id
        } catch (e: IllegalArgumentException){
            logger.error { "Error getting UserId with email: $email \n $e" }
        }
        return null
    }

    override suspend fun getCredentials(userId: String): Any? {
        try {
            return credentialService.getCredentialsByUserIdEquals(userId)
        } catch (e: IllegalArgumentException){
            println(" Error Getting Credential of user by id: \n $e")
            logger.error { "Error deleting User with id: $userId \n $e" }
        }
        return null
    }

    override fun getSettings(userId: String): Setting {
        TODO("Not yet implemented")
    }

    override suspend fun getSettingPasswordDuration(userId: String): Any? {
        try {
            return userRepository.findById(userId).block()?.passwordDuration
        } catch (e: IllegalArgumentException){
            logger.error { "Error getting Setting Password Duration of User with id: $userId \n $e" }
        }
        return null
    }
}