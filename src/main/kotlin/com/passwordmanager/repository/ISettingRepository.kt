package com.passwordmanager.repository

import com.passwordmanager.domain.Setting
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ISettingRepository: CoroutineCrudRepository<Setting, String> {
}