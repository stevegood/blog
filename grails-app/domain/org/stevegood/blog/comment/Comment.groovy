package org.stevegood.blog.comment

import org.stevegood.blog.sec.User

/**
 * Created by steve on 10/26/14.
 */
class Comment {
    Date dateCreated
    Date lastUpdated
    String authorName
    User author
    String body

    static constraints = {
        authorName nullable: true, validator: { String authName, Comment comment ->
            (comment.author) ? true : authName.size() > 0
        }

        author nullable: true, validator: { User _author, Comment comment ->
            (comment.authorName?.size()) ? true : _author != null
        }

        body blank: false
    }

    String commentAuthorName() {
        author?.fullName ?: authorName
    }
}
