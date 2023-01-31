package com.enterprise.testing.model

import org.springframework.stereotype.Component

data class User(

        var id: String? = "",
        val name: String? = "",
        val gender: String? = "",
        val desc: String? = ""
)