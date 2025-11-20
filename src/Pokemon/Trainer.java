package Pokemon;

import java.util.ArrayList;

public class Trainer {
    private String vards;
    private int vecums;
    private double limenis;
    private ArrayList<Pokemons> pokemons;

    public Trainer(String vards, int vecums, double level) {
        this.vards = vards;
        this.vecums = vecums;
        this.limenis = level;
        this.pokemons = new ArrayList<>();
    }

	public String getVards() {
        return vards;
    }

    public int getVecums() {
        return vecums;
    }

    public double getLimenis() {
        return limenis;
    }

    public ArrayList<Pokemons> getPokemons() {
        return pokemons;
    }

    public void addPokemon(Pokemons p) {
        pokemons.add(p);
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
}
