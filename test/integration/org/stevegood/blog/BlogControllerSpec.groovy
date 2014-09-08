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
}
