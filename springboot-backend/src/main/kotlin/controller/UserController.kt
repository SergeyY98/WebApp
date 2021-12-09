package com.example.springboot.controller

import com.example.springboot.exception.ResourceNotFoundException
import com.example.springboot.model.User
import com.example.springboot.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.List

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users")
class UserController {
    @Autowired
    private val userRepository: UserRepository? = null

    @GetMapping
    fun getAllUsers(): MutableList<User> {
        return userRepository!!.findAll()
    }

    // build create user REST API
    @PostMapping
    fun createUser(@RequestBody user: User): User {
        return userRepository!!.save(user)
    }

    // build get user by id REST API
    @GetMapping("{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        val user: User = userRepository!!.findById(id)
            .orElseThrow { ResourceNotFoundException("User not exist with id:$id") }
        return ResponseEntity.ok(user)
    }

    // build update user REST API
    @PutMapping("{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody userDetails: User): ResponseEntity<User> {
        val updateUser: User = userRepository!!.findById(id)
            .orElseThrow { ResourceNotFoundException("User not exist with id: $id") }
        updateUser.firstName = userDetails.firstName
        updateUser.lastName = userDetails.lastName
        updateUser.email = userDetails.email
        userRepository!!.save(updateUser)
        return ResponseEntity.ok(updateUser)
    }

    // build delete user REST API
    @DeleteMapping("{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<HttpStatus> {
        val user: User = userRepository!!.findById(id)
            .orElseThrow { ResourceNotFoundException("User not exist with id: $id") }
        userRepository!!.delete(user)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}