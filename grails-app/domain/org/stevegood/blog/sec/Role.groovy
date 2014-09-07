package org.stevegood.blog.sec

class Role implements Serializable {

	String authority

	static mapping = {
		cache true
        authority index: true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
