package com.leanne.tgbl.rest.controllers

import com.leanne.tgbl.exceptions.ResourceNotFoundException
import com.leanne.tgbl.rest.domain.RestTodo
import com.leanne.tgbl.service.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TodoController {
    @Autowired
    lateinit var todoService: TodoService

    @GetMapping("/")
    fun welcome(): String {
        return "Welcome to the great bucket list"
    }

    @GetMapping("/todos")
    fun getAllTodos(): List<RestTodo?> {
        return todoService.getAllTodos()
    }

    @GetMapping("/todos/{id}")
    fun getTodoById(@PathVariable id: Long): ResponseEntity<RestTodo?> {
        try {
            return ResponseEntity(todoService.getTodoById(id), HttpStatus.ACCEPTED)
        } catch (e: ResourceNotFoundException) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/todos")
    fun createTodo(@RequestBody restTodo: RestTodo?): ResponseEntity<RestTodo> {
        val createdTodo = todoService.createTodo(restTodo!!)
        return ResponseEntity(createdTodo, HttpStatus.CREATED)
    }

    @PutMapping("/todos/{id}")
    fun updateTodo(@PathVariable id: Long, @RequestBody restTodo: RestTodo?): ResponseEntity<RestTodo> {
        val updatedTodo = todoService.updateTodo(id, restTodo!!)
        return ResponseEntity(updatedTodo, HttpStatus.ACCEPTED)
    }

    @DeleteMapping("todos/{id}")
    fun deleteTodo(@PathVariable id: Long): ResponseEntity<HttpStatus> {
        try {
            todoService.deleteTodo(id).also {
                return ResponseEntity(HttpStatus.ACCEPTED)
            }
        } catch (e: ResourceNotFoundException) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
