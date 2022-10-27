package com.finalpretty.app.registration;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.finalpretty.app.Response.RegistrationResponse;
import com.finalpretty.app.email.EmailSender;
import com.finalpretty.app.model.Users;
import com.finalpretty.app.registration.token.ConfirmationToken;
import com.finalpretty.app.registration.token.ConfirmationTokenServices;
import com.finalpretty.app.security.UserRole;
import com.finalpretty.app.security.UsersServices;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final String ValidEmailRegex = "^[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-z]{2,3}$";
    private final String EMAIL_NOT_VALID = "email not valid";
    private final String EMAIL_ALREADY_CONFIRMED = "email already confirmed";
    private final String TOKEN_NOT_FOUND = "token not found";
    private final String TOKEN_EXPIRED = "token expired";
    // private final String LINK =
    // "https://localhost:8443/api/v1/registration/confirm?token=";
    private final String EMAIL_SEND_SUCCESS = "email send ok!";
    private final String CONFIRMED = "Confirmed";
    private final ConfirmationTokenServices confirmationTokenServices;

    private final UsersServices usersService;
    private final EmailSender emailSender;

    public RegistrationResponse register(RegistrationRequest request) {
        String token;
        RegistrationResponse registrationResponse = new RegistrationResponse();

        // check if eamil is valid
        if (!Pattern.compile(ValidEmailRegex).matcher(request.getEmail()).find()) {
            registrationResponse.setError(EMAIL_NOT_VALID);
            return registrationResponse;
        }
        try {
            token = usersService.signUpUser(
                    new Users(
                            request.getAccount(),
                            request.getEmail(),
                            request.getPassword(),
                            UserRole.USER));
            registrationResponse.setAccount(request.getAccount());
            registrationResponse.setEmail(request.getEmail());
            registrationResponse.setToken(token);

        } catch (IllegalStateException e) {
            registrationResponse.setError(e.getMessage());
            return registrationResponse;
        }
        emailSender.verificationEmailsend(request.getEmail(), token);

        return registrationResponse;
    }

    @Transactional
    public void confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenServices.getToken(token)
                .orElseThrow(() -> new IllegalStateException(TOKEN_NOT_FOUND));
        if (confirmationToken.getConfirmedAt() != null)
            throw new IllegalStateException(EMAIL_ALREADY_CONFIRMED);

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException(TOKEN_EXPIRED);
        }
        confirmationTokenServices.setConfirmedAt(token);
        usersService.enableUser(confirmationToken.getUsers().getEmail());

    }

}
