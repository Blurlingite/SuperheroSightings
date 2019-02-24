/*
Now that the system is configured to use hashed passwords, we must update the existing passwords in our database — if we don’t, we’ll be locked out of the system.

Create a new Java class called PWEnc in the controller package and insert the following code
 */
package com.sg.superherosightings.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author vishnukdawah
 */
public class PWEnc {

    public static void main(String[] args) {
        String clearTxtPw = "user";
        // BCrypt
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPw = encoder.encode(clearTxtPw);
        System.out.println(hashedPw);
        
        
        

    }
}
