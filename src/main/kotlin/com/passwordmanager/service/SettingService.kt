package com.passwordmanager.service

import com.passwordmanager.common.*
import com.passwordmanager.domain.Setting
import com.passwordmanager.repository.ISettingRepository
import org.springframework.stereotype.Service

@Service
class SettingService(val settingRepository: ISettingRepository): ISettingService {
    override suspend fun createSetting(settingRequest: ISettingRequest): Setting? {
        try {
            return settingRepository.save( settingRequest.convert() )
        } catch ( e: IllegalArgumentException  ){
            println("Error creating Setting\n Setting to save was null\n $e")
        } catch ( e: Exception ){
            println("Error creating Setting\n $e")
        }

        return null
    }

    //right now it saves a new one; modify to updates (patch)
    override suspend fun modifySetting(userId: String, settingRequest: ISettingRequest): Setting? {
        var savedSetting: Setting? = null

        try {
            savedSetting = settingRepository.save( settingRequest.convert() )

        } catch ( e: IllegalArgumentException  ){
            println("Error modifying Setting\n Setting to save was null\n $e")
        } catch ( e: Exception ){
            println("Error modifying Setting\n $e")
        }

        return savedSetting
    }
}