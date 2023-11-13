# Spring-websocket
一段Spring整合WebSocket协议的代码，实现了简易聊天室的功能；包含聊天室登录、退出的功能，支持群发消息的功能。
传统的HTTP协议，一般通过向服务器发送请求，拉取数据实现半双工通信，缺点是服务器难以直接向浏览器下发消息，因此，websocket协议应运而生，可用于向建立连接的浏览器主动下发任意数据（PUSH）。本项目基于Spring平台，整合websocket协议，实现一个简易web聊天室的功能。主要特性如下:

1.包含聊天室登录、退出的功能。登录时，浏览器自动向服务器发起websocket连接，退出时自动切断。登录页面为http://localhost:8080/login

2.账号Jon或者TOM，密码是1234，登录后，用户可查看到聊天室在线的用户列表，我们在服务器上通过一个hashmap始终记录了当前在线的用户列表；

3.登录的用户可以点击一个在线的其他用户，并给他发送消息，消息先提交给服务器，在通过服务器转发给另一端用户；

4.支持群发消息的功能，使用时，服务器会将收到的消息群发给当前在线的所有用户;

5.添加好友上线提醒和下线提醒的功能，当有好友上线或下线时自动通知所有其他在线人，不要刷新页面可看到实时在线用户列表。

效果图：

![QQ图片20231113170919](https://github.com/Canon-fiala/boot-webSocket/assets/96220897/7232bc88-a35c-49ba-8921-211e5de7e6db)
![QQ图片20231113171107](https://github.com/Canon-fiala/boot-webSocket/assets/96220897/6e4e99b7-bac2-4afc-84ed-5b13382221c1)
![QQ图片20231113171110](https://github.com/Canon-fiala/boot-webSocket/assets/96220897/77da8159-634c-4f58-8574-5e21fea21058)
![QQ图片20231113171112](https://github.com/Canon-fiala/boot-webSocket/assets/96220897/4b4c581e-f180-463b-b3f1-e8f24cf1c094)
![QQ图片20231113171114](https://github.com/Canon-fiala/boot-webSocket/assets/96220897/65667c57-cfea-44fa-aedc-21658be03159)

