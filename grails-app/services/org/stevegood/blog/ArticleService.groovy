package org.stevegood.blog

import grails.transaction.Transactional

@Transactional
class ArticleService {

    def createArticle(String title, String body, boolean published = false) {
        new Article(title: title, body: body, published: published).save()
    }

    @Transactional(readOnly = true)
    def getArticle(long id) {
        Article.get(id)
    }

    @Transactional(readOnly = true)
    def getArticle(String slug) {
        Article.findBySlug(slug)
    }

    def updateArticle(Article article) {
        article.save()
    }

    void deleteArticle(Article article) {
        article.delete()
    }

    def publishArticle(Article article) {
        article.published = true
        article.datePublished = new Date()
        updateArticle(article)
    }
}
