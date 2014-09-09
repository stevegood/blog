package org.stevegood.blog

import grails.test.mixin.TestFor
import spock.lang.*

/**
 *
 */
@TestFor(BlogController)
class BlogControllerSpec extends Specification {

    def articleService

    def setup() {
    }

    def cleanup() {
    }

    void "test index action"() {
        given: 'A collection of articles are created for testing'
        def articles = []
        6.times {
            articles << articleService.createArticle("Testing $it", 'This is the body', true)
        }

        when: 'The index action is executed'
        def result = controller.index()

        then: '5 or fewer articles should be returned'
        result.articles.size() <= 5

        and: 'All articles should be published == true and datePublished != null'
        result.articles.each {
            it.published
            it.datePublished != null
        }

        articles.each {
            articleService.deleteArticle(it)
        }
    }

    void "test show action happy path"() {
        given: 'An Article is created'
        def article = articleService.createArticle("This is an integration test from ${new Date().toString()}", 'This is the body', true)
        assert article.slug

        when: 'The show action is called using the article slug'
        def result = controller.show(article.slug)

        then: 'We should get back the same article instance'
        article.id == result.article.id
        article.slug == result.article.slug
        article.title == result.article.title
        article.body == result.article.body
        article.published == result.article.published

        articleService.deleteArticle article
    }

    void "test show action expect 404 for published == false"() {
        given: 'An Article is created'
        def article = articleService.createArticle("This is an integration test from ${new Date().toString()}", 'This is the body', false)
        assert article.slug

        when: 'The show action is called using the article slug'
        def result = controller.show(article.slug)

        then: 'The response should have a status of 404'
        controller.response.status == 404

        articleService.deleteArticle article
    }

    void "test show action for a slug that does not exist"() {
        given: 'A slug that does not exist'
        String slug = "this-will-never-exist-${new Date().time}"

        when: 'The show action is called using the slug'
        def result = controller.show(slug)

        then: 'The response should have a status of 404'
        controller.response.status == 404
    }
}
