package co.edu.unbosque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Email;
import co.edu.unbosque.utils.EmailService;


@Service
public class EmailServiceImpl implements EmailService {

	@Autowired private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}") private String sender;


	@Override
	public String sendSimpleMail(Email email) {
		// TODO Auto-generated method stub
		try {

			SimpleMailMessage mailMessage
			= new SimpleMailMessage();

			mailMessage.setFrom(sender);
			mailMessage.setTo(email.getTo());
			mailMessage.setText(email.getText());
			mailMessage.setSubject(email.getSubject());

			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully...";
		}
		catch (Exception e) {
			 e.printStackTrace();
			return "Error while Sending Mail"+ e.getMessage();
		}
	}


	   @Override
	    public String sendWelcomeEmail(String to, String nombre, String username, String password) {
	        Email email = new Email();
	        email.setTo(to);
	        email.setSubject("Bienvenido a nuestra plataforma");
	        email.setText(
	            "Hola " + nombre + ",\n\n" +
	            "Tus credenciales de acceso son:\n\n" +
	            "Usuario: " + username + "\n" +
	            "Contraseña: " + password + "\n\n" +
	            "Por seguridad, cambia tu contraseña después de iniciar sesión."
	        );
	        
	        return sendSimpleMail(email); 
	    }
}

