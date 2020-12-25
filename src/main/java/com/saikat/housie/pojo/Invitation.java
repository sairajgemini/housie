package com.saikat.housie.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "INVITATION")
@Data
@NoArgsConstructor
public class Invitation {
    @Id
    @Column(name = "invite_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inviteCode;
    @OneToMany(cascade = CascadeType.ALL)
    private List<User> userList = new ArrayList<>();
}
