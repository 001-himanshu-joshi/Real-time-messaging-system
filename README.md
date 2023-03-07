# End-to-End-Chat-Application-Model

## Framework And language used
- SpringBoot
- Java

## Data Flow

### Controller
 1) UserController
 2) ChatController
 3) StatusController
 
### Service
 1) UserService
 2) ChatService
 3) StatusService
 
### Repository
 - UserRepository
 - StatusRepository
 - ChatRepository
 
### Database Design
 - Here we are using the MySql DataBase and Hibernate and databases are in tabular form
 
## Project Summary
 In this Project we are performing the CRUD operations by hitting the API and here we have the links
 
 1) User
  - PostMapping -> http://localhost:8080/api/v1/user/saveUser
  - GetMapping  -> http://localhost:8080/api/v1/user/getUserById
  - PutMapping -> http://localhost:8080/api/v1/user/updateUser/{userId}
  - Login -> http://localhost:8080/api/v1/user/login
  - DeleteMapping -> http://localhost:8080/api/v1/user/deleteUser/{userId}
  
  2) Status
 - PostMapping -> http://localhost:8080/api/v1/status/saveStatus
 
  3) Chat
 - PostMapping -> http://localhost:8080/api/v1/chat/postChat
 - GetMapping ->http://localhost:8080/api/v1/chat/get-chat
 - GetMapping ->http://localhost:8080/api/v1/chat/get-conversation
 - DeleteMapping -> http://localhost:8080/api/v1/chat/deleteByID/{chatId}
 
