package Pokemon;

public class UdensP extends Pokemons {

	public UdensP() {
        super("Squirtle", "Water", 5, 100, 5, 100);
    }

    @Override
    public String ipaUzbruk() {
        return getVards() + " use Water Splash!";
    }
}
