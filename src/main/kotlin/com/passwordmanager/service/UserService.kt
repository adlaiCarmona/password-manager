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

    override suspend fun createUser(user: UserCreateRequest): User? {
        user.password ?: throw IllegalArgumentException("Password cannot be null!")

        try {
            return userRepository.save( user.convert() ).block()
        } catch ( e: IllegalArgumentException  ){
            println("Error creating User\n User to save was null\n $e")
        } catch ( e: Exception ){
            println("Error creating User\n $e\n userRequest: $user")
        }

        return null
    }

    // make it patch type
    override suspend fun modifyUser(user: UserRequest): User? {
        user.password ?: throw IllegalArgumentException("Password cannot be null!")

        try {
            return null//userRepository.save( user.convert() )
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

    override suspend fun getUser(userId: String): User? {
        try {
            return userRepository.findById(userId).block()
        } catch (e: Exception){
            println("Error getting User with id: $userId \n $e")
        }
        return null
    }

    override suspend fun getCredentials(userId: String): List<Credential>? {
        try {
            return credentialService.getCredentialsByUserIdEquals(userId)
        } catch (e: Exception){
            println("Error deleting User with id: $userId \n $e")
        }
        return null
    }

    override fun getSettings(userId: String): Setting {
        TODO("Not yet implemented")
    }
}