package Pokemon;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.*;

public class Pokedatnis {
    
    private static final String[] tipi = {"Electric", "Water", "Fire", "Psychic"};
    private static final String[] atbilde = {"Jā", "Nē"};
    public static ArrayList<Pokemons> pokemoni = new ArrayList<>();
    public static JFrame mainFrame;
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
            System.err.println("Nevar iestatīt sistēmas izskatu: " + e.getMessage());
        }
        
        String izvele;
        int izvelesID;
        String[] darbibas = {
            "Jauns pokemons", "Nodot pokemonu", "Pokemonu saraksts", 
            "Pokemona profils", "Kārtot pēc stipruma", "Trenera profils", 
            "Arena cīņas", "Apturet programmu"
        };
        
        do {
            izvele = (String) JOptionPane.showInputDialog(
                    null, "Izvēlies darbību", "Izvēlne",
                    JOptionPane.QUESTION_MESSAGE, null, darbibas, darbibas[0]);

            if (izvele == null) break;

            izvelesID = Arrays.asList(darbibas).indexOf(izvele);

            switch (izvelesID) {
                case 0: 
                    if(pokemoni.size() >= 6) {
                        JOptionPane.showMessageDialog(null, 
                            "Jūms ir maksimālais pokemonu skaits (6)!", 
                            "Kļūda", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    izveidotJaunuPokemonu();
                    break;
                    
                case 1: 
                    nodotPokemonu();
                    break;
                    
                case 2: 
                    paradiPokemonuSarakstu();
                    break;
                    
                case 3: 
                    paradiPokemonaProfilu();
                    return;
                    
                case 4: 
                    kartotPokemonus();
                    break;
                    
                case 5: 
                    paradiTreneraProfilu();
                    break;
                    
                case 6: 
                    new Arena();
                    break;
                    
                case 7: 
                    JOptionPane.showMessageDialog(null, 
                        "Paldies par spēli! Programma ir apturēta.");
                    System.exit(0);
                    break;
            }
            
        } while (izvelesID != 7);
    }
    
    private static void izveidotJaunuPokemonu() {
        String vards = Metodes.virkneParbaud("Ievadi Pokemona vārdu:");
        if (vards == null) return;

        String tips = (String) JOptionPane.showInputDialog(null,
                "Izvēlies tipu:", "Tips",
                JOptionPane.QUESTION_MESSAGE, null, tipi, tipi[0]);
        if (tips == null) return;

        Integer lvl = Metodes.skaitlaParbaudeInt("Ievadi līmeni (1-50)", 1, 50);
        if (lvl == null || lvl < 0) return;

        Double hp = Metodes.skaitlaParbaude("Ievadi HP (1-200)", 1, 200);
        if (hp == null || hp < 0) return;

        Double atk = Metodes.skaitlaParbaude("Ievadi uzbrukumu (1-10)", 1, 10);
        if (atk == null || atk < 0) return;

        Double def = Metodes.skaitlaParbaude("Ievadi aizsardzību (1-100)", 1, 100);
        if (def == null || def < 0) return;

        Pokemons jaunsPokemons = null;
        switch (tips) {
            case "Electric":
                jaunsPokemons = new ElektriskaisP(vards, lvl, hp, atk, def);
                break;
            case "Water":
                jaunsPokemons = new UdensP(vards, lvl, hp, atk, def);
                break;
            case "Fire":
                jaunsPokemons = new Uguns(vards, lvl, hp, atk, def);
                break;
            default:
                jaunsPokemons = new Pokemons(vards, tips, lvl, hp, atk, def) {
                    @Override
                    public String ipaUzbruk() {
                        return getVards() + " izmanto " + getTips() + " uzbrukumu!";
                    }
                    
                    @Override
                    public Color getCardColor() {
                        return Color.LIGHT_GRAY;
                    }
                };
        }
        
        pokemoni.add(jaunsPokemons);
        JOptionPane.showMessageDialog(null, 
            "Pokemons " + vards + " veiksmīgi pievienots!\n" +
            "Tips: " + tips + "\n" +
            "Līmenis: " + lvl, "Veiksmīgi!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static void nodotPokemonu() {
        if (pokemoni.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nav neviena Pokemona!");
            return;
        }
        
        int id = Metodes.pokemonIzvele(pokemoni);
        if (id >= 0) {
            Pokemons pokemons = pokemoni.get(id);
            if (Metodes.apstiprinatDarbibu("Vai tiešām vēlaties nodot pokemonu " + pokemons.getVards() + "?")) {
                pokemoni.remove(id);
                JOptionPane.showMessageDialog(null, "Pokemons " + pokemons.getVards() + " nodots!");
            }
        }
    }
    
    private static void paradiPokemonuSarakstu() {
        if (pokemoni.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nav neviena Pokemona!", 
                "Kļūda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        StringBuilder str = new StringBuilder();
        str.append("=== POKEMONU SARAKSTS ===\n");
        str.append("Kopā: ").append(pokemoni.size()).append(" pokemoni\n");
        str.append("=================================\n");
        
        for (int i = 0; i < pokemoni.size(); i++) {
            str.append(i + 1).append(". ").append(pokemoni.get(i).izvadit());
            str.append("\n=================================\n");
        }
        
        JTextArea ta = new JTextArea(str.toString(), 20, 50);
        ta.setEditable(false);
        ta.setFont(new Font("Consolas", Font.PLAIN, 12));
        JScrollPane sp = new JScrollPane(ta);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        JOptionPane.showMessageDialog(null, sp, "Pokemonu Saraksts", 
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private static void paradiPokemonaProfilu() {
        if (pokemoni.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nav neviena Pokemona!");
            return;
        }
        
        int id = Metodes.pokemonIzvele(pokemoni);
        if (id >= 0) {
            pokemoni.get(id).showProfile();
        }
    }
    
    private static void kartotPokemonus() {
        if (pokemoni.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nav neviena Pokemona!", 
                "Kļūda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String atb = (String) JOptionPane.showInputDialog(null,
                "Kārtot pokemonus pēc spēka augoši?", "Izvēle",
                JOptionPane.QUESTION_MESSAGE, null, atbilde, atbilde[0]);
        
        if (atb != null) {
            if (atb.equals("Jā")) {
                Collections.sort(pokemoni);
                JOptionPane.showMessageDialog(null, "Pokemoni sakārtoti augoši!");
            } else {
                Collections.sort(pokemoni, Collections.reverseOrder());
                JOptionPane.showMessageDialog(null, "Pokemoni sakārtoti dilstoši!");
            }
        }
    }
    
    private static void paradiTreneraProfilu() {
        if (MainMenu.aktivTrener == null) {
            JOptionPane.showMessageDialog(null, "Treneris vēl nav izvēlēts!");
            return;
        }
        
        if (MainMenu.aktivTrener.getVards().equals("Ash Ketchum")) {
            Ash.showAshInfo();
        } else {

            Leon.showIzvInfo(MainMenu.aktivTrener.getVards(), 
                           MainMenu.aktivTrener.getVecums(), 
                           MainMenu.aktivTrener.getLimenis());
        }
        
        if (MainMenu.aktivTrener.getVards().equals("Ash Ketchum")) {
            Ash.showAshInfo();
        } else {
            Leon.showIzvInfo(MainMenu.aktivTrener.getVards(), 
                           MainMenu.aktivTrener.getVecums(), 
                           MainMenu.aktivTrener.getLimenis());
        }
    }
}