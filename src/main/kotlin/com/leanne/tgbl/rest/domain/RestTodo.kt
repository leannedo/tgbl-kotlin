package com.leanne.tgbl.rest.domain

import com.leanne.tgbl.repository.domain.RepoTodo

data class RestTodo(
    val id: Long = 0,

    val name: String = "",

    val completed: Boolean = false,
) {
    companion object {
        fun toRepoTodo(restTodo: RestTodo): RepoTodo {
            return RepoTodo(
                todoId = restTodo.id,
                name = restTodo.name,
                completed = restTodo.completed
            )
        }
    }
}
