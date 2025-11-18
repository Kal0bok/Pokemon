package Pokemon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public abstract class Pokemons implements Comparable<Pokemons> {

    private String vards;
    private String tips;
    private int limenis;
    private double dziv;
    private double uzbruk;
    private double aizsarg;

    public Pokemons(String vards, String tips, int limenis, double dziv, double uzbruk, double aizsarg) {
        this.vards = vards;
        this.tips = tips;
        this.limenis = limenis;
        this.dziv = dziv;
        this.uzbruk = uzbruk;
        this.aizsarg = aizsarg;
    }


    public String getVards() { 
    	return vards; }
    
    public String getTips() { 
    	return tips; }
    
    public int getLimenis() { 
    	return limenis; }
    
    public double getDziv() { 
    	return dziv; }
    
    public double getUzbruk() { 
    	return uzbruk; }
    
    public double getAizsarg() { 
    	return aizsarg; }


    public void saņBojās(double daudzums) {
    	dziv -= daudzums;
        if (dziv < 0) dziv = 0;
    }

    public double dotBoja() {
        return uzbruk * (limenis / 2.0);
    }

    public double aizsarg() {
        return aizsarg * 0.7;
    }

    public String ipaUzbruk() {
        return vards + " izmanto īpašo uzbrukumu!";
    }

    public String izvadit() {
        return "Vārds: " + vards +
               "\nTips: " + tips +
               "\nLīmenis: " + limenis +
               "\nDzīvības: " + dziv +
               "\nUzbrukums: " + uzbruk +
               "\nAizsardzība: " + aizsarg;
        
    }
    
    public void showProfile() {
    	 JDialog info = new JDialog((JFrame)null, "Pokemon Profile", true);
        info.setSize(350, 450);
        info.setLocationRelativeTo(null);
        info.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        info.setLayout(new BorderLayout());
        info.getContentPane().setBackground(new Color(30, 40, 60));
        info.setResizable(false);

        JLabel title = new JLabel(getVards(), SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(Color.YELLOW);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel stats = new JPanel(new GridLayout(6, 1, 5, 5));
        stats.setBackground(new Color(30, 40, 60));

        stats.add(makeLabel("Pokemona vārds: " + getVards()));
        stats.add(makeLabel("Tips: " + getTips()));
        stats.add(makeLabel("Līmenis: " + getLimenis()));
        stats.add(makeLabel("Dzīvības: " + getDziv()));
        stats.add(makeLabel("Uzbrukums: " + getUzbruk()));
        stats.add(makeLabel("Aizsardzība: " + getAizsarg()));

        JButton close = new JButton("Aizvērt");
        close.addActionListener(e -> {
            info.dispose();            
        });

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(30, 40, 60));
        bottom.add(close);

        info.add(title, BorderLayout.NORTH);
        info.add(stats, BorderLayout.CENTER);
        info.add(bottom, BorderLayout.SOUTH);
        
        info.setVisible(true);
        
    }
    
    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.PLAIN, 16));
        lbl.setForeground(Color.WHITE);
        return lbl;
    }


    public String info() {
        return vards + " (" + tips + ") - Līmenis: " + limenis;
    }

    @Override
    public int compareTo(Pokemons cits) {
        return Double.compare(this.uzbruk, cits.uzbruk);
    }


}
