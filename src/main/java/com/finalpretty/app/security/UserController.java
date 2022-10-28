package com.finalpretty.app.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalpretty.app.Response.ResetPwdResponse;

// @RestController
@Controller
@RequestMapping("/public")
public class UserController {

    @Autowired
    UsersServices usersServices;

    @ResponseBody
    @PostMapping("/api/user/resetpwd")
    public ResetPwdResponse resetPwd(@RequestBody Map<String, String> emailinput) {
        ResetPwdResponse resetPwdResponse = new ResetPwdResponse();
        String result = usersServices.resetPwd(emailinput.get("email"));
        resetPwdResponse.setMessage(result);
        return resetPwdResponse;
    }

    @RequestMapping("/user/confirmresetpwd")
    public String confirmresetpwd(@RequestParam String token) {
        if (!usersServices.confirmresetpwd(token)) {
            return "404";
        } else {
            return "resetPwd";
        }
    }
}
