import org.stevegood.blog.Article
import org.stevegood.blog.ArticleComment
import org.stevegood.blog.comment.Comment
import org.stevegood.blog.sec.Requestmap
import org.stevegood.blog.sec.Role
import org.stevegood.blog.sec.User
import org.stevegood.blog.sec.UserRole

class BootStrap {

    def articleService
    def commentService
    def grailsApplication
    def userService

    def init = { servletContext ->
        // specifying env here so that this doesn't run in test
        environments {
            development {
                dropData()
                setup()

                def author = User.findByUsername('sa')
                10.times {
                    def article = articleService.publishArticle(new Article(title: "This is test # $it", body: 'This is a test article!', author: author), true)
                    def comment = commentService.create((it % 2 ? 'Steve Good' : author), 'This is the best article of all time!', true)
                    ArticleComment.create(article, comment, true)
                }
            }
            test {
                dropData()
            }
            production {
                setup()
            }
        }
    }
    def destroy = {
        environments {
            test{
                dropData()
            }
        }
    }

    void setup() {
        [
            '/':                 'permitAll',
            '/index':            'permitAll',
            '/assets/**':        'permitAll',
            '/**/js/**':         'permitAll',
            '/**/css/**':        'permitAll',
            '/**/images/**':     'permitAll',
            '/**/favicon.ico':   'permitAll',
            '/login/**':         'permitAll',
            '/logout/**':        'permitAll',
            '/admin/**':         'ROLE_SUPER_USER',
            '/blog/**':          'permitAll'
        ].each { k, v ->
            Requestmap.findOrCreateByUrlAndConfigAttribute(k, v).save()
        }

        def superUser = Role.findOrCreateByAuthority('ROLE_SUPER_USER').save(flush: true)

        if (!User.count()){
            def sa = userService.createUser('sa', 'sapassword', 'Steve', 'Good', 'steve@stevegood.org', true)
            UserRole.create(sa, superUser)
        }
    }

    void dropData() {
        grailsApplication.domainClasses*.clazz?.each {
            it.list()*.delete(flush: true)
        }
    }
}
