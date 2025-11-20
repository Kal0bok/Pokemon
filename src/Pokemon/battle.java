package Pokemon;

import java.util.Random;

public class battle {
    private Pokemons playerPokemon;
    private Pokemons enemyPokemon;
    private Trainer playerTrainer;
    private boolean playerTurn;
    private boolean playerHasShield;
    private boolean enemyHasShield;
    
    public battle(Pokemons playerPokemon, Pokemons enemyPokemon, Trainer playerTrainer) {
        this.playerPokemon = playerPokemon;
        this.enemyPokemon = enemyPokemon;
        this.playerTrainer = playerTrainer;
        this.playerTurn = true; 
        this.playerHasShield = false;
        this.enemyHasShield = false;
    }
    
    public Pokemons getPlayerPokemon() {
    	return playerPokemon; 
    	}
    
    public Pokemons getEnemyPokemon() { 
    	return enemyPokemon; 
    	}
    
    public boolean isPlayerTurn() { 
    	return playerTurn; 
    	}
    
    public boolean playerHasShield() { 
    	return playerHasShield;
    	}
    
    public boolean enemyHasShield() { 
    	return enemyHasShield;
    	}
    
private Random random = new Random();
    
    public String useDefenseAction() {
        if (!playerTurn) return "Pretinieks uzbruka!";
        
        if (random.nextBoolean()) {
            double maxHp = getMaxHp(playerPokemon);
            playerPokemon.saņBojās(playerPokemon.getDziv() - maxHp);
            playerTurn = false;
            enemyTurn();
            return playerPokemon.getVards() + " Sasniedz pilnu dzivibu!";
        } else {
            playerHasShield = true;
            playerTurn = false;
            enemyTurn();
            return playerPokemon.getVards() + " Sasniedz aizsardzību uz nakamo uzbrukumu!";
        }
    }
    
    private double getMaxHp(Pokemons pokemon) {

        return 100;    
    }
    
    private void enemyTurn() {
        playerTurn = true;
    }
    
    
    public String useNormalAttack() {
        if (!playerTurn) return "Pretinieks uzbruka!";
        
        double damage = calculateNormalDamage();
        String result = applyDamageToEnemy(damage);
        
        playerTurn = false;
        enemyTurn();
        return result;
    }
    
    private double calculateNormalDamage() {
        return playerPokemon.getUzbruk() + playerTrainer.getLimenis();
    }
    
    private String applyDamageToEnemy(double damage) {
        if (enemyHasShield) {
            enemyHasShield = false;
            return playerPokemon.getVards() + " Uzbruka! Vairogs противника поглотил урон!";
        } else {
            double currentHp = enemyPokemon.getDziv();
            enemyPokemon.saņBojās(damage);
            String result = playerPokemon.getVards() + " uzbruka " + damage + " bojājumi!";
            
            if (enemyPokemon.getDziv() <= 0) {
                result += "\n" + enemyPokemon.getVards() + " Uzvarēts!";
            }
            
            return result;
        }
    }

private static final double SUPER_ATTACK_DAMAGE = 50;
    
    public String useSuperAttack() {
        if (!playerTurn) return "Pretinieks uzbruka!";
        
        String result = applyDamageToEnemy(SUPER_ATTACK_DAMAGE);
        playerTurn = false;
        enemyTurn();
        return result;
    }
}