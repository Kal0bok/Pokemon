package Pokemon;

public class ElektriskaisP extends Pokemons {

	public ElektriskaisP() {
        super("Pikachu", "Electric", 5, 100, 5, 100);
    }

    @Override
    public String ipaUzbruk() {
        return getVards() + " use Electric Shock!";
    }

	public static void showPikachu(String string, int i, int j) {
		// TODO Auto-generated method stub
		
	}
}