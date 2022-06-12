package com.rk.service;

import com.rk.dto.RegisterRequest;
import com.rk.exception.SpringStoreException;
import com.rk.model.User;
import com.rk.model.VerificationToken;
import com.rk.repository.UserRepository;
import com.rk.repository.VerificationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AuthService {
      @Autowired
      private PasswordEncoder passwordEncoder;

      @Autowired
      private UserRepository userRepository;

      @Autowired
      private VerificationTokenRepository verificationTokenRepository;

      @Autowired
      private MailContentBuilder mailContentBuilder;

      @Autowired
      private MailService mailService;

      @Transactional
       public void signup(RegisterRequest registerRequest){
         User user=new User();
         user.setEmail(registerRequest.getEmail());
         user.setUsername(registerRequest.getUsername());
         user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
         user.setEnabled(false);
         userRepository.save(user);

         String token = generateVerificationToken(user);
           String message = mailContentBuilder.build("Thank you for signing up to Spring Store, " +
                   "please click on the below url to activate your account : "
                   + "http://localhost:8080/api/auth/accountVerification/" + token);

           mailService.sendMail(user.getEmail(), message);
          // log.info("Activation email sent!!");
       }

       private String generateVerificationToken(User user) {
        String token= UUID.randomUUID().toString();
        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);

        return token;
       }


    public void verifyAccount(String token) {
        Optional<VerificationToken> vertoken=verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(vertoken.orElseThrow(()->new SpringStoreException("Invalid Token")));
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
          String username=verificationToken.getUser().getUsername();
          User user=userRepository.findByUsername(username).orElseThrow(()->new SpringStoreException("Username not found"));
          user.setEnabled(true);
          userRepository.save(user);
    }
}
