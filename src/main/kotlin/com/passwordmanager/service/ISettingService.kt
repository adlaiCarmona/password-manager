package com.passwordmanager.service

import com.passwordmanager.common.ISettingRequest
import com.passwordmanager.domain.Setting

interface ISettingService {

    suspend fun createSetting(settingRequest: ISettingRequest): Setting?

    suspend fun modifySetting(userId: String, settingRequest: ISettingRequest): Setting?
}