package Pokemon;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Metodes {
	
	public static String virkneParbaud(String zinojums) {
	    String virkne;
	    while (true) {
	        virkne = JOptionPane.showInputDialog(null, zinojums,
	                "Datu ievade", JOptionPane.INFORMATION_MESSAGE);
	        if (virkne == null) {
	            return null; 
	        }
	        if (Pattern.matches("^[\\p{L} .]+$", virkne)) {
	            return virkne; 
	        } else {
	            JOptionPane.showMessageDialog(null,
	                    "Nepareizs formāts! Ievadiet tikai burtus un atstarpes.", 
	                    "Nekorekti dati", JOptionPane.WARNING_MESSAGE);
	        }
	    }
	}

}
