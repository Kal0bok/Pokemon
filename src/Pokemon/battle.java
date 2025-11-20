package Pokemon;

import java.util.Random;

public class battle {
    private Pokemons playerPokemon;
    private Pokemons enemyPokemon;
    private Trainer playerTrainer;
    private boolean playerTurn;
    private boolean playerHasShield;
    private boolean enemyHasShield;
    private Random random;
    
    public battle(Pokemons playerPokemon, Pokemons enemyPokemon, Trainer playerTrainer) {
        this.playerPokemon = playerPokemon;
        this.enemyPokemon = enemyPokemon;
        this.playerTrainer = playerTrainer;
        this.playerTurn = true;
        this.playerHasShield = false;
        this.enemyHasShield = false;
        this.random = new Random();
    }
    
    public Pokemons getPlayerPokemon() { return playerPokemon; }
    public Pokemons getEnemyPokemon() { return enemyPokemon; }
    public boolean isPlayerTurn() { return playerTurn; }
    public boolean playerHasShield() { return playerHasShield; }
    public boolean enemyHasShield() { return enemyHasShield; }
    
    public String izmantotAizsardzibu() {
        if (!playerTurn) return "Nav jūsu gājiens!";
        
        if (random.nextBoolean()) {
            double maxHp = 100;
            playerPokemon.saņBojās(playerPokemon.getDziv() - maxHp);
            playerTurn = false;
            ienaidniekaGajiens();
            return playerPokemon.getVards() + " Pilnībā atjaunoja veselību!";
        } else {
            playerHasShield = true;
            playerTurn = false;
            ienaidniekaGajiens();
            return playerPokemon.getVards() + " Ieguva vairogu uz nākamo triecienu!";
        }
    }
    
    public String izmantotParastoUzbrukumu() {
        if (!playerTurn) return "Nav jūsu gājiens!";
        
        double bojajums = aprekinatParastoBojajumu();
        String rezultats = uzliktBojajumuIenaidniekam(bojajums);
        
        playerTurn = false;
        ienaidniekaGajiens();
        return rezultats;
    }
    
    public String izmantotSuperUzbrukumu() {
        if (!playerTurn) return "Nav jūsu gājiens!";
        
        String rezultats = uzliktBojajumuIenaidniekam(50);
        playerTurn = false;
        ienaidniekaGajiens();
        return rezultats;
    }
    
    private double aprekinatParastoBojajumu() {
        return playerPokemon.getUzbruk() + playerTrainer.getLimenis();
    }
    
    private String uzliktBojajumuIenaidniekam(double bojajums) {
        if (enemyHasShield) {
            enemyHasShield = false;
            return playerPokemon.getVards() + " Uzbruk! Ienaidnieka vairogs absorbēja bojājumu!";
        } else {
            enemyPokemon.saņBojās(bojajums);
            String rezultats = playerPokemon.getVards() + " nodara " + bojajums + " bojājumus!";
            
            if (enemyPokemon.getDziv() <= 0) {
                rezultats += "\n" + enemyPokemon.getVards() + " Ir sakauts!";
            }
            
            return rezultats;
        }
    }
    
    private void ienaidniekaGajiens() {
        if (enemyPokemon.getDziv() > 0) {
            int darbiba = random.nextInt(3);
            String ienaidniekaRezultats = "";
            
            switch (darbiba) {
                case 0:
                    ienaidniekaRezultats = ienaidniekaAizsardziba();
                    break;
                case 1:
                    ienaidniekaRezultats = ienaidniekaParastaisUzbrukums();
                    break;
                case 2:
                    ienaidniekaRezultats = ienaidniekaSuperUzbrukums();
                    break;
            }
            
            paradiIenaidniekaDarbibu(ienaidniekaRezultats);
        }
        
        playerTurn = true;
    }
    
    private String ienaidniekaAizsardziba() {
        if (random.nextBoolean()) {
            double maxHp = 100;
            enemyPokemon.saņBojās(enemyPokemon.getDziv() - maxHp);
            return enemyPokemon.getVards() + " Pilnībā atjaunoja veselību!";
        } else {
            enemyHasShield = true;
            return enemyPokemon.getVards() + " Ieguva vairogu uz nākamo triecienu!";
        }
    }
    
    private String ienaidniekaParastaisUzbrukums() {
        double bojajums = enemyPokemon.getUzbruk() + 5;
        return uzliktBojajumuSpeletajam(bojajums);
    }
    
    private String ienaidniekaSuperUzbrukums() {
        return uzliktBojajumuSpeletajam(50);
    }
    
    private String uzliktBojajumuSpeletajam(double bojajums) {
        if (playerHasShield) {
            playerHasShield = false;
            return enemyPokemon.getVards() + " Uzbruk! Jūsu vairogs absorbēja bojājumu!";
        } else {
            playerPokemon.saņBojās(bojajums);
            String rezultats = enemyPokemon.getVards() + " nodara " + bojajums + " bojājumus!";
            
            if (playerPokemon.getDziv() <= 0) {
                rezultats += "\nJūsu pokemons ir sakauts!";
            }
            
            return rezultats;
        }
    }
    
    private void paradiIenaidniekaDarbibu(String zina) {
        System.out.println("Ienaidnieka darbība: " + zina);
    }
}