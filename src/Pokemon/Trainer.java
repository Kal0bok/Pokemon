package Pokemon;

import java.util.ArrayList;

public class Trainer {
    private String vards;
    private int vecums;
    private int limenis;
    private ArrayList<Pokemons> pokemons;

    public Trainer(String vards, int vecums, int limenis) { 
        this.vards = vards;
        this.vecums = vecums;
        this.limenis = limenis;
        this.pokemons = new ArrayList<>();
    }

    public String getVards() {
        return vards;
    }

    public int getVecums() {
        return vecums;
    }

    public int getLimenis() { 
        return limenis;
    }

    public ArrayList<Pokemons> getPokemons() {
        return pokemons;
    }

    public void addPokemon(Pokemons p) {
        pokemons.add(p);
    }

    public void removePokemon(Pokemons p) {
        pokemons.remove(p);
    }

    public String treneraInfo() {
        String info = "Treneris: " + vards + "\nVecums: " + vecums + "\nLīmenis: " + limenis;
        info += "\nPokemoni: ";
        if (pokemons.isEmpty()) {
            info += "Nav pievienots neviens pokemons";
        } else {
            for (Pokemons p : pokemons) {
                info += "\n- " + p.getVards() + " (Līmenis " + p.getLimenis() + ")";
            }
        }
        return info;
    }

    public String getHtmlInfo() {
        StringBuilder html = new StringBuilder();
        html.append("<html><body style='width: 300px;'>");
        html.append("<h3 style='color: #3366CC;'>").append(vards).append("</h3>");
        html.append("<p><b>Vecums:</b> ").append(vecums).append("</p>");
        html.append("<p><b>Līmenis:</b> ").append(limenis).append("</p>");
        html.append("<p><b>Pokemoni:</b> ").append(pokemons.size()).append("</p>");
        html.append("</body></html>");
        return html.toString();
    }
}