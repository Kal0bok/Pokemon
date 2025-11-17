package Pokemon;

public class ElektriskaisP extends Pokemons {

    public ElektriskaisP(String vards, String tips, int limenis, double dziv, double uzbruk, double aizsarg) {
        super(vards, "Electric", limenis, dziv, uzbruk, aizsarg);
    }

    @Override
    public String ipaUzbruk() {
        return getVards() + " use Electric Shock!";
    }
}
