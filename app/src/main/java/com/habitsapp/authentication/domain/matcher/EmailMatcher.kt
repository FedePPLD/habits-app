package com.habitsapp.authentication.domain.matcher

interface EmailMatcher {
    fun isValid(email: String): Boolean
}