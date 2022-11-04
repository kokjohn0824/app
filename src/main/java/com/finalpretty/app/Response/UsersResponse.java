package com.finalpretty.app.Response;

import com.finalpretty.app.security.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UsersResponse {
    private Integer users_id; // 帳號ID
    private UserRole userrole; // 權限
    private String account; // 帳號
    private String email; // email
    private Boolean locked; // 停權
}