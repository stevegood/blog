package org.stevegood.blog.sec

class Role {

	String authority

	static mapping = {
		cache true
        authority index: true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
