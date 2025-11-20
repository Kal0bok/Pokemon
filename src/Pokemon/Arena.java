package Pokemon;

import javax.swing.*;
import java.awt.*;

public class Arena extends JFrame {
    private Pokemons pokemon1, pokemon2;
    
    public Arena() {
        super("Pokémon Arena");
        initializeArena();
    }
    
    private void initializeArena() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        if (Pokedatnis.pokemoni.size() < 2) {
            JOptionPane.showMessageDialog(this, "Nepietiek pokemonu cīņai! Vajag vismaz 2 pokemonus.");
            dispose();
            return;
        }
        
        String[] options = new String[Pokedatnis.pokemoni.size()];
        for (int i = 0; i < Pokedatnis.pokemoni.size(); i++) {
            options[i] = Pokedatnis.pokemoni.get(i).toString();
        }
        
        String choice1 = (String) JOptionPane.showInputDialog(this,
            "Izvēlieties pirmo pokemonu:", "Cīņas sākums",
            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        if (choice1 == null) {
            dispose();
            return;
        }
        
        String choice2 = (String) JOptionPane.showInputDialog(this,
            "Izvēlieties otro pokemonu:", "Cīņas sākums",
            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        if (choice2 == null) {
            dispose();
            return;
        }
        
        for (Pokemons p : Pokedatnis.pokemoni) {
            if (p.toString().equals(choice1)) pokemon1 = p;
            if (p.toString().equals(choice2)) pokemon2 = p;
        }
        
        if (pokemon1 == pokemon2) {
            JOptionPane.showMessageDialog(this, "Nevar cīnīties ar to pašu pokemonu!");
            dispose();
            return;
        }
        
        startBattle();
    }
    
    private void startBattle() {
        JPanel battlePanel = new JPanel(new GridLayout(1, 2, 20, 20));
        battlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        battlePanel.setBackground(new Color(240, 240, 240));
        
        JPanel p1Panel = createPokemonBattlePanel(pokemon1, pokemon2);
        JPanel p2Panel = createPokemonBattlePanel(pokemon2, pokemon1);
        
        battlePanel.add(p1Panel);
        battlePanel.add(p2Panel);
        
        add(battlePanel, BorderLayout.CENTER);
        
        JButton startBattleBtn = new JButton("SĀKT CĪŅU!");
        startBattleBtn.setFont(new Font("Arial", Font.BOLD, 18));
        startBattleBtn.setBackground(Color.RED);
        startBattleBtn.setForeground(Color.WHITE);
        startBattleBtn.addActionListener(e -> conductBattle());
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(startBattleBtn);
        add(bottomPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private JPanel createPokemonBattlePanel(Pokemons pokemon, Pokemons opponent) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(pokemon.getCardColor());
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel nameLabel = new JLabel(pokemon.getVards(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        JTextArea statsArea = new JTextArea();
        statsArea.setText(pokemon.izvadit());
        statsArea.setEditable(false);
        statsArea.setBackground(pokemon.getCardColor().brighter());
        
        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(statsArea, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void conductBattle() {
        StringBuilder battleLog = new StringBuilder();
        battleLog.append("=== POKÉMON CĪŪŅA SĀKAS ===\n\n");
        
        Pokemons attacker = pokemon1;
        Pokemons defender = pokemon2;
        
        int round = 1;
        while (pokemon1.getDziv() > 0 && pokemon2.getDziv() > 0 && round <= 10) {
            battleLog.append("*** RUNDE " + round + " ***\n");
            
            double damage = attacker.dotBoja() - defender.aizsarg();
            if (damage < 1) damage = 1;
            
            defender.saņBojās(damage);
            
            battleLog.append(attacker.getVards() + " uzbruk: " + attacker.ipaUzbruk() + "\n");
            battleLog.append(String.format("Dati bojājumi: %.1f\n", damage));
            battleLog.append(defender.getVards() + " atlikušās dzīvības: " + 
                String.format("%.1f", defender.getDziv()) + "\n\n");
            
            if (defender.getDziv() <= 0) {
                battleLog.append("*** " + attacker.getVards() + " UZVAR! ***\n");
                break;
            }
            
            Pokemons temp = attacker;
            attacker = defender;
            defender = temp;
            round++;
        }
        
        if (pokemon1.getDziv() > 0 && pokemon2.getDziv() > 0) {
            battleLog.append("*** CĪŅA BEIDZAS NEIZŠĶIRTĀ! ***\n");
        }
        
        JTextArea battleArea = new JTextArea(battleLog.toString(), 20, 50);
        battleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(battleArea);
        
        JOptionPane.showMessageDialog(this, scrollPane, "Cīņas Rezultāti", 
            JOptionPane.INFORMATION_MESSAGE);
        
        dispose();
    }
}