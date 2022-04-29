package com.pathhack.nofront.domain;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Count {

    @Id @GeneratedValue
    private Long id;

    private Long count;
}
