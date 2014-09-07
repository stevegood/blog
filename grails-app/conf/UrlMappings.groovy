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

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
