package Pokemon;

public class ElektriskaisP extends Pokemons {

	public ElektriskaisP() {
        super("Pikachu", "Electric", 5, 100, 5, 100);
    }

    @Override
    public String ipaUzbruk() {
        return getVards() + " use Electric Shock!";
    }
}