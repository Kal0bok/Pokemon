package Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class Pokedatnis {
	
	private static final String[] tipi = {"Electric", "Fire", "Water", "Grass", "Psychic"};
    private static final String[] atbilde = {"Jā", "Nē"};
    
    public static Trainer createTrainer() {

        String tVards = Metodes.virkneParbaud("Ievadi trenera vārdu:");
        if (tVards == null) {
        	return null;
        }
        int vecums = (int) Metodes.skaitlaParbaude("Ievadi vecumu (5-99)", 5, 99).doubleValue();
        if (vecums < 0) return null;

        int limenis = (int) Metodes.skaitlaParbaude("Ievadi līmeni (1-10)", 1, 10).doubleValue();
        if (limenis < 0) return null;

        Trainer treneris = new Trainer(tVards, vecums, limenis);

        JOptionPane.showMessageDialog(null, "Treneris veiksmīgi izveidots!");
		return treneris;
    }
	
    public static void main(String[] args) {
    	String izvele;
        int izvelesID;
        String[] darbibas = {"Jauns pokemons", "Nodot pokemonu",
                "Pokemonu saraksts", "Pokemona profils", "Kārtot pēc stipruma", "Trenera profils", "Arena cīņas",
                "Apturet programmu"};
        
        ArrayList<Pokemons> pokemoni = new ArrayList<>();
        
        do {
            izvele = (String) JOptionPane.showInputDialog(
                    null, "Izvēlies darbību",
                    "Izvēlne",
                    JOptionPane.QUESTION_MESSAGE,
                    null, darbibas, darbibas[0]);

            if (izvele == null) break;

            izvelesID = Arrays.asList(darbibas).indexOf(izvele);

            switch (izvelesID) {


            case 0:
            	String vards = Metodes.virkneParbaud("Ievadi Pokemona vārdu:");
                if (vards == null) break;

                String tips = (String) JOptionPane.showInputDialog(null,
                        "Izvēlies tipu:", "Tips",
                        JOptionPane.QUESTION_MESSAGE, null, tipi, tipi[0]);
                if (tips == null) break;

                double lvl = (double) Metodes.skaitlaParbaude("Ievadi līmeni (1-50)", 1, 50);
                if (lvl < 0) break;

                double hp = (double) Metodes.skaitlaParbaude("Ievadi HP (1-200)", 1, 200);
                if (hp < 0) break;

                double def = (double) Metodes.skaitlaParbaude("Ievadi aizsardzību (1-100)", 1, 100);
                if (def < 0) break;

                double atk = (double) Metodes.skaitlaParbaude("Ievadi uzbrukumu (1-100)", 1, 100);
                if (atk < 0) break;

                pokemoni.add(new Pokemons(vards, tips, lvl, hp, atk, def));
                JOptionPane.showMessageDialog(null, "Pokemon pievienots!");
                break;
            	
            case 1:
                if (pokemoni.size() > 0) {
                    int id = Metodes.pokemonIzvele(pokemoni);
                    if (id >= 0) {
                        pokemoni.remove(id);
                        JOptionPane.showMessageDialog(null, "Pokemonы nodots!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nav neviena Pokemona!");
                }
                break;
            	
            case 2:
            	if(pokemoni.size() > 0) { 
					String str = "Pokemonu skaits: "+pokemoni.size()+
							"\n_________________________________\n";
					for(int i=0; i<pokemoni.size(); i++) {
						str += ((Pokemons)pokemoni.get(i)).izvadit()+
							"\n_________________________________\n";
					}
					
					JTextArea ta = new JTextArea (str, 10, 40);
					ta.setEditable(false);
					JScrollPane sp = new JScrollPane(ta);
					sp.setVerticalScrollBarPolicy(
							ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					JOptionPane.showMessageDialog(ta, sp, "Saraksts",
							JOptionPane.PLAIN_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Nav neviena Pokemona!",
							"Kļūda", JOptionPane.ERROR_MESSAGE);
					break;
				}
                break;
            	
            case 3:
                if (pokemoni.size() > 0) {
                    int id = Metodes.pokemonIzvele(pokemoni);
                    if (id >= 0) {
                        JOptionPane.showMessageDialog(null, pokemoni.get(id).display());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nav neviena Pokemona!");
                }
                break;
            	
            case 4:
                if (pokemoni.size() > 0) {
                    String atb = (String) JOptionPane.showInputDialog(null,
                            "Kārtot pēc spēka augoši?",
                            "Izvēle",
                            JOptionPane.QUESTION_MESSAGE,
                            null, atbilde, atbilde[0]);

                    if (atb != null) {
                        if (atb.equals("Jā")) Collections.sort(pokemoni);
                        else Collections.sort(pokemoni, Collections.reverseOrder());

                        JOptionPane.showMessageDialog(null, "Pokemoni sakārtoti!");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Nav ko kārtot!");
                }
                break;
                
            case 5:
                JOptionPane.showMessageDialog(null, "Tev vel nav trenera.");
                break;
            	
            case 6:
                JOptionPane.showMessageDialog(null, "Arena cīņa vēl nav izveidota.");
                break;
                
            case 7:
                JOptionPane.showMessageDialog(null, "Programma ir aptureta");
                break;
            }
            
           
        }while (izvelesID != 7);
    }
	
}