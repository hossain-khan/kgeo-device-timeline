package dev.hossain.timeline

/**
 * Base exception for all parser-related errors.
 */
sealed class ParseException(message: String, cause: Throwable? = null) : Exception(message, cause)

/**
 * Exception thrown when the input file is not found or cannot be accessed.
 */
class FileNotFoundException(path: String, cause: Throwable? = null) : 
    ParseException("File not found: $path", cause)

/**
 * Exception thrown when the JSON format is invalid or cannot be parsed.
 */
class InvalidFormatException(message: String, cause: Throwable? = null) : 
    ParseException("Invalid JSON format: $message", cause)

/**
 * Exception thrown when the input validation fails.
 */
class ValidationException(message: String, cause: Throwable? = null) : 
    ParseException("Validation failed: $message", cause)

/**
 * Exception thrown when an IO error occurs during parsing.
 */
class ParseIOException(message: String, cause: Throwable? = null) : 
    ParseException("IO error during parsing: $message", cause)