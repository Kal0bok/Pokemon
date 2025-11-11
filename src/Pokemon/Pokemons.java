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
    
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public double getHp() {
        return hp;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public String display() {
        return "Name: " + name +
               "\nType: " + type +
               "\nLevel: " + level +
               "\nHP: " + hp +
               "\nAttack: " + attack +
               "\nDefense: " + defense;
    }

    public String izvadit() {
        return name + " (" + type + ") - Level: " + level;
    }
        
        @Override
        public int compareTo(Pokemons other) {
            return Integer.compare(this.level, other.level);
        }
    }
