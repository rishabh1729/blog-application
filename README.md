# blog-application
- This repository contains backend and frontend code for blog application. Frontend uses Angular and backend uses Spring Boot.

- Users can register and log in to the application. The application allows only the logged in users to access the blogs written by multiple users and also to write their own. Other users will always be redirected to login screen. Users can write rich beautiful blogs with the help of text-editor provided in the application.

## Backend
- Spring Boot Maven Project
- **Dependencies:** Spring Data JPA, Spring Web, Spring Security
- *Postgres* database persists user authentication details and blog posts.
- Maven dependencies of *jsonwebtoken* have been used to create and validate JWT tokens for authentication.
- The JWT tokens, on successful login, are signed with private key, retrieved from Java keystore, and sent to client. Public key is used for validation of these tokens whenever user makes any request after login.
- APIs:-
    - AuthenticationController: `/api/auth`
	    - `/signup`: POST Request to register
	    - `/login`: POST Request to login
    - PostController: `/api/posts`
	    - `/`: POST Request to create post
	    - `/all`: GET Request to get all posts
	    - `/get/{id}`: GET Request to get a post with *id*

### Building the project
To build the backend, run the command: `mvn clean install`

### Running the project
To run the Spring Boot application, run the command: `mvn spring-boot:run`

## Frontend
- Angular Project
- **Dependencies:** Bootstrap, ngx-webstorage, tinyMCE text-editor
- *ngx-webstorage* manages local storage of authentication token.
- [TinyMCE](https://www.tiny.cloud/docs/tinymce/6/) text-editor enables user to create rich posts with various formatting options.
- AuthGuard is used to protect the routes from unauthorized access in application.

### Running the project
To run the Angular application, run the command: `ng serve`