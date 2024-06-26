package com.carlostorres.apphabits.authentication.domain.matcher

interface EmailMatcher {

    fun isValid(email: String) : Boolean

}