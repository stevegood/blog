package org.stevegood.blog.comment

/**
 * Created by steve on 10/26/14.
 */
class CommentException extends Exception {

    Comment comment
    CommentExceptionType exceptionType

    CommentException(String message, CommentExceptionType exceptionType, Comment comment) {
        super(message)
        this.exceptionType = exceptionType
        this.comment = comment
    }
}

enum CommentExceptionType {
    FAILED_VALIDATION,
    CANNOT_SAVE
}
