package nl.keukentafelmeta.keukentafelmeta.controller;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class GenerateUserToken {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @Test
    void createUser(){
        for(int i=0; i<10; i++) {
            String encodePassword = bCryptPasswordEncoder.encode("keukentafelmeta" + i);
            System.out.println(encodePassword);
        }
    }
}

