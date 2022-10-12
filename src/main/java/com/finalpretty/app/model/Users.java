package com.finalpretty.app.model;

import javax.persistence.*;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Entity
@NoArgsConstructor
@Getter
@Setter
public class Users {
    
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

    @Column(table = "account")
    private String account;
    
    @Column(table = "password")
    private String password;

    @Column(table = "register_date")
    private String registerDate;

    @Column(table = "change_password_date")
    private String changePasswordDate;
    
    @Column(table = "role")
    private int role;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="fk_member_id", referencedColumnName="id")
    private Member fkMember;
    

}
