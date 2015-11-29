package be.ephec.application;
import java.util.Calendar;

public class Console {
	/**
	 * @return Chaîne de caractères représentant l'invite de commande comprenant l'heure suivi du signe >
	 */
	public static String getCommandPrompt(){
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		return hour+":"+minute+":"+second+"> ";
	}
}
