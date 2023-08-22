package com.dale.api.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/users/v1")
@Validated
public interface UserOperations {

    @Operation(summary = "Get a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    User getUser(@PathVariable String id);

    @Operation(summary = "Get all users")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    List<User> getUsers();

    @Operation(summary = "Update user by id")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateUser(@PathVariable final String id);

    @Operation(summary = "Create a new user")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void createUser(@Valid @RequestBody final CreateUserRequest user);

    record User(
            String id,
            String name,
            String title,
            int age) {
    }

    @Data
    @AllArgsConstructor
    @Validated
    class CreateUserRequest {
        @NotBlank
        @Size(min = 1, max = 50)
        private String name;
        @NotBlank
        @Size(min = 1, max = 50)
        private String title;
        @Positive
        private int age;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CreateUserRequest that = (CreateUserRequest) o;
            return age == that.age && Objects.equals(name, that.name) && Objects.equals(title, that.title);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, title, age);
        }
    }
}
