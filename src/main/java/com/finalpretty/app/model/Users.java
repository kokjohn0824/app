package com.finalpretty.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Users {
    
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

    @Column(name = "account")
    private String account;
    
    @Column(name = "password")
    private String password;

    @Column(name = "register_date")
    private String registerDate;

    @Column(name = "change_password_date")
    private String changePasswordDate;
    
    @Column(name = "role")
    private int role;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="fk_member_id", referencedColumnName="id")
    private Member fkMember;
    

}
