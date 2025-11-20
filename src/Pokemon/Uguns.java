package Pokemon;

import java.awt.Color;

public class Uguns extends Pokemons {
    
    public Uguns(String vards, int limenis, double dziv, double uzbruk, double aizsarg) {
        super(vards, "Fire", limenis, dziv, uzbruk, aizsarg);
    }
    
    @Override
    public String ipaUzbruk() {
        return getVards() + " izmanto Uguns liesmas! 🔥";
    }
    
    @Override
    public double dotBoja() {
        return super.dotBoja() * 1.5;
    }
    
    @Override
    public String izvadit() {
        return super.izvadit() + "\nSpecialitāte: Jaudīgi uguns uzbrukumi";
    }
    
    @Override
    public Color getCardColor() {
        return new Color(255, 150, 150); 
    }
}