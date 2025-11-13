package Pokemon;

import java.util.ArrayList;

public class Trainer {
    private String name;
    private int age;
    private int level;
    private ArrayList<Pokemons> pokemons;
    private String imageName;
    
    public Trainer(String name, int age, int level) {
        this(name, age, level, null);
    }

    public Trainer(String name, int age, int level, String imageName) {
        this.name = name;
        this.age = age;
        this.level = level;
        this.pokemons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<Pokemons> getPokemons() {
        return pokemons;
    }

    public void addPokemon(Pokemons p) {
        pokemons.add(p);
    }

    public String treneraInfo() {
        String info = "Treneris: " + name + "\nVecums: " + age + "\nLīmenis: " + level;
        info += "\nPokemoni: ";
        if (pokemons.isEmpty()) {
            info += "Nav pievienots neviens pokemons";
        } else {
            for (Pokemons p : pokemons) {
                info += "\n- " + p.getName() + " (Līmenis " + p.getLevel() + ")";
            }
        }
        return info;
    }
}
