package com.leanne.tgbl.repository

import com.leanne.tgbl.repository.domain.RepoTodo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<RepoTodo, Long> {
    fun findByTodoId(id: Long): RepoTodo?
}
