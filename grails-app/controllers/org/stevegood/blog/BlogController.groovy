package org.stevegood.blog

import grails.transaction.Transactional

@Transactional(readOnly = true)
class BlogController {

    def index() {
        [articles: Article.findAllByPublished(true, [max: 5, sort: 'datePublished', order: 'desc'])]
    }
}
