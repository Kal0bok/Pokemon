package Pokemon;

public abstract class Pokemons implements Comparable<Pokemons> {

    private String vards;
    private String tips;
    private int limenis;
    private double dziv;
    private double uzbruk;
    private double aizsarg;

    public Pokemons(String vards, String tips, int limenis, double dziv, double uzbruk, double aizsarg) {
        this.vards = vards;
        this.tips = tips;
        this.limenis = limenis;
        this.dziv = dziv;
        this.uzbruk = uzbruk;
        this.aizsarg = aizsarg;
    }


    public String getVards() { 
    	return vards; }
    
    public String getTips() { 
    	return tips; }
    
    public int getLimenis() { 
    	return limenis; }
    
    public double getDziv() { 
    	return dziv; }
    
    public double getUzbruk() { 
    	return uzbruk; }
    
    public double getAizsarg() { 
    	return aizsarg; }


    public void saņBojās(double daudzums) {
    	dziv -= daudzums;
        if (dziv < 0) dziv = 0;
    }

    public double dotBoja() {
        return uzbruk * (limenis / 2.0);
    }

    public double aizsarg() {
        return aizsarg * 0.7;
    }

    public String ipaUzbruk() {
        return vards + " izmanto īpašo uzbrukumu!";
    }

    public String izvadit() {
        return "Vārds: " + vards +
               "\nTips: " + tips +
               "\nLīmenis: " + limenis +
               "\nDzīvības: " + dziv +
               "\nUzbrukums: " + uzbruk +
               "\nAizsardzība: " + aizsarg;
    }

    public String info() {
        return vards + " (" + tips + ") - Līmenis: " + limenis;
    }

    @Override
    public int compareTo(Pokemons cits) {
        return Double.compare(this.uzbruk, cits.uzbruk);
    }
}
