package com.enterprise.testing.Controller

import com.enterprise.testing.model.ClientDeleteResponse
import com.enterprise.testing.model.ClientListResponse
import com.enterprise.testing.model.User
import com.enterprise.testing.model.ClientResponse
import com.enterprise.testing.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/customer")
class TransferController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/")
    fun getAllUser(): List<User> {
        return userService.getallUser()
    }


    @PostMapping("/new")
    fun postAPi(@RequestBody user: User): ClientResponse {
        return userService.createUser(user)
    }


    @GetMapping("/search")
    fun getAllUserByName(@RequestParam key: String): ClientListResponse {

        return userService.getUserByName(key)
    }

    @PutMapping("/update")
    fun updateUser(@RequestBody user: User): ClientResponse {
        return userService.updateUser(user)
    }

    @DeleteMapping("/delete")
    fun deleteUser(@RequestParam id: String): ClientDeleteResponse {

        return userService.deletUser(id)
    }
}