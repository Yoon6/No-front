package com.pathhack.nofront.handler;

import com.pathhack.nofront.domain.Count;
import com.pathhack.nofront.domain.CountRepository;
import com.pathhack.nofront.domain.Message;
import com.pathhack.nofront.domain.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final Map<String, WebSocketSession> winnersMap = new ConcurrentHashMap<>();
    private int count;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        String sessionId = session.getId();
        sessions.put(sessionId, session);

        Message message = Message.builder().sender(sessionId).receiver("all").build();
        message.newConnect();

        count++;
        sessions.values().forEach(s -> {
            try {
                if(!s.getId().equals(sessionId)) {
//                    s.sendMessage(new TextMessage((Utils.getString(message))));
                    s.sendMessage(new TextMessage(sessionId + " 님이 들어오셨습니다. \n"));
//                    System.out.println(sessionId+" 님이 들어오셨습니다.");
                }
                s.sendMessage(new TextMessage(count+" 명 들어왔습니다."));

            } catch (Exception e) {

            }
        });

        if (count >= 6) {
            sessions.values().forEach(s -> {
                try {
                    s.sendMessage(new TextMessage("6명이 되었습니다."));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(message.getPayload());
        if (message.getPayload().equals("cell phone") && winnersMap.size() < 3) {
            winnersMap.put(session.getId(), session);
        }

        if (winnersMap.size() == 3) {
            sessions.values().forEach(s -> {
                try {
                    if (winnersMap.containsKey(s.getId())) {
                        s.sendMessage(new TextMessage("success"));
                    } else {
                        s.sendMessage(new TextMessage("fail"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        count--;

        String sessionId = session.getId();

        sessions.remove(sessionId);

        final Message message = new Message();
        message.closeConnect();
        message.setSender(sessionId);

        sessions.values().forEach(s -> {
            try {
                s.sendMessage(new TextMessage(Utils.getString(message)));
                System.out.println(sessionId+" 님이 나가셨습니다.");
            } catch (Exception e) {}
        });
    }
}
