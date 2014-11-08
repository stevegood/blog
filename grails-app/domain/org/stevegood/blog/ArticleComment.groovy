package org.stevegood.blog

import org.stevegood.blog.comment.Comment

/**
 * Created by steve on 10/26/14.
 */
class ArticleComment {
    Article article
    Comment comment

    static ArticleComment create(Article article, Comment comment, Boolean flush = false) {
        ArticleComment.findOrCreateByArticleAndComment(article, comment).save(flush: flush)
    }
}
