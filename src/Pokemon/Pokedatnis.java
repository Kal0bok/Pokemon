package Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class Pokedatnis {
	
	private static final String[] tipi = {"Fire", "Psychic"};
    private static final String[] atbilde = {"Jā", "Nē"};
    public static ArrayList<Pokemons> pokemoni = new ArrayList<>();
    public static JFrame mainFrame;
    
    public static Trainer createTrainer(String trenerisImage) {

        String vards = Metodes.virkneParbaud("Ievadi trenera vārdu:");
        if (vards == null) {
        	return null;
        }
        int vecums = (int) Metodes.skaitlaParbaude("Ievadi vecumu (5-99)", 5, 99).doubleValue();
        if (vecums < 0) return null;

        int limenis = (int) Metodes.skaitlaParbaude("Ievadi līmeni (1-10)", 1, 10).doubleValue();
        if (limenis < 0) return null;

        Trainer treneris = new Trainer(vards, vecums, limenis);
        
        return treneris;
    }
    
	
    public static void main(String[] args) {
    	String izvele;
        int izvelesID;
        String[] darbibas = {"Jauns pokemons", "Nodot pokemonu",
                "Pokemonu saraksts", "Pokemona profils", "Kārtot pēc stipruma", "Trenera profils", "Arena cīņas",
                "Apturet programmu"};
        
        
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
            	if(pokemoni.size() == 3) {
            		JOptionPane.showMessageDialog(null, "Jūms ir maksimals pokemonu skaits!");
            		break;
            	}
            	
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

                double atk = (double) Metodes.skaitlaParbaude("Ievadi uzbrukumu (1-10)", 1, 10);
                if (atk < 0) break;

                pokemoni.add(new Pokemons(vards, tips, (int) lvl, hp, atk, def) {
                            @Override
                            public String ipaUzbruk() {
                                return getVards() + " izmanto " + getTips() + " uzbrukumu!";
                            }
                        }
                    );
                
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
					
					JTextArea ta = new JTextArea (str, 15, 45);
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
                        pokemoni.get(id).showProfile(); 	
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nav neviena Pokemona!");
                }
                break;
            	
            case 4:
            	if(pokemoni.size() > 0) {
					String atb = (String) JOptionPane.showInputDialog(null,
							"Kārtot pokemonus pēc spēka augoši?", "Izvēle",
							JOptionPane.INFORMATION_MESSAGE, null, 
							atbilde, atbilde[0]);
					if(atb != null) {
						if(atb.equals("Jā")) {
							 Collections.sort(pokemoni);
							JOptionPane.showMessageDialog(null, 
									"Konti sakārtoti augoši!", "Kārtošana",
									JOptionPane.INFORMATION_MESSAGE);
						}else {
							Collections.sort(pokemoni, Collections.reverseOrder());
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
                
            case 5:
                
                if(MainMenu.aktivTrener == null) {
                	JOptionPane.showMessageDialog(null, "Treneris vel nav izvelets");
                }
                
                if(MainMenu.aktivTrener.getVards().equals("Ash Kečums")) {
					Ash.showAshInfo();
					return;
                }else {
					Leon.showIzvInfo(MainMenu.aktivTrener.getVards(), MainMenu.aktivTrener.getVecums(), MainMenu.aktivTrener.getLimenis());
					return;
                }
                
				case 6:
					new Arena();
	                break;
                
            case 7:
                JOptionPane.showMessageDialog(null, "Programma ir aptureta");
                break;
            }
            
           
        }while (izvelesID != 7);
    }
	
}