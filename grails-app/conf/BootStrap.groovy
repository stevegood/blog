import org.stevegood.blog.sec.Requestmap
import org.stevegood.blog.sec.Role
import org.stevegood.blog.sec.User
import org.stevegood.blog.sec.UserRole

class BootStrap {

    def init = { servletContext ->
        [
                '/':                              'permitAll',
                '/index':                         'permitAll',
                '/index.gsp':                     'permitAll',
                '/assets/**':                     'permitAll',
                '/**/js/**':                      'permitAll',
                '/**/css/**':                     'permitAll',
                '/**/images/**':                  'permitAll',
                '/**/favicon.ico':                'permitAll',
                '/login/**':                      'permitAll',
                '/logout/**':                     'permitAll',
                '/admin/**':                      'ROLE_SUPER_USER',
                '/requestmap/**':                 'ROLE_SUPER_USER',
                '/security/**':                   'ROLE_SUPER_USER'
        ].each { k, v ->
            Requestmap.findOrCreateByUrlAndConfigAttribute(k, v).save()
        }

        def superUser = Role.findOrCreateByAuthority('ROLE_SUPER_USER').save(flush: true)

        if (!User.count()){
            def sa = new User(username: 'sa', password: 'sapassword', enabled: true).save(flush: true)

            def p2 = sa.springSecurityService.encodePassword('sapassword')
            println sa.springSecurityService.passwordEncoder.isPasswordValid(sa.password, p2, null)
            println sa.password
            println p2

            UserRole.create(sa, superUser)
        }
    }
    def destroy = {
    }
}
