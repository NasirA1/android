package com.example.dagger2tut2.model

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val website: String
) {
    companion object {
        fun unauthenticatedUser() =
            User(-1, "", "", "")
    }
}
