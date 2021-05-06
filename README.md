## Chat server through web sockets
First attempt at a chat server using STOMP client.
#### Notes on application design
* You can check out the database at http://localhost:8080/h2-console
* Spring exception handler was not implemented, as I did not see a use case for user defined errors for these use cases. Blank responses are clear enough for receiver of data.
* Attempted at creating unit tests for business logic in controller, but failed to implement without mocking every method.
* ChatMessage.MessageType constants are for receiving application to identify what type of message is coming in.
#### Comments
Figuring out how to test the message routing of STOMP was a very big challenge. I ended up using a template client from some article and editing the javascript file there to test out the API's. I am not familiar with javascript, so this took me way too long. Postman sadly has no functionality to call web sockets :(

I am not confident about the way file sending/receiving should work with STOMP. I was not able to test it with a client.