package com.leanne.tgbl.service

import com.leanne.tgbl.rest.domain.RestTodo

interface TodoService {
    fun createTodo(restTodo: RestTodo): RestTodo
    fun getAllTodos(): List<RestTodo?>
    fun getTodoById(id: Long): RestTodo?
    fun updateTodo(id: Long, todo: RestTodo): RestTodo?
    fun deleteTodo(id: Long)
}
