package com.pathhack.nofront.service;

import com.pathhack.nofront.domain.HomeRepository;
import com.pathhack.nofront.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeService {

    private final HomeRepository homeRepository;

    @Transactional
    private Long join(Member member){
        homeRepository.save(member);
        return member.getId();
    }

    @Transactional
    private void updateScore(String nickName, Long plusScore){
        Member member = homeRepository.findOne(nickName);
        member.setScore(member.getScore()+plusScore);

    }
}
