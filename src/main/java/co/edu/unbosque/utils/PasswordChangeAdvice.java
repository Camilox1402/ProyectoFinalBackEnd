package co.edu.unbosque.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
public class PasswordChangeAdvice {


	public static boolean mustChangePassword(Date fchaUltmaClave, int diasMaximos) {
		if (fchaUltmaClave == null)
			return true;

		LocalDateTime ultimaFecha = fchaUltmaClave.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		long dias = ChronoUnit.DAYS.between(ultimaFecha, LocalDateTime.now());
		return dias >= diasMaximos;
	}

	public static boolean isValidPassword(String password, int minLen, int maxLen) {
		if (password == null)
			return false;
		if (password.length() < minLen || password.length() > maxLen)
			return false;

		boolean tieneMayuscula = password.matches(".*[A-Z].*");
		boolean tieneMinuscula = password.matches(".*[a-z].*");
		boolean tieneNumero = password.matches(".*\\d.*");

		return tieneMayuscula && tieneMinuscula && tieneNumero;
	}

}

