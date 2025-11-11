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
	
    public static void main(String[] args) {
    	String izvele;
        int izvelesID;
        String[] darbibas = {"Jauns pokemons", "Nodot pokemonu",
                "Pokemonu saraksts", "Pokemona profils", "Kārtot pēc stipruma", "Arena cīņas",
                "Apturet programmu"};
        
        ArrayList<Pokemon> pokemoni = new ArrayList<>();
        
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
            	String vards = Metodes.virkneParbaud("Ievadi Pokémona vārdu:");
                if (vards == null) break;

                String tips = (String) JOptionPane.showInputDialog(null,
                        "Izvēlies tipu:", "Tips",
                        JOptionPane.QUESTION_MESSAGE, null, tipi, tipi[0]);
                if (tips == null) break;

                int lvl = (int) Metodes.skaitlaParbaude("Ievadi līmeni (1-50)", 1, 50);
                if (lvl < 0) break;

                int hp = (int) Metodes.skaitlaParbaude("Ievadi HP (1-200)", 1, 200);
                if (hp < 0) break;

                int def = (int) Metodes.skaitlaParbaude("Ievadi aizsardzību (1-100)", 1, 100);
                if (def < 0) break;

                int atk = (int) Metodes.skaitlaParbaude("Ievadi uzbrukumu (1-100)", 1, 100);
                if (atk < 0) break;

                pokemoni.add(new Pokemon(vards, tips, lvl, hp, atk, def));
                JOptionPane.showMessageDialog(null, "Pokemon sekmīgi pievienots!");
                break;
            	
            case 1:
            	if (konti.size() > 0) {
                    int kontID = Metodes.kontaIzvele(konti);
                    if (kontID >= 0) {
                    konti.remove(kontID);
                    JOptionPane.showMessageDialog(null, "Veiksmīgi izdzēsts konts",
                            "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Nav ievadīts neviens konts",
                            "Kļūda", JOptionPane.ERROR_MESSAGE);
                }
            }
            	break;
            	
            case 2:
            	if (konti.size() > 0) {
                    String str = "Kontu skaits: " + konti.size() +
                            "\n_________________________________\n";
                    
                    for (norkarte karte : konti) {
                    	String veids;
                    	if (karte instanceof kredkarte) {
                    	    veids = "Kredītkarte";
                    	} else {
                    	    veids = "Norēķinu karte";
                    	}
                        str += "Kartes veids: " + veids + "\n";
                        str += karte.izvadit() + "\n";
                        str += "_________________________________\n";
                    }

                    JTextArea ta = new JTextArea(str, 10, 40);
                    ta.setEditable(false);
                    JScrollPane sp = new JScrollPane(ta);
                    sp.setVerticalScrollBarPolicy(
                            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                    JOptionPane.showMessageDialog(ta, sp, "Kontu saraksts",
                            JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Nav ievadīts neviens konts",
                            "Kļūda", JOptionPane.ERROR_MESSAGE);
                }
            	break;
            	
            case 3:
            	if(konti.size() > 0) {
					String atb = (String) JOptionPane.showInputDialog(null,
							"Kārtot kontus pēc atlikuma augoši?", "Izvēle",
							JOptionPane.INFORMATION_MESSAGE, null, 
							atbilde, atbilde[0]);
					if(atb != null) {
						if(atb.equals("Jā")) {
							 Collections.sort(konti);
							JOptionPane.showMessageDialog(null, 
									"Konti sakārtoti augoši!", "Kārtošana",
									JOptionPane.INFORMATION_MESSAGE);
						}else {
							Collections.sort(konti, Collections.reverseOrder());
							JOptionPane.showMessageDialog(null, 
									"Konti sakārtoti dilstoši!", "Kārtošana",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Nav ievadīts neviens konts",
							"Kļūda", JOptionPane.ERROR_MESSAGE);
					break;
				}
				break;
            	
            case 4:
            	bankomats.apkalpo(konti);
            	break;
            	
            case 5:
            	JOptionPane.showMessageDialog(null, "Programma apturēta", "Apturēta",
                        JOptionPane.PLAIN_MESSAGE);
            	break;
            
            }
  
        }while (izvelesID != 5);
    }
	
}