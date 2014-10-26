package org.stevegood.blog

import grails.transaction.Transactional

@Transactional
class ArticleService {

    Article createArticle(String title, String body, boolean published = false, boolean flush = false) {
        new Article(title: title, body: body, published: published).save(flush: flush)
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

    void deleteArticle(Article article, boolean flush = flush) {
        article.delete(flush: flush)
    }

    Article publishArticle(Article article, boolean flush = false) {
        article.published = true
        article.datePublished = new Date()
        updateArticle(article, flush)
    }
}
