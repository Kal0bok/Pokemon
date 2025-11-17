package Pokemon;

public abstract class Pokemons implements Comparable<Pokemons> {

    private String vards;
    private String tips;
    private int limenis;
    private double dzivibas;
    private double uzbrukums;
    private double aizsardziba;

    public Pokemons(String vards, String tips, int limenis, double dzivibas, double uzbrukums, double aizsardziba) {
        this.vards = vards;
        this.tips = tips;
        this.limenis = limenis;
        this.dzivibas = dzivibas;
        this.uzbrukums = uzbrukums;
        this.aizsardziba = aizsardziba;
    }

    // ---------- GETTERI ----------
    public String getVards() { return vards; }
    public String getTips() { return tips; }
    public int getLimenis() { return limenis; }
    public double getDzivibas() { return dzivibas; }
    public double getUzbrukums() { return uzbrukums; }
    public double getAizsardziba() { return aizsardziba; }

    // ---------- METODES ----------
    public void saņemtBojājumus(double daudzums) {
        dzivibas -= daudzums;
        if (dzivibas < 0) dzivibas = 0;
    }

    public double dotBojajumus() {
        return uzbrukums * (limenis / 2.0);
    }

    public double aizsargaties() {
        return aizsardziba * 0.7;
    }

    public String ipasaisUzbrukums() {
        return vards + " izmanto īpašo uzbrukumu!";
    }

    public String rādītInfo() {
        return "Vārds: " + vards +
               "\nTips: " + tips +
               "\nLīmenis: " + limenis +
               "\nDzīvības: " + dzivibas +
               "\nUzbrukums: " + uzbrukums +
               "\nAizsardzība: " + aizsardziba;
    }

    public String īsaInfo() {
        return vards + " (" + tips + ") - Līmenis: " + limenis;
    }

    @Override
    public int compareTo(Pokemons cits) {
        return Integer.compare(this.limenis, cits.limenis);
    }
}
