package org.stevegood.blog

import grails.transaction.Transactional
import org.stevegood.blog.sec.User

@Transactional
class ArticleService {

    Article createArticle(String title, String body, User author, boolean published = false, boolean flush = false) {
        new Article(title: title, body: body, author: author, published: published).save(flush: flush)
    }

    @Transactional(readOnly = true)
    Article getArticle(long id) {
        Article.get(id)
    }

    @Transactional(readOnly = true)
    Article getArticle(String slug) {
        Article.findBySlug(slug)
    }

    Article updateArticle(Article article, boolean flush = false) {
        article.save(flush: flush)
    }

    void deleteArticle(Article article, boolean flush = false) {
        article.delete(flush: flush)
    }

    Article publishArticle(Article article, boolean flush = false) {
        article.published = true
        article.datePublished = new Date()
        updateArticle(article, flush)
    }
}
