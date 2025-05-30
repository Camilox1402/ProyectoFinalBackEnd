package co.edu.unbosque.utils;

import co.edu.unbosque.entity.Email;

public interface EmailService {

    String sendSimpleMail(Email email);
    String sendWelcomeEmail(String to, String nombre, 
			String username, String password);
}
