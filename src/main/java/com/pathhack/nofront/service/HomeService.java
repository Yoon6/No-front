package com.pathhack.nofront.service;

import com.pathhack.nofront.WebSocketUtil;
import org.java_websocket.drafts.Draft_6455;
import org.springframework.stereotype.Service;

import java.net.URI;


@Service
public class HomeService {
    public void makeSocketConnection(String nickname) throws Exception {

        System.out.println("nickname : " + nickname);

        URI uri = new URI("ws://localhost:8080/room");
        WebSocketUtil util = new WebSocketUtil(uri, new Draft_6455());


        util.connectBlocking();
    }
}
