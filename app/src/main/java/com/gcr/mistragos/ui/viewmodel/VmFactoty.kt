package com.gcr.mistragos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gcr.mistragos.domain.Repository

class VmFactoty (private val repo:Repository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java).newInstance(repo)
    }
}