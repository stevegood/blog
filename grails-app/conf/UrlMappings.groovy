class UrlMappings {

	static mappings = {

        "/admin/$controller/$action?/$id?(.$format)?"(namespace:'admin'){
            constraints {
                // apply constraints here
            }
        }

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'blog', action: 'index')
        "/blog/$slug"(controller: 'blog', action: 'show')
        "/profile/$username"(controller: 'user', action: 'profile')
        "500"(view:'/error')
	}
}
