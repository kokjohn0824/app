package com.finalpretty.app.security;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.finalpretty.app.email.EmailSender;
import com.finalpretty.app.model.Users;
import com.finalpretty.app.registration.token.ConfirmationToken;
import com.finalpretty.app.registration.token.ConfirmationTokenServices;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsersServices implements UserDetailsService {

    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final static String EMAIL_TAKEN = "user email is already taken!";
    private final String LINK = "https://localhost:8443/api/v1/registration/confirm?token=";

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailSender emailSender;
    private final UsersRepository usersRepository;
    private final ConfirmationTokenServices confirmationTokenServices;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {

        return usersRepository.findByEmail(input).or(() -> usersRepository.findByaccount(input))

                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, input)));
    }

    public String signUpUser(Users users) {
        boolean userExists = usersRepository.findByEmail(users.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException(EMAIL_TAKEN);
        }
        String encodedPassword = bCryptPasswordEncoder.encode(users.getPassword());
        users.setPassword((encodedPassword));
        usersRepository.save(users);

        return initToken(users);

    }

    public int enableUser(String email) {
        return usersRepository.enableUser(email);
    }

    public String resendToken(String email) {
        String token = initToken(usersRepository.findByEmail(email).orElseThrow());
        emailSender.send(email, String.format("<a>%s</a>", LINK + token));
        return "EMAIL_SEND_SUCCESS";
    }

    private String initToken(Users users) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                users);

        confirmationTokenServices.saveConfirmationToken(confirmationToken);
        return token;
    }

}
