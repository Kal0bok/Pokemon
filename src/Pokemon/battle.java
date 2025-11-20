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
    
}