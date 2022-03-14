package com.leanne.tgbl.service

import com.leanne.tgbl.exceptions.ResourceNotFoundException
import com.leanne.tgbl.repository.TodoRepository
import com.leanne.tgbl.repository.domain.RepoTodo
import com.leanne.tgbl.rest.domain.RestTodo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
public class TodoServiceImpl : TodoService {
    @Autowired
    lateinit var toDoRepository: TodoRepository

    override fun createTodo(restTodo: RestTodo): RestTodo {
        return toDoRepository.saveAndFlush(RestTodo.toRepoTodo(restTodo)).let {
            RepoTodo.toRestTodo(it)
        }
    }

    override fun getAllTodos(): List<RestTodo?> {
        return toDoRepository.findAll().map(RepoTodo::toRestTodo)
    }

    override fun getTodoById(id: Long): RestTodo? {
        if (!toDoRepository.existsById(id)) {
            throw ResourceNotFoundException("todo", id)
        }

        return toDoRepository.findByTodoId(id)?.let {
            RepoTodo.toRestTodo(it)
        }
    }

    override fun updateTodo(id: Long, todo: RestTodo): RestTodo? {
        if (!toDoRepository.existsById(id)) {
            throw ResourceNotFoundException("todo", id)
        }

        val todoToUpdate = toDoRepository.findByTodoId(id)!!.copy(
            name = todo.name,
            completed = todo.completed
        )
        return toDoRepository.save(todoToUpdate).let { RepoTodo.toRestTodo(it) }
    }

    override fun deleteTodo(id: Long) {
        if (!toDoRepository.existsById(id)) {
            throw ResourceNotFoundException("todo", id)
        }

        toDoRepository.deleteById(id)
    }
}
