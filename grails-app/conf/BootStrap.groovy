import org.stevegood.blog.Article
import org.stevegood.blog.sec.Requestmap
import org.stevegood.blog.sec.Role
import org.stevegood.blog.sec.User
import org.stevegood.blog.sec.UserRole

class BootStrap {

    def articleService
    def userService

    def init = { servletContext ->
        // specifying env here so that this doesn't run in test
        environments {
            development {
                setup()
                if (!Article.count())
                    articleService.publishArticle(new Article(title: 'This is a test', body: 'This is a test article!'))
            }
            production {
                setup()
            }
        }
    }
    def destroy = {
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
            def sa = userService.createUser('sa', 'sapassword', true)
            UserRole.create(sa, superUser)
        }
    }
}
