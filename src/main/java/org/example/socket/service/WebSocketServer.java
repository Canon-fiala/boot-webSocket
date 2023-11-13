package org.example.socket.service;

import com.alibaba.fastjson.JSON;
import org.example.socket.domain.pojo.Message;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luoJ
 * @date 2023/5/26 16:21
 */
@ServerEndpoint("/webSocket/{username}")
@Component
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onLineNum =  new AtomicInteger();

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<String, Session> sessionPools =  new ConcurrentHashMap<>();

    //发送消息
    public void sendMessage(Session session,String message) throws IOException{
        if(session != null){
            synchronized (session){
                System.out.println("发送数据:"+message);
                session.getBasicRemote().sendText(message);
            }
        }
    }

    //给指定用户发送信息
    public void sendInfo(String username, String message)throws IOException{
        Session session = sessionPools.get(username);
        try {
            sendMessage(session,message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //群发消息
    public void broadCast(String message){
        for(Session session:sessionPools.values()){
            try {
                sendMessage(session,message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @OnOpen
    //建立连接成功调用
    public void onOpen(Session session, @PathParam(value = "username")String username){
        sessionPools.put(username,session);
        addOnlineCount();
        System.out.println(username + "加入webSocket！当前人数为" + onLineNum);
        //广播上线
        Message message = new Message();
        message.setDate(LocalDateTime.now());
        message.setTo("0");
        message.setText(username);
        broadCast(JSON.toJSONString(message,true));
    }
    //关闭连接调用
    @OnClose
    public void onClose(Session session,@PathParam(value = "username")String username){
        sessionPools.remove(username);
        subOnlineCount();
        System.out.println(username + "退出webSocket！当前人数为" + onLineNum);
        //广播下线通知
        Message message = new Message();
        message.setDate(LocalDateTime.now());
        message.setTo("-2");
        message.setText(username);
        broadCast(JSON.toJSONString(message,true));
    }

    //收到客户端信息后，根据接收人的username把消息推下去或者群发
    // to=-1群发消息
    @OnMessage
    public void onMessage(String message) throws IOException{
        System.out.println("server get" + message);
        Message msg = JSON.parseObject(message, Message.class);
        msg.setDate(LocalDateTime.now());
        if(msg.getTo().equals("-1")){
            broadCast(JSON.toJSONString(msg,true));
        }else {
            sendInfo(msg.getTo(),JSON.toJSONString(msg,true));
        }
    }

    private void subOnlineCount() {
        onLineNum.decrementAndGet();
    }

    private static void addOnlineCount() {
        onLineNum.incrementAndGet();
    }

    public static AtomicInteger getOnlineNumber() {
        return onLineNum;
    }

    public static ConcurrentHashMap<String, Session> getSessionPools() {
        return sessionPools;
    }
}
