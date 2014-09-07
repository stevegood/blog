package org.stevegood.blog

class Article {

    static transients = ['slugGeneratorService']

    def slugGeneratorService

    String title
    String body
    String slug
    Date dateCreated
    Date lastUpdated
    Date datePublished
    boolean published = false

    static constraints = {
        slug nullable: true, blank: false, unique: true
    }

    static mapping = {
        slug index: true
    }

    def beforeInsert() {
        slug = slugGeneratorService.generateSlug(this.class, 'slug', title)
    }

    def beforeUpdate(){
        if(isDirty('title'))
            slug = slugGeneratorService.generateSlug(this.class, 'slug', title)
    }
}
