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
    private long lastSuperAttackTime;
    private static final long SUPER_ATTACK_COOLDOWN = 60000; 
    
    public battle(Pokemons playerPokemon, Pokemons enemyPokemon, Trainer playerTrainer) {
        this.playerPokemon = playerPokemon;
        this.enemyPokemon = enemyPokemon;
        this.playerTrainer = playerTrainer;
        this.playerTurn = true;
        this.playerHasShield = false;
        this.enemyHasShield = false;
        this.random = new Random();
        this.lastSuperAttackTime = 0;
    }
    
    public Pokemons getPlayerPokemon() { return playerPokemon; }
    public Pokemons getEnemyPokemon() { return enemyPokemon; }
    public boolean isPlayerTurn() { return playerTurn; }
    public boolean playerHasShield() { return playerHasShield; }
    public boolean enemyHasShield() { return enemyHasShield; }
    
    public String izmantotAizsardzibu() {
        if (!playerTurn) return "Nav jūsu gājiens!";
        
        if (random.nextBoolean()) {
            double maxHp = playerPokemon.getDziv() + 50; // Pilnīga atjaunošana
            playerPokemon.saņBojās(playerPokemon.getDziv() - maxHp);
            playerTurn = false;
            return playerPokemon.getVards() + " pilnībā atjaunoja veselību!";
        } else {
            playerHasShield = true;
            playerTurn = false;
            return playerPokemon.getVards() + " ieguva vairogu uz nākamo triecienu!";
        }
    }
    
    public String izmantotParastoUzbrukumu() {
        if (!playerTurn) return "Nav jūsu gājiens!";
        
        double bojajums = aprekinatParastoBojajumu();
        String rezultats = uzliktBojajumuIenaidniekam(bojajums);
        playerTurn = false;
        return rezultats;
    }
    
    public String izmantotSuperUzbrukumu() {
        if (!playerTurn) return "Nav jūsu gājiens!";
        
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSuperAttackTime < SUPER_ATTACK_COOLDOWN) {
            long remainingTime = (SUPER_ATTACK_COOLDOWN - (currentTime - lastSuperAttackTime)) / 1000;
            return "Super uzbrukums vēl nav gatavs! Gaidiet " + remainingTime + " sekundes!";
        }
        
        lastSuperAttackTime = currentTime;
        String rezultats = uzliktBojajumuIenaidniekam(50);
        playerTurn = false;
        return rezultats;
    }
    
    private double aprekinatParastoBojajumu() {
        return playerPokemon.getUzbruk() + playerTrainer.getLimenis();
    }
    
    private String uzliktBojajumuIenaidniekam(double bojajums) {
        if (enemyHasShield) {
            enemyHasShield = false;
            return playerPokemon.getVards() + " uzbruk! Ienaidnieka vairogs absorbēja bojājumu!";
        } else {
            double currentHp = enemyPokemon.getDziv();
            enemyPokemon.saņBojās(bojajums);
            String rezultats = playerPokemon.getVards() + " nodara " + bojajums + " bojājumus!";
            
            if (enemyPokemon.getDziv() <= 0) {
                rezultats += "\n" + enemyPokemon.getVards() + " ir sakauts!";
            }
            
            return rezultats;
        }
    }
    
    public String veiktIenaidniekaGajienu() {
        if (enemyPokemon.getDziv() <= 0) return "";
        
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
        
        playerTurn = true;
        return ienaidniekaRezultats;
    }
    
    private String ienaidniekaAizsardziba() {
        if (random.nextBoolean()) {
            double maxHp = enemyPokemon.getDziv() + 50;
            enemyPokemon.saņBojās(enemyPokemon.getDziv() - maxHp);
            return enemyPokemon.getVards() + " pilnībā atjaunoja veselību!";
        } else {
            enemyHasShield = true;
            return enemyPokemon.getVards() + " ieguva vairogu uz nākamo triecienu!";
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
            return enemyPokemon.getVards() + " uzbruk! Jūsu vairogs absorbēja bojājumu!";
        } else {
            playerPokemon.saņBojās(bojajums);
            String rezultats = enemyPokemon.getVards() + " nodara " + bojajums + " bojājumus!";
            
            if (playerPokemon.getDziv() <= 0) {
                rezultats += "\nJūsu pokemons ir sakauts!";
            }
            
            return rezultats;
        }
    }
}