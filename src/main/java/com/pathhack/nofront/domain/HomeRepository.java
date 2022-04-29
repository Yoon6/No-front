package com.pathhack.nofront.domain;


public interface HomeRepository {

    // 입력된 아이디 저장
    public Long save(Member member);

    public Member findOne(Long id);
}
