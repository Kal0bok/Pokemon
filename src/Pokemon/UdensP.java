package Pokemon;

import java.awt.Color;

public class UdensP extends Pokemons {
    
    public UdensP(String vards, int limenis, double dziv, double uzbruk, double aizsarg) {
        super(vards, "Water", limenis, dziv, uzbruk, aizsarg);
    }
    
    public UdensP() {
        super("Squirtle", "Water", 5, 85, 7.0, 7.5);
    }
    
    @Override
    public String ipaUzbruk() {
        return getVards() + " izmanto Ūdens šāvienu! 💧";
    }
    
    @Override
    public double aizsarg() {
        // Водные покемоны имеют лучшую защиту
        return super.aizsarg() * 1.3;
    }
    
    @Override
    public String izvadit() {
        return super.izvadit() + "\nSpecialitāte: Ūdens aizsardzība";
    }
    
    @Override
    public Color getCardColor() {
        return new Color(150, 200, 255); // Голубой для водных
    }
}