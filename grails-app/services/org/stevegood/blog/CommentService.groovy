package org.stevegood.blog

import static org.stevegood.blog.comment.CommentExceptionType.*
import grails.transaction.Transactional
import org.stevegood.blog.comment.Comment
import org.stevegood.blog.comment.CommentException
import org.stevegood.blog.sec.User

/**
 * Created by steve on 10/26/14.
 */
@Transactional
class CommentService {

    Comment create(User author, String body, boolean flush = false) {
        Comment comment = new Comment(author: author, body: body)
        save(comment, flush)
    }

    Comment create(String authorName, String body, boolean flush = false) {
        Comment comment = new Comment(authorName: authorName, body: body)
        save(comment, flush)
    }

    Comment save(Comment comment, boolean flush = false) {
        if (!comment.validate()){
            throw new CommentException('Comment failed validation', FAILED_VALIDATION, comment)
        }
        comment.save(flush: flush)
    }

}
