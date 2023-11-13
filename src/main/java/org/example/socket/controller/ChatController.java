package org.example.socket.controller;

import org.example.socket.domain.pojo.User;
import org.example.socket.service.LoginService;
import org.example.socket.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.Session;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author luoJ
 * @date 2023/5/26 16:20
 */
@Controller
public class ChatController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/onlineusers")
    @ResponseBody
    public Set<String> onlineusers(@RequestParam("currentuser") String currentuser) {
        ConcurrentHashMap<String, Session> map = WebSocketServer.getSessionPools();
        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        Set<String> nameset = new HashSet<String>();
        while (it.hasNext()) {
            String entry = it.next();
            if (!entry.equals(currentuser))
                nameset.add(entry);
        }
        return nameset;
    }


    @RequestMapping("getuid")
    @ResponseBody
    public User getuid(@RequestParam("username") String username) {
        Long a = loginService.getUidbyname(username);
        User u = new User();
        u.setUid(a);
        return u;
    }
}
