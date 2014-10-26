package org.stevegood.blog

import groovy.xml.MarkupBuilder
import org.stevegood.blog.sec.User
import spock.lang.*

/**
 *
 */
class ArticleServiceSpec extends Specification {

    def articleService
    def slugGeneratorService

    def setup() {
        Article.metaClass.generateSlug = {
            slugGeneratorService.generateSlug(delegate.class, 'slug', delegate.title)
        }
    }

    def cleanup() {
    }

    void "test article service crud methods"() {
        given: 'A map of properties'
        def writer = new StringWriter()
        new MarkupBuilder(writer).p 'This is a test!'
        def props = [title: 'Integration Test Article', published: false, body: writer.toString()]
        String slug = 'integration-test-article'
        def author = User.findOrCreateByFirstNameAndLastNameAndUsernameAndEmail('Steve','Good','stevegood','steve@stevegood.org')
        author.password = 'testing'
        author.save()

        expect: 'The following code will exercise the service'
        def article = articleService.createArticle(props.title, props.body, author, props.published)

        // article should have an ID
        article.id > 0
        def article_id = article.id

        // article slug should match predefined slug
        article.slug == slug

        // get the article by ID and also by slug
        articleService.getArticle(article_id)?.slug == slug
        articleService.getArticle(article_id)?.title == props.title
        articleService.getArticle(article_id)?.body == props.body
        articleService.getArticle(article_id)?.published == props.published

        articleService.getArticle(slug)?.id == article_id
        articleService.getArticle(slug)?.title == props.title
        articleService.getArticle(slug)?.body == props.body
        articleService.getArticle(slug)?.published == props.published

        // modify the article and make sure the values persisted
        article.setTitle "${props.title} x"
        article.setPublished true
        articleService.updateArticle(article)

        articleService.getArticle(article_id)?.slug == "${slug}-x"
        articleService.getArticle(article_id)?.title == "${props.title} x"
        articleService.getArticle(article_id)?.published

        // test publishing the article
        articleService.publishArticle(article)
        articleService.getArticle(article_id).published
        articleService.getArticle(article_id).datePublished != null

        // test deleting the article
        articleService.deleteArticle(article)
        articleService.getArticle(article_id) == null
    }
}
