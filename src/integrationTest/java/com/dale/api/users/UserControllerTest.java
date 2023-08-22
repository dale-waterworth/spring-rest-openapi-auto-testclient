package com.dale.api.users;

import com.dale.ApiClient;
import com.dale.CreateUserRequest;
import com.dale.api.UserControllerApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserControllerTest {
    private final static int port = 8080;
    private final static String host = "localhost";
    private static UserControllerApi userController;

    @BeforeAll
    public static void beforeAll() {
        var apiClient = new ApiClient();
        apiClient.setBasePath(String.format("http://%s:%s", host, port));
        userController = new UserControllerApi(apiClient);
    }

    @Test
    public void shouldGetUserList() {
        var users = userController.getUsers();
        assertEquals(1, users.size());
        assertEquals("dale", users.get(0).getName());
    }

    @Test
    public void shouldGetUser() {
        assertEquals("dale", userController.getUser("1").getName());
    }

    private static List<CreateUserRequest> failingCreates() {
        return List.of(
            new CreateUserRequest().name(null).title(null),
            new CreateUserRequest().name("").title(""),
            new CreateUserRequest().name("dale").title("mr"),
            new CreateUserRequest().name("dale").title("mr").age(0)
        );
    }
    @ParameterizedTest
    @MethodSource("failingCreates")
    public void shouldFailForInvalidCreates(CreateUserRequest createUserRequest) {
        Assertions.assertThrows(Exception.class, () -> userController.createUser(createUserRequest));
    }

    @Test
    public void shouldCreateUser() {
        assertEquals(HttpStatus.CREATED,
            userController.createUserWithHttpInfo(
                new CreateUserRequest().name("newName").title("newTitle").age(32)
            ).getStatusCode());
    }

    @Test
    public void shouldUpdateUser() {
        assertEquals(HttpStatus.NO_CONTENT,
            userController.updateUserWithHttpInfo("1").getStatusCode());
    }
}