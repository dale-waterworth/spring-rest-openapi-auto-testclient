# Welcome!!

This repo provides an example of how to automatically create a client to test a rest API.

# Quick start in 3 steps

**1. View the OpenAPI definition**

`./gradlew bootrun`

View http://localhost:8080/swagger-ui/index.html#/


**2. Build the swagger file**

`./gradlew openApiGeneratorTest`

**3. Generate the client**

`./gradlew buildJavaRestTemplateSdk`

Now all the code is ready to run the tests:

`./gradlew integrationTest`

## Further information
For a more detailed overview visit the corresponding on [DZONE](https://dzone.com/users/3058394/dezza.html): TODO once published


