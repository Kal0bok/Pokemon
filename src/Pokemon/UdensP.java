package Pokemon;

public class UdensP extends Pokemons {

    public UdensP(String vards, String tips, int limenis, double dziv, double uzbruk, double aizsarg) {
        super(vards, "Water", limenis, dziv, uzbruk, aizsarg);
    }

    @Override
    public String ipaUzbruk() {
        return getVards() + " use Water Splash!";
    }
}
