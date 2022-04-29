package com.pathhack.nofront.service;

import com.pathhack.nofront.WebSocketUtil;
import org.java_websocket.drafts.Draft_6455;
import com.pathhack.nofront.domain.HomeRepository;
import com.pathhack.nofront.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeService {
    
    private final HomeRepository homeRepository;

    @Transactional
    public Long join(Member member){
        homeRepository.save(member);
        return member.getId();
    }

    @Transactional
    public void updateScore(Member targetMember, Long plusScore){
        //Member member = homeRepository.findOne(targetMember.getId());
        targetMember.setScore(targetMember.getScore()+plusScore);
    }

    @Transactional
    public void updateTotal(){
        homeRepository.findOne(1L).setTotal(homeRepository.findOne(1L).getTotal() + 1L);
    }
  
    public void makeSocketConnection(String nickname) throws Exception {

        System.out.println("nickname : " + nickname);

        URI uri = new URI("ws://localhost:8080/room");
        WebSocketUtil util = new WebSocketUtil(uri, new Draft_6455());

        util.connectBlocking();
    }

  
}
