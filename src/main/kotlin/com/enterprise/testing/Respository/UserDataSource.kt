package com.enterprise.testing.Respository

import com.enterprise.testing.model.User

interface UserDataSource {

    fun getallUser (): Collection<User>
}