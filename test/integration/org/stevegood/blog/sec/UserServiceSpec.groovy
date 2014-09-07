package org.stevegood.blog.sec

import spock.lang.*

/**
 *
 */
class UserServiceSpec extends Specification {

    def userprops
    def userService

    def setup() {
        userprops = [
            username: 'chester',
            password: 'testpassword'
        ]
    }

    def cleanup() {}

    void "test user service crud methods"() {
        given: 'User parameters are sent to the service to create a user'
        def user = userService.createUser(userprops.username, userprops.password, false)

        expect: 'The user to be saved and have a valid ID'
        user.id > 0
        user.username == userprops.username
        user.password != userprops.password
        def user_id = user.id

        // When the user has their details changed the save should happen with no errors
        user.setEnabled true
        userService.updateUser(user)

        // Test the two ways of getting a user (id | username)
        userService.getUser(user_id)?.username == userprops.username
        userService.getUser(userprops.username)?.id == user_id

        // When the user is deleted it should no longer exist when trying to retrieve by ID
        userService.deleteUser(user)
        userService.getUser(user_id) == null
    }
}
