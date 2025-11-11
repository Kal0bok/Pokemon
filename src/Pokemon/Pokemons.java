package Pokemon;

public class Pokemons implements Comparable<Pokemons> {
    private String name;
    private String type;
    private int level;
    private double hp;
    private double attack;
    private double defense;

    public Pokemons(String name, String type, double level, double hp, double attack, double defense) {
        this.name = name;
        this.type = type;
        this.level = (int) level;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
    }
        
        @Override
        public int compareTo(Pokemons other) {
            return Integer.compare(this.level, other.level);
        }
    }
