package com.finalpretty.app.registration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.finalpretty.app.Response.RegistrationResponse;
import com.finalpretty.app.security.UsersServices;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(path = "public/api/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    private UsersServices usersServices;

    @PostMapping
    @ResponseBody
    public RegistrationResponse register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    // 驗證信箱是否存在 回傳json
    @PostMapping("/checkEmail")
    @ResponseBody
    public Map<String, Boolean> emailExists(@RequestBody() Map<String, String> email) {
        Map<String, Boolean> m = new HashMap<String, Boolean>();
        boolean emailExists = usersServices.emailExists(email.get("email"));
        m.put("emailExists", emailExists);
        return m;
    }

    @GetMapping(path = "/public/confirm")
    public String confirm(@RequestParam("token") String token) {
        try {
            registrationService.confirmToken(token);
            return "/member/memberInputPage";
        } catch (Exception e) {
            return "404";
        }

    }

}
