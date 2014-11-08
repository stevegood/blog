package org.stevegood.blog.sec

class User implements Serializable {

	transient springSecurityService

	String username
	String password
    String firstName
    String lastName
    String email
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
        firstName blank: false
        lastName blank: false
        email blank: false, email: true, unique: true
	}

	static mapping = {
		password index: true
        username index: true
        email index: true
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

    String getFullName() {
        "$firstName $lastName"
    }
}
