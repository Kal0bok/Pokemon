package Pokemon;

public class ElektriskaisP extends Pokemons {

    public ElektriskaisP() {
        super("Pikachu", "Electric", 5, 100, 8, 80);
    }

    @Override
    public String ipaUzbruk() {
        return getVards() + " izmanto Zibens triecienu!";
    }
}