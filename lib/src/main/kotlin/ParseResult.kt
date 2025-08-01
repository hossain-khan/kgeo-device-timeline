package dev.hossain.timeline

import dev.hossain.timeline.model.TimelineData

/**
 * Sealed class representing the result of a parsing operation.
 */
sealed class ParseResult {
    /**
     * Successful parsing result containing the timeline data.
     */
    data class Success(val data: TimelineData) : ParseResult()
    
    /**
     * Failed parsing result containing error information.
     */
    data class Error(val exception: Throwable, val message: String = exception.message ?: "Unknown error") : ParseResult()
    
    /**
     * Returns the data if successful, or null if failed.
     */
    fun getOrNull(): TimelineData? = when (this) {
        is Success -> data
        is Error -> null
    }
    
    /**
     * Returns the data if successful, or throws the contained exception if failed.
     */
    fun getOrThrow(): TimelineData = when (this) {
        is Success -> data
        is Error -> throw exception
    }
    
    /**
     * Returns true if the result is successful.
     */
    fun isSuccess(): Boolean = this is Success
    
    /**
     * Returns true if the result is an error.
     */
    fun isError(): Boolean = this is Error
}