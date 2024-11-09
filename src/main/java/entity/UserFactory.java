package main.java.entity;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     * @return the new user
     */
    User create();

}
