# Pokémon Cīņas Simulators ⚡💧🔥

## Apraksts
Objektorientēta Java programma ar GUI (JOptionPane), kas ļauj:

- Izveidot Pokemonus (Elektriskais / Ūdens / Uguns)
- Apskatīt statistiku un profilus
- Cīnīties ar tipa efektivitāti arenā
- Attīstīt un dziedēt Pokemonus
- Pārvaldīt trenera profilu
- Kārtot Pokemonus pēc stipruma

---

## Funkcionalitāte
- **Polimorfisms:** ipaUzbruk(), dotBoja(), aizsarg()
- **Pārmantošana:** ElektriskaisP, UdensP, Uguns manto no Pokemons
- **Abstraktā klase:** Pokemons ar abstraktām metodēm
- **Ievades validācija** ar regex un JOptionPane
- **Grafiskais interfeiss** ar gradientiem un animācijām

---

## Klases

| Klase              | Atribūti | Metodes          |
|--------------------|----------|------------------|
| Pokemons (abstraktā) | 6        | 4 (2 abstraktās) |
| ElektriskaisP       | +0       | 3 override       |
| UdensP              | +0       | 3 override       |
| Uguns               | +0       | 3 override       |
| Pokedatnis          | -        | 7 statiskās      |
| MainMenu            | 2        | 10               |
| Trainer             | 4        | 6                |
| Metodes             | -        | 5 statiskās      |
| Arena               | 2        | 4                |
| Ash                 | -        | 1 statiska       |
| Leon                | -        | 1 statiska       |

---

## OOP Principi

🔒 **Inkapsulācija** — privāti atribūti ar get/set metodēm  
🌳 **Pārmantošana** — apakšklases manto no bāzes klases  
🔄 **Polimorfisms** — override metodes katrai apakšklasei  
📐 **Abstrakcija** — abstraktā klase ar abstraktām metodēm  

---

## Iespējas

✅ **Jauns pokemons** — Izveidot jebkura tipa pokemonu  
✅ **Pokemonu saraksts** — Apskatīt visus pokemonus  
✅ **Pokemona profils** — Detalizēta statistika un darbības  
✅ **Kārtot pēc stipruma** — Augoši vai dilstoši  
✅ **Trenera profils** — Personīgā informācija  
✅ **Arena cīņas** — Cīņas starp diviem pokemoniem  
✅ **Pokemonu nodošana** — Noņemt pokemonu no saraksta  

---

## Tipu Efektivitāte

⚡ **Elektriskais** — +20% bojājumi  
💧 **Ūdens** — +30% aizsardzība  
🔥 **Uguns** — +50% bojājumi  

---

## Autori
**Bogdans Čumaks** un **Ņikita Ņikodimovs**, 2025
