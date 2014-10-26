package org.stevegood.blog

import groovy.xml.MarkupBuilder
import org.stevegood.blog.sec.User
import spock.lang.*

/**
 *
 */
class ArticleSpec extends Specification {

    def slugGeneratorService

    def setup() {
    }

    def cleanup() {
    }

    void "test article methods"() {
        given: 'An Article instance is created and a user is create to be the author'
        def writer = new StringWriter()
        new MarkupBuilder(writer).p 'This is the body of the test article'
        def author = User.findOrCreateByFirstNameAndLastNameAndUsernameAndEmail('Steve','Good','stevegood','steve@stevegood.org')
        author.password = 'testing'
        author.save()

        Article article = new Article(title: 'This is a test Article for integration testing', body: writer.toString(), author: author)
        if (!article.slugGeneratorService)
            article.slugGeneratorService = slugGeneratorService

        expect: 'The following tests to pass'
        article.slug == null

        article.generateSlug() == 'this-is-a-test-article-for-integration-testing'
        article.setSlug article.generateSlug()

        article.save()

        Article.get(article.id).slug == 'this-is-a-test-article-for-integration-testing'

        article.setTitle 'Hello world!'
        article.generateSlug() == 'hello-world'
        article.setSlug article.generateSlug()

        article.save()

        Article.get(article.id).slug == 'hello-world'

        article.delete()
    }
}
