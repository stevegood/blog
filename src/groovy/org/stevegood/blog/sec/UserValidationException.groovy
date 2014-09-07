package org.stevegood.blog.sec

/**
 * @author <a href="http://stevegood.org">Steve Good</a>
 * @since 9/7/14.
 */
class UserValidationException extends Exception {

    User user

    UserValidationException(String message, User user) {
        super(message)
        this.user = user
    }
}
