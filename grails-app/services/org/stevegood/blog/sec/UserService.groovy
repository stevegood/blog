package org.stevegood.blog.sec

import grails.transaction.Transactional

@Transactional
class UserService {

    def createUser(String username, String password, String firstName, String lastName, String email, boolean enabled = false) {
        User user = new User(username: username, password: password, enabled: enabled, firstName: firstName, lastName: lastName, email: email)
        if (!user.validate()){
            throw new UserValidationException('Error creating user', user)
        }

        user.save()
    }

    def updateUser(User user) {
        if (!user.validate()){
            throw new UserValidationException('Error updating user', user)
        }

        user.save()
    }

    void deleteUser(User user) {
        user.delete()
    }

    User getUser(long id) {
        User.get(id)
    }

    User getUser(String username) {
        User.findByUsername(username)
    }
}
