package org.stevegood.blog

import org.stevegood.blog.comment.Comment
import org.stevegood.blog.sec.User

class Article implements Serializable {

    static transients = ['slugGeneratorService','comments','commentCount']

    def slugGeneratorService

    User author
    String title
    String body
    String slug
    Date dateCreated
    Date lastUpdated
    Date datePublished
    boolean published = false

    static constraints = {
        slug nullable: true, blank: false, unique: true
        datePublished nullable: true
    }

    static mapping = {
        slug index: true
    }

    def beforeInsert() {
        slug = generateSlug()
    }

    def beforeUpdate(){
        if(isDirty('title'))
            slug = generateSlug()
    }

    String generateSlug() {
        slugGeneratorService.generateSlug(this.class, 'slug', title)
    }

    ArrayList<Comment> getComments() {
        ArticleComment.findAllByArticle(this)*.comment
    }

    int getCommentCount() {
        ArticleComment.countByArticle(this)
    }
}
