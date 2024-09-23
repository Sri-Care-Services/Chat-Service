# Chat Microservice
## Overview
The Chat Microservice provides a platform for real-time communication between users, allowing them to create chats, send messages, and retrieve chat histories. It is designed to be a standalone service that can be integrated into larger applications, such as employee management systems or social networking platforms
## Create new Chat 
- method : post
```
http://localhost:8080/api/chat/create
```
- sample input data
```
{
    "senderId": "user1",
    "receiverId": "user2"
}
```
- output
```
{
    "chatId": "66f1154f73acee646b2c4c12",
    "senderId": "user1",
    "receiverId": "user2",
    "messages": []
}
```
## Send Message
- method : post
```
[http://localhost:8080/api/chat/create](http://localhost:8080/api/chat/{chatId}/send)
```
- sample input data
```
{
    "senderId": "user1",
    "message": "Hello, Are you busy!"
}
```
- output
```
{
    "chatId": "66f1154f73acee646b2c4c12",
    "senderId": "user1",
    "receiverId": "user2",
    "messages": [
        {
            "messageId": "message_1727076287748",
            "senderId": "user1",
            "message": "Hello, Are you busy!",
            "timestamp": "2024-09-23T12:54:47.7487364"
        }
    ]
}
```
## Retrieve chat by ID
- method : Get
```
http://localhost:8080/api/chat/{chatId}
```
- output
```
{
    "chatId": "66f1154f73acee646b2c4c12",
    "senderId": "user1",
    "receiverId": "user2",
    "messages": [
        {
            "messageId": "message_1727075866590",
            "senderId": "user1",
            "message": "Hello, this is a test message!",
            "timestamp": "2024-09-23T12:47:46.59"
        }
    ]
}
```
## Get chat history for a user
- method : Get
```
http://localhost:8080/api/chat/history/user1
```
- output
```
 [
 {
        "chatId": "66f1135e73acee646b2c4c11",
        "senderId": "user1",
        "receiverId": "user2",
        "messages": []
    },
    {
        "chatId": "66f1154f73acee646b2c4c12",
        "senderId": "user1",
        "receiverId": "user2",
        "messages": [
            {
                "messageId": "message_1727075866590",
                "senderId": "user1",
                "message": "Hello, this is a test message!",
                "timestamp": "2024-09-23T12:47:46.59"
            }
        ]
    }
]
```
