package com.saikat.housie.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "HOUSIE_USER")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invite_code", referencedColumnName = "invite_code")
    private Invitation invitation;
    @Column(name = "user_name")
    private String userName;
}
