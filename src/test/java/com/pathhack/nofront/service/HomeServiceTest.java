package com.pathhack.nofront.service;

import com.pathhack.nofront.domain.HomeRepositoryImp;
import com.pathhack.nofront.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class HomeServiceTest {

    @Autowired
    HomeRepositoryImp homeRepository;

    @Autowired
    HomeService homeService;

    @Test
    @Transactional
    @Rollback(false)
    public void join() throws Exception{
        //Given
        Member member1 = new Member();
        Member member2 = new Member();
        Member member3 = new Member();
        member1.setNickName("kim");
        member2.setNickName("choi");
        member3.setNickName("park");


        //When
        Long saveId1 = homeService.join(member1);
        Long saveId2 = homeService.join(member2);
        Long saveId3 = homeService.join(member3);
        homeService.updateScore(member1, 3L);
        homeService.updateScore(member2, 2L);
        homeService.updateScore(member3, 1L);
        //Then
        assertEquals(3L, homeRepository.findOne(member1.getId()).getScore());
        assertEquals(2L, homeRepository.findOne(member2.getId()).getScore());
        assertEquals(1L, homeRepository.findOne(member3.getId()).getScore());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateTotal() throws Exception{
        //Given
        Member member1 = new Member();
        Member member2 = new Member();
        Member member3 = new Member();
        member1.setNickName("kim");
        member2.setNickName("choi");
        member3.setNickName("park");


        //When
        Long saveId1 = homeService.join(member1);
        homeService.updateTotal(); // 소켓에서 들어온거 신호 올때마다
        Long saveId2 = homeService.join(member2);
        homeService.updateTotal();
        Long saveId3 = homeService.join(member3);
        homeService.updateTotal();

        //Then
        assertEquals(3L, homeRepository.findOne(1L).getTotal());
    }
}