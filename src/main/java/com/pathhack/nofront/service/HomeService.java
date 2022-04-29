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
}
