package Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static Double skaitlaParbaude(String zinojums, double min, double max) {
        String ievade;
        Double skaitlis;
        while (true) {
            ievade = JOptionPane.showInputDialog(null, zinojums,
                    "Datu ievade", JOptionPane.INFORMATION_MESSAGE);
            if (ievade == null)
                return -1.0;
            try {
                skaitlis = Double.parseDouble(ievade);
                if (skaitlis < min || skaitlis > max) {
                    JOptionPane.showMessageDialog(null,
                            "Norādīts nederīgs skaitlis. Jābūt no " + min + " līdz " + max, 
                            "Nekorekti dati", JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                return skaitlis;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Netika ievadīts pareizs skaitlis!", "Nekorekti dati",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    // НОВЫЙ МЕТОД для целых чисел
    public static Integer skaitlaParbaudeInt(String zinojums, int min, int max) {
        String ievade;
        Integer skaitlis;
        while (true) {
            ievade = JOptionPane.showInputDialog(null, zinojums,
                    "Datu ievade", JOptionPane.INFORMATION_MESSAGE);
            if (ievade == null)
                return -1;
            try {
                skaitlis = Integer.parseInt(ievade);
                if (skaitlis < min || skaitlis > max) {
                    JOptionPane.showMessageDialog(null,
                            "Norādīts nederīgs skaitlis. Jābūt no " + min + " līdz " + max, 
                            "Nekorekti dati", JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                return skaitlis;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Netika ievadīts pareizs vesels skaitlis!", "Nekorekti dati",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    public static int pokemonIzvele(ArrayList<Pokemons> pokemoni) {
        if (pokemoni == null || pokemoni.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nav pieejams neviens pokemons!", "Kļūda", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        
        String[] kSaraksts = new String[pokemoni.size()];
        for (int i = 0; i < pokemoni.size(); i++) {
            kSaraksts[i] = (i+1) + ". " + pokemoni.get(i).getVards() + " (" + pokemoni.get(i).getTips() + ") Lv." + pokemoni.get(i).getLimenis();
        }

        String izveletais = (String) JOptionPane.showInputDialog(null,
                "Izvēlies pokemonu: ", "Izvēle", JOptionPane.QUESTION_MESSAGE, null,
                kSaraksts, kSaraksts[0]);

        if (izveletais == null) {
            return -1;
        }
        
        // Находим индекс выбранного покемона
        for (int i = 0; i < kSaraksts.length; i++) {
            if (kSaraksts[i].equals(izveletais)) {
                return i;
            }
        }
        return -1;
    }
    
    // НОВЫЙ МЕТОД для подтверждения действий
    public static boolean apstiprinatDarbibu(String zinojums) {
        int result = JOptionPane.showConfirmDialog(null, zinojums, "Apstiprinājums", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
}