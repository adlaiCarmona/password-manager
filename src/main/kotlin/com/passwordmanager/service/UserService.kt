package com.passwordmanager.service

import com.passwordmanager.common.*
import com.passwordmanager.domain.Credential
import com.passwordmanager.domain.Setting
import com.passwordmanager.domain.User
import com.passwordmanager.repository.IUserRepository
import com.passwordmanager.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.sql.Timestamp

@Service
class UserService(
    val userRepository: IUserRepository,
    val credentialService: CredentialService,
    val settingService: SettingService
        ): IUserService {

    override suspend fun createUser(user: UserCreateRequest): Mono<User>? {

        try {
            return userRepository.save( user.convert() )
        } catch ( e: IllegalArgumentException  ){
            println("Error creating User\n User to save was null\n $e")
        } catch ( e: Exception ){
            println("Error creating User\n $e\n userRequest: $user")
        }

        return null
    }

    // make it patch type
    override suspend fun modifyUser(user: UserCreateRequest): Mono<User>? {

        try {
            return userRepository.save( user.convert() )
        } catch ( e: IllegalArgumentException  ){
            println("Error modifying User\n User to save was null\n $e")
        } catch ( e: Exception ){
            println("Error modifying User\n $e")
        }

        return null
    }

    override suspend fun deleteUser(user: UserRequest): Int {
        try {
            userRepository.delete( user.convert() )
            return 0
        } catch (e: Exception){
            println("Error deleting User: \n$user \n $e")
        }
        return 1
    }

    override suspend fun deleteUserById(userId: String): Int {
        try {
            userRepository.deleteById( userId )
            return 0
        } catch (e: Exception){
            println("Error deleting User with id: $userId \n $e")
        }
        return 1
    }

    override suspend fun getUser(userId: String): Mono<User>? {
        try {
            return userRepository.findById(userId)
        } catch (e: Exception){
            println("Error getting User with id: $userId \n $e")
        }
        return null
    }

    override suspend fun getCredentials(userId: String): Any? {
        try {
            println("DEBUG get Credentials v1.0: ${credentialService.getCredentialsByUserIdEquals(userId)}")
            println("DEBUG get Credentials v2.0: ${credentialService.getCredentialsByUserIdEquals2(userId)}")
//            println("DEBUG get Credentials v3.0: ${credentialService.getCredentialsByUserIdEquals3(userId)}")
            return credentialService.getCredentialsByUserIdEquals3(userId)
        } catch (e: Exception){
            println("Error deleting User with id: $userId \n $e")
        }
        return null
    }

    override fun getSettings(userId: String): Setting {
        TODO("Not yet implemented")
    }

    override suspend fun getSettingPasswordDuration(userId: String): Any? {
        try {
            println("DEBUG get Password Duration: ${userRepository.queryUserByIdEquals(userId)}")
//            return userRepository.queryUserByIdEquals(userId)
            return userRepository.findById(userId).block()?.passwordDuration
        } catch (e: Exception){
            println("Error getting Setting Password Duration of User with id: $userId \n $e")
        }
        return null
    }
}