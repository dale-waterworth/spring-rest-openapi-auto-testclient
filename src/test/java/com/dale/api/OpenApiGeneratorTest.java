package com.dale.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Tag("openApiGeneratorTest")
public class OpenApiGeneratorTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void generateSwaggerJson() throws Exception {
        final var writer = Files.newBufferedWriter(
            Paths.get("build", "api-docs.json"), StandardCharsets.UTF_8
        );
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api-docs/v1")
                .accept(MediaType.APPLICATION_JSON))
            .andDo(result -> writer.write(result.getResponse().getContentAsString()));
        writer.flush();
        writer.close();
    }
}
