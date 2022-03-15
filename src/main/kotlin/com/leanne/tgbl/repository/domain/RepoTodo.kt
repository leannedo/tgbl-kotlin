package com.leanne.tgbl.repository.domain

import com.leanne.tgbl.rest.domain.RestTodo
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "todos")
data class RepoTodo(
    @field:Id
    @field:GeneratedValue
    @field:Column(name = "id")
    val todoId: Long = 0,

    @field:Column(name = "name")
    val name: String = "",

    @field:Column(name = "completed")
    val completed: Boolean = false,

    @field:Column(name = "created_at")
    val createdAt: LocalDateTime? = null,

    @field:Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,
) {
    companion object {
        fun toRestTodo(repoTodo: RepoTodo): RestTodo {
            return RestTodo(repoTodo.todoId, repoTodo.name, repoTodo.completed)
        }
    }
}
