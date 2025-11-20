package Pokemon;

import java.awt.Color;

public class ElektriskaisP extends Pokemons {
    
    public ElektriskaisP(String vards, int limenis, double dziv, double uzbruk, double aizsarg) {
        super(vards, "Electric", limenis, dziv, uzbruk, aizsarg);
    }
    
    public ElektriskaisP() {
        super("Pikachu", "Electric", 5, 80, 8.5, 6.0);
    }
    
    @Override
    public String ipaUzbruk() {
        return getVards() + " izmanto Zibens triecienu! ⚡";
    }
    
    @Override
    public double dotBoja() {
        return super.dotBoja() * 1.2;
    }
    
    @Override
    public String izvadit() {
        return super.izvadit() + "\nSpecialitāte: Elektriskie uzbrukumi";
    }
    
    @Override
    public Color getCardColor() {
        return new Color(255, 255, 150); 
    }
}