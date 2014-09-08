package org.stevegood.blog

class Article implements Serializable {

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
}
