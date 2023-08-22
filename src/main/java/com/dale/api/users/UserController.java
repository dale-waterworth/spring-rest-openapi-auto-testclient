package com.dale.api.users;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController implements UserOperations {
    @Override
    public User getUser(final String id) {
        return new User(
                UUID.randomUUID().toString(),
                "dale",
                "Mr",
                21
        );
    }

    @Override
    public List<User> getUsers() {
        return List.of(new User(
                UUID.randomUUID().toString(),
                "dale",
                "Mr",
                21
        ));
    }

    @Override
    public void updateUser(final String id) {
        System.out.println(id);
        // todo
    }

    @Override
    public void createUser(final CreateUserRequest user) {
        System.out.println(user);
        // todo
    }
}
