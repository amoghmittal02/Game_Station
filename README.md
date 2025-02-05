Guide to Readme.txt or Readme.md
Required sections: Header, Files, Notes, How to compile and run, I/O Example

# CS611-Assignment 5
## Tic Tac Toe // Order and Chaos // Super Tic Tac Toe // Quoridor // Monsters and Heroes // Legends of Valor
---------------------------------------------------------------------------
Member 1:
- Name: Yibo Zhou
- Email: ybzhou02@bu.edu
- Student ID: U69057986

Member 2:
- Name: Amogh Mittal
- Email: amittal2@bu.edu
- Student ID: U65464717

## Files
---------------------------------------------------------------------------
I reuse **all classes in Monster and Hero game** and some classes in other games such as **Board, ChessPiece, InputRule and other** classes.
Also, to implement new requirements in this assignment, I create new classes and interfaces below.
### Entity Class
1. I create 5 new space classes: BushSpace, CaveSpace, KoulouSpace, NexuSpace and ObstacleSpace. **They are subclasses of Space class, the same as other spaces in Monsters and Heroes**.
### Interface and Implementation Class
1. To implement some design patterns and some functions of the game, I design a series of interfaces.
2. **SpaceExtraIncrease** interface is used to implement increasing specific attribute while heroes enter specific space. And this is for **Decorator Pattern implementation**.
3. **HeroFactory, MonsterFactory and ItemFactory** interfaces are used to implement **Factory Pattern** while building different heroes, monsters and items. All fatory classes in **Interface.Impl** package are implementation classes of one of these three interfaces.
4. **ChoiceStrategy** interface is used to implement **Strategy Pattern** while processing heroes' choices during fights. **EquipArmor, EquipSpell, EquipWeapon, PotionUse** classes in **Interface.Impl** package are implementation classes of this interface.
### Game Controller Class
1. **LOVController** class controls the whole process of the game.
2. **LOVRule** class implements all detailed rules, examinations, function implementations in the game.


## Notes
---------------------------------------------------------------------------

### Total Game Flow(Move, Fight and Market Related)
1. Player needs to choose game difficulty at first.
2. Player chooses three of heroes he wants.
3. The game will initialize and show the map.
4. Each hero is born in a Market Space, i.e. Heroes' Nexus. **Heroes can only purchase or sell items in their nexus**, inputing M/m can enter the market.
    1. Player can **buy items or sell items** in markets.
    2. Every market will randomly have different numbers and different kinds of items.
    3. Different heroes have different amount of gold and level, so they can buy different items.
5. At any time except fighting and purchasing items, the player can input Q/q to quit the game.
6. At almost any time, the player **can input I/i or 0** to see heroes information.
7. Input S/s to move, A/a to attack, C/c to change equipment, U/u to use potion, R/r to recall, T/t to teleport. **Each hero can move once in its turn**.
8. After all heroes' turns finish, monsters can begin their turns.
    1. Monsters will go forward if no heroes in their attack range.
    2. Monsters cannot go forward if they face an obstacle space.
9. Different difficulty means new monsters will be generated after different number of rounds.
10. Heroes cannot go up if it is in the same row and same column with a monster, it has to kill the monster.
11. If heroes are killed, they will respawn in their nexus.
    1. If monsters kill heroes, then monsters can go forward for oen space immediately because heroes will respawn. If monsters are in the 7th row, and they kill heroes that are in nexus, and monsters do not move forward, monsters will never win, they will fall in a loop of killing a hero and the hero repwawn and the hero be killed again.
12. Checking win condition for every heroes' and monsters' turn.
    

### Equipments Related
1. Heroes **can at most equip two weapons at the same time**, and each weapon should be **only one handrequired**.
2. If heroes **only equip one weapon, and the weapon is one handrequired, its attack will create more damage**.
3. When heroes want to equip new spells and armory, **its spells and armory equipped previously will be put back to inventories automatically**.
4. When heroes want to equip new weapons, due to the hand required limitation, player **should choose to which weapon should be put back to inventories, and then equip the new weapon. If player cannot handle this situation well, he may waste a turn**.
5. Due to make sure related operations can be handled well, I design a lot of prompts, some may be too much, but these can assist the player to play properly and understand the game well.

### Design Pattern
1. I use **Factory Pattern** to initialize all heroes, monsters, spaces and items.
2. I use **Strategy Pattern** to handle all equipment related operations.
3. I also try to use **Observer Pattern** in fights, but I do not realize yet. However, when I deal with fights, I use Observer Pattern ideas.
4. I use **Decorator Pattern** to implement increasing specific attribute while heroes enter specific space.

### Multipliers
1. A lot of the base settings are unbalanced, so I set a lot of multipliers to balance the game. They are in the Config package.

### About Packages
1. **Many packages are created by me and referenced in my java class files, because in this way I can manage classes of different types and functions in a unified way, and the code structure will be clearer. I do not reference content that is irrelevant to the project or use some packages to assist me in completing my homework**.
2. All heroes, monsters and items are get from the **InputFile.*.txt** files.
## Program Advantages
---------------------------------------------------------------------------
1. **Code Reuse**: As much as possible, use the same code structure that was implemented in the previous five games, especially the Monsters and Heroes. **LOVRule is the subclass of MHRule, LOVController id the subclass of MHController**, because many logics, classes and methods in Monsters and Heroes can be reused in Legends of Valor. This approach ensures consistency and efficiency in development. 
2. **Colorful Output**: I use different colors of output prompts to represent different meanings. On the board, **yellow spaces represents the Koulou spaces, red spaces represents for Inaccessible spaces and Obstacle spaces, purple spaces represents Cave spaces, green color represents Bush spaces, blue spaces represents Nexu and Market spaces**. During the game, **red represents quit or monster attack, and yellow is generally a warning, indicating that the user's input or related behavior is illegal. Green is generally information that is beneficial to the user or hero, and blue is a general prompt**. In this way, players can distinguish different information well in the game.
3. **Multiplier**: By specifying the Multiplier, the balance problem of the game can be well solved. If the hero damage is too low, the monster damage is too high, etc., you can change it by adjusting the relevant Multiplier in **Config package** without modifying the original file.
4. **Design Pattern**: I used many design patterns such as **Factory Pattern, Strategy Pattern and Decorator Pattern** to optimize my code so that related code can be changed more easily in subsequent assignments.
5. **Detail Implementation**: I implemented many details in the game and took into account many subsequent problems. For example, **after selecting a large number of heroes, many previous prompts are easily moved up by related hero information**. Therefore, I will reprint the previously printed content when the player needs to make a choice to ensure that he does not need to frequently flip up to read the previous information.
6. **User Guidance with Prompts**: I have incorporated a large number of prompts to assist users in understanding the game mechanics and making valid inputs, enhancing the user experience.
7. **Music**: The game has a background music which plays once the board appears, this enhances the user experience of playing the game

## How to compile and run
---------------------------------------------------------------------------


1. Navigate to the directory "src" after unzipping the files
2. Run the following instructions:
    3. javac *.java
    3. java Run

## Input/Output Example
---------------------------------------------------------------------------
```

amoghmittal@crc-dot1x-nat-10-239-168-15 src % javac *.java
amoghmittal@crc-dot1x-nat-10-239-168-15 src % java Run
Choose game: input 0 to exit, 1 for tic-tac-toe, 2 for order and chaos, 3 for super tic-tac-toe, 4 for quoridor, 5 for Monsters and Heroes, 6 for Legends of Valor
6
Please input a valid game number!

Initializing Monsters and Heroes Game...

BEFORE WE START:

GAME REMINDER:
1. You can choose any number of heroes, unless the limit is exceeded.
2. You can stand on a Market Space, then enter M/m to get in.
3. You may face monsters when you go through a Common Space.
4. Every hero has its own gold, experience and level, it does not share anything with other heroes.
5. In the game especially during fight processes, please read prompts carefully, you may waste a turn by wrong operations!

HOW TO WIN: The game does not have a win condition, it will only end when you input Q/q to quit the total game!

Can we get started? Input Y/y to continue! Input others to quit.
y
Enjoy your game!
Please choose game difficulty:
1. easy
2. medium
3. difficult
1
Here are all heroes you can choose:
1. Hero's name='Gaerdal_Ironhand'
type='Warrior'	level=1	hp=100	mp=100	strength=700	dexterity=600	agility=50	gold=1354	experience=7

2. Hero's name='Sehanine_Monnbow'
type='Warrior'	level=1	hp=100	mp=600	strength=700	dexterity=500	agility=80	gold=2500	experience=8

3. Hero's name='Muamman_Duathall'
type='Warrior'	level=1	hp=100	mp=300	strength=900	dexterity=750	agility=50	gold=2546	experience=6

4. Hero's name='Flandal_Steelskin'
type='Warrior'	level=1	hp=100	mp=200	strength=750	dexterity=700	agility=65	gold=2500	experience=7

5. Hero's name='Undefeated_Yoj'
type='Warrior'	level=1	hp=100	mp=400	strength=800	dexterity=700	agility=40	gold=2500	experience=7

6. Hero's name='Eunoia_Cyn'
type='Warrior'	level=1	hp=100	mp=400	strength=700	dexterity=600	agility=80	gold=2500	experience=6

7. Hero's name='Parzival'
type='Sorcerer'	level=1	hp=100	mp=300	strength=750	dexterity=700	agility=65	gold=2500	experience=7

8. Hero's name='Sehanine_Moonbow'
type='Sorcerer'	level=1	hp=100	mp=300	strength=750	dexterity=700	agility=70	gold=2500	experience=7

9. Hero's name='Skoraeus_Stonebones'
type='Sorcerer'	level=1	hp=100	mp=250	strength=650	dexterity=350	agility=60	gold=2500	experience=4

10. Hero's name='Garl_Glittergold'
type='Sorcerer'	level=1	hp=100	mp=100	strength=600	dexterity=400	agility=50	gold=2500	experience=5

11. Hero's name='Amaryllis_Astra'
type='Sorcerer'	level=1	hp=100	mp=500	strength=500	dexterity=500	agility=50	gold=2500	experience=5

12. Hero's name='Caliber_Heist'
type='Sorcerer'	level=1	hp=100	mp=400	strength=400	dexterity=400	agility=40	gold=2500	experience=8

13. Hero's name='Rillifane_Rallathil'
type='Sorcerer'	level=1	hp=100	mp=1300	strength=750	dexterity=500	agility=450	gold=2500	experience=9

14. Hero's name='Segojan_Earthcaller'
type='Sorcerer'	level=1	hp=100	mp=900	strength=800	dexterity=650	agility=500	gold=2500	experience=5

15. Hero's name='Reign_Havoc'
type='Sorcerer'	level=1	hp=100	mp=800	strength=800	dexterity=800	agility=800	gold=2500	experience=8

16. Hero's name='Reverie_Ashels'
type='Sorcerer'	level=1	hp=100	mp=900	strength=800	dexterity=400	agility=700	gold=2500	experience=7

17. Hero's name='Kalabar'
type='Sorcerer'	level=1	hp=100	mp=800	strength=850	dexterity=600	agility=400	gold=2500	experience=6

18. Hero's name='Skye_Soar'
type='Sorcerer'	level=1	hp=100	mp=1000	strength=700	dexterity=500	agility=400	gold=2500	experience=5

Please choose three heroes.
Three heroes will be set sequentially as H1, H2, and H3.
Please enter all the required hero numbers. Split by space
Please input a series of space-separated integers:
1 2 3
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|    M1 |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   O - O - O   I - I - I   O - O - O   O - O - O   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   O - O - O   I - I - I   O - O - O   O - O - O   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   | H1    |   | X X X |   |       |   | H2    |   | X X X |   |       |   | H3    |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H1's turn! Here is its information
Hero H1 is in space (8, 2)
Hero's name='Gaerdal_Ironhand'
type='Warrior'	level=1	hp=100	mp=100	strength=700	dexterity=600	agility=50	gold=1354	experience=7

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
m
market logic
You are in the market. Input B/b to but items, input S/s to sell items, input Q/q to quit, input I/i to print hero's information.
b
The market has these items!
1. Potion's name='Mermaid_Tears'
	type='Potion'	price=850	level=5	affectedAttribute='Health/Mana/Strength/Agility'	attributeIncrease=100	num: 1
2. Potion's name='Luck_Elixir'
	type='Potion'	price=500	level=4	affectedAttribute='Agility'	attributeIncrease=65	num: 2
3. Potion's name='Strength_Potion'
	type='Potion'	price=200	level=1	affectedAttribute='Strength'	attributeIncrease=75	num: 2
4. Armor's name='Platinum_Shield'
	type='Armor'	price=150	level=1	damageReduction=200	num: 2
5. Armor's name='Guardian_Angel'
	type='Armor'	price=1000	level=10	damageReduction=1000	num: 2
6. Armor's name='Wizard_Shield'
	type='Armor'	price=1200	level=10	damageReduction=1500	num: 2
7. Weapon's name='Bow'
	type='Weapon'	price=300	level=2	damage=500	handsRequired=2	num: 2
8. Weapon's name='Scythe'
	type='Weapon'	price=1000	level=6	damage=1100	handsRequired=2	num: 1
9. Weapon's name='Dagger'
	type='Weapon'	price=200	level=1	damage=250	handsRequired=2	num: 4
10. Spell's name='Electric_Arrows'
	type='Spell'	price=550	level=5	damage=650	manaCost=200	spellType='Lighting'	num: 5
11. Spell's name='Breath_of_Fire'
	type='Spell'	price=350	level=1	damage=450	manaCost=100	spellType='Fire'	num: 1
12. Spell's name='Spark_Needles'
	type='Spell'	price=500	level=2	damage=600	manaCost=200	spellType='Lighting'	num: 4
Please choose the item you want to purchase. Input 0 to quit.
9
Please input the number you want to purchase. Input 0 to quit.
1
hero gold: 1354
total cost: 200
Purchase successfully!
Hero has 1154 gold left.
Please choose a action again!
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|    M1 |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   O - O - O   I - I - I   O - O - O   O - O - O   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   O - O - O   I - I - I   O - O - O   O - O - O   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   | H2    |   | X X X |   |       |   | H3    |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H2's turn! Here is its information
Hero H2 is in space (8, 4)
Hero's name='Sehanine_Monnbow'
type='Warrior'	level=1	hp=100	mp=600	strength=700	dexterity=500	agility=80	gold=2500	experience=8

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
m
market logic
You are in the market. Input B/b to but items, input S/s to sell items, input Q/q to quit, input I/i to print hero's information.
b
The market has these items!
1. Potion's name='Strength_Potion'
	type='Potion'	price=200	level=1	affectedAttribute='Strength'	attributeIncrease=75	num: 4
2. Potion's name='Magic_Potion'
	type='Potion'	price=350	level=2	affectedAttribute='Mana'	attributeIncrease=100	num: 5
3. Potion's name='Ambrosia'
	type='Potion'	price=1000	level=8	affectedAttribute='All'	attributeIncrease=150	num: 3
4. Armor's name='Platinum_Shield'
	type='Armor'	price=150	level=1	damageReduction=200	num: 4
5. Armor's name='Guardian_Angel'
	type='Armor'	price=1000	level=10	damageReduction=1000	num: 3
6. Armor's name='Breastplate'
	type='Armor'	price=350	level=3	damageReduction=600	num: 3
7. Weapon's name='Axe'
	type='Weapon'	price=550	level=5	damage=850	handsRequired=1	num: 1
8. Weapon's name='Sword'
	type='Weapon'	price=500	level=1	damage=800	handsRequired=1	num: 1
9. Weapon's name='TSwords'
	type='Weapon'	price=1400	level=8	damage=1600	handsRequired=2	num: 4
10. Spell's name='Snow_Cannon'
	type='Spell'	price=500	level=2	damage=650	manaCost=250	spellType='Ice'	num: 4
11. Spell's name='Lightning_Dagger'
	type='Spell'	price=400	level=1	damage=500	manaCost=150	spellType='Lighting'	num: 4
12. Spell's name='Breath_of_Fire'
	type='Spell'	price=350	level=1	damage=450	manaCost=100	spellType='Fire'	num: 5
Please choose the item you want to purchase. Input 0 to quit.
11
Please input the number you want to purchase. Input 0 to quit.
1
hero gold: 2500
total cost: 400
Purchase successfully!
Hero has 2100 gold left.
Please choose a action again!
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|    M1 |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   O - O - O   I - I - I   O - O - O   O - O - O   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   O - O - O   I - I - I   O - O - O   O - O - O   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   | H1    |   | X X X |   |       |   | H2    |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   | H3    |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H3's turn! Here is its information
Hero H3 is in space (8, 6)
Hero's name='Muamman_Duathall'
type='Warrior'	level=1	hp=100	mp=300	strength=900	dexterity=750	agility=50	gold=2546	experience=6

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
m
market logic
You are in the market. Input B/b to but items, input S/s to sell items, input Q/q to quit, input I/i to print hero's information.
b
The market has these items!
1. Potion's name='Mermaid_Tears'
	type='Potion'	price=850	level=5	affectedAttribute='Health/Mana/Strength/Agility'	attributeIncrease=100	num: 1
2. Potion's name='Strength_Potion'
	type='Potion'	price=200	level=1	affectedAttribute='Strength'	attributeIncrease=75	num: 2
3. Potion's name='Ambrosia'
	type='Potion'	price=1000	level=8	affectedAttribute='All'	attributeIncrease=150	num: 4
4. Armor's name='Guardian_Angel'
	type='Armor'	price=1000	level=10	damageReduction=1000	num: 1
5. Armor's name='Full_Body_Armor'
	type='Armor'	price=1000	level=8	damageReduction=1100	num: 3
6. Armor's name='Platinum_Shield'
	type='Armor'	price=150	level=1	damageReduction=200	num: 1
7. Weapon's name='TSwords'
	type='Weapon'	price=1400	level=8	damage=1600	handsRequired=2	num: 5
8. Weapon's name='Scythe'
	type='Weapon'	price=1000	level=6	damage=1100	handsRequired=2	num: 4
9. Weapon's name='Axe'
	type='Weapon'	price=550	level=5	damage=850	handsRequired=1	num: 2
10. Spell's name='Ice_Blade'
	type='Spell'	price=250	level=1	damage=450	manaCost=100	spellType='Ice'	num: 5
11. Spell's name='Electric_Arrows'
	type='Spell'	price=550	level=5	damage=650	manaCost=200	spellType='Lighting'	num: 3
12. Spell's name='Arctic_Storm'
	type='Spell'	price=700	level=6	damage=800	manaCost=300	spellType='Ice'	num: 3
Please choose the item you want to purchase. Input 0 to quit.
6
Please input the number you want to purchase. Input 0 to quit.
1
hero gold: 2546
total cost: 150
Purchase successfully!
Hero has 2396 gold left.
Please choose a action again!
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
Monster M1's turn!
Monster M2's turn!
Monster M3's turn!
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|    M1 |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   O - O - O   I - I - I   O - O - O   O - O - O   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   O - O - O   I - I - I   O - O - O   O - O - O   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   | H1    |   | X X X |   |       |   | H2    |   | X X X |   |       |   | H3    |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H1's turn! Here is its information
Hero H1 is in space (7, 2)
Hero's name='Gaerdal_Ironhand'
type='Warrior'	level=1	hp=100	mp=100	strength=700	dexterity=600	agility=50	gold=1154	experience=7

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|    M1 |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   O - O - O   I - I - I   O - O - O   O - O - O   
6.|       |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   O - O - O   I - I - I   O - O - O   O - O - O   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   | H2    |   | X X X |   |       |   | H3    |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H2's turn! Here is its information
Hero H2 is in space (7, 4)
Hero's name='Sehanine_Monnbow'
type='Warrior'	level=1	hp=100	mp=600	strength=700	dexterity=500	agility=88	gold=2100	experience=8

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
It's an obstacle space, input Y/y to remove it, input others to choose another action.
s
Please choose another action.
Please choose a action again!
y
Invalid input! Please input again!
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
It's an obstacle space, input Y/y to remove it, input others to choose another action.
y
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|    M1 |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   O - O - O   
6.|       |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   O - O - O   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   | H2    |   | X X X |   |       |   | H3    |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H3's turn! Here is its information
Hero H3 is in space (7, 6)
Hero's name='Muamman_Duathall'
type='Warrior'	level=1	hp=100	mp=300	strength=900	dexterity=825	agility=50	gold=2396	experience=6

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
It's an obstacle space, input Y/y to remove it, input others to choose another action.
y
Monster M1's turn!
Monster M2's turn!
Monster M3's turn!
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|    M1 |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   | H2    |   | X X X |   |       |   | H3    |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H1's turn! Here is its information
Hero H1 is in space (6, 2)
Hero's name='Gaerdal_Ironhand'
type='Warrior'	level=1	hp=100	mp=100	strength=700	dexterity=600	agility=55	gold=1154	experience=7

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|    M1 |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   | H2    |   | X X X |   |       |   | H3    |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H2's turn! Here is its information
Hero H2 is in space (7, 4)
Hero's name='Sehanine_Monnbow'
type='Warrior'	level=1	hp=100	mp=600	strength=700	dexterity=500	agility=88	gold=2100	experience=8

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|    M1 |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   | H2    |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   | H3    |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H3's turn! Here is its information
Hero H3 is in space (7, 6)
Hero's name='Muamman_Duathall'
type='Warrior'	level=1	hp=100	mp=300	strength=900	dexterity=825	agility=50	gold=2396	experience=6

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
Monster M1's turn!
Monster M2's turn!
Monster M3's turn!
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|    M1 |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   | H2    |   | X X X |   |       |   | H3    |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H1's turn! Here is its information
Hero H1 is in space (5, 2)
Hero's name='Gaerdal_Ironhand'
type='Warrior'	level=1	hp=100	mp=100	strength=700	dexterity=660	agility=50	gold=1154	experience=7

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|    M1 |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   | H1    |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   | H2    |   | X X X |   |       |   | H3    |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H2's turn! Here is its information
Hero H2 is in space (6, 4)
Hero's name='Sehanine_Monnbow'
type='Warrior'	level=1	hp=100	mp=600	strength=700	dexterity=500	agility=80	gold=2100	experience=8

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|    M1 |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   | H1    |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   | H2    |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   | H3    |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H3's turn! Here is its information
Hero H3 is in space (6, 6)
Hero's name='Muamman_Duathall'
type='Warrior'	level=1	hp=100	mp=300	strength=900	dexterity=750	agility=50	gold=2396	experience=6

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
Monster M1's turn!
Monster M2's turn!
Monster M2 attacks hero H2!
Monster Desghidorrah is attacking!
Monster Desghidorrah may cause 30 damage!
The hero get 30 damage.
The hero has 70 hp left.
Monster M3's turn!
Monster M3 attacks hero H3!
Monster Desghidorrah is attacking!
Monster Desghidorrah may cause 30 damage!
The hero get 30 damage.
The hero has 70 hp left.
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|    M1 |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   | H1    |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   | H2    |   | X X X |   |       |   | H3    |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H1's turn! Here is its information
Hero H1 is in space (4, 2)
Hero's name='Gaerdal_Ironhand'
type='Warrior'	level=1	hp=100	mp=100	strength=770	dexterity=600	agility=50	gold=1154	experience=7

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|    M1 |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   | H2    |   | X X X |   |       |   | H3    |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H2's turn! Here is its information
Hero H2 is in space (5, 4)
Hero's name='Sehanine_Monnbow'
type='Warrior'	level=1	hp=77	mp=600	strength=700	dexterity=550	agility=80	gold=2100	experience=8

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|    M1 |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   | H2    |   | X X X |   |    M3 |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   | H3    |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H3's turn! Here is its information
Hero H3 is in space (5, 6)
Hero's name='Muamman_Duathall'
type='Warrior'	level=1	hp=77	mp=300	strength=900	dexterity=750	agility=55	gold=2396	experience=6

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
Monster M1's turn!
Monster M1 attacks hero H1!
Monster Desghidorrah is attacking!
Monster Desghidorrah may cause 30 damage!
The hero get 30 damage.
The hero has 70 hp left.
Monster M2's turn!
Monster M2 attacks hero H2!
Monster Desghidorrah is attacking!
Monster Desghidorrah may cause 30 damage!
The hero dodges the attack!!!
Monster M3's turn!
Monster M3 attacks hero H3!
Monster Desghidorrah is attacking!
Monster Desghidorrah may cause 30 damage!
The hero get 30 damage.
The hero has 47 hp left.
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|    M1 |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   | H2    |   | X X X |   |    M3 |   | H3    |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H1's turn! Here is its information
Hero H1 is in space (3, 2)
Hero's name='Gaerdal_Ironhand'
type='Warrior'	level=1	hp=77	mp=100	strength=700	dexterity=660	agility=50	gold=1154	experience=7

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|    M1 |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   | H2    |   | X X X |   |    M3 |   | H3    |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H2's turn! Here is its information
Hero H2 is in space (4, 4)
Hero's name='Sehanine_Monnbow'
type='Warrior'	level=1	hp=84	mp=600	strength=700	dexterity=500	agility=88	gold=2100	experience=8

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
t
teleport logic
Choose a target hero to teleport near. Here are the available heroes:
1. H1
2. H3
1
Possible teleport positions:
1. (2, 1)
2. (3, 2)
1
Hero H2 successfully teleported to (2, 1).
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.| H2 M1 |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |    M3 |   | H3    |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H3's turn! Here is its information
Hero H3 is in space (4, 6)
Hero's name='Muamman_Duathall'
type='Warrior'	level=1	hp=51	mp=300	strength=990	dexterity=750	agility=50	gold=2396	experience=6

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
t
teleport logic
Choose a target hero to teleport near. Here are the available heroes:
1. H1
2. H2
1
Possible teleport positions:
1. (3, 2)
1
Hero H3 successfully teleported to (3, 2).
Monster M1's turn!
Monster M1 attacks hero H2!
Monster Desghidorrah is attacking!
Monster Desghidorrah may cause 30 damage!
The hero get 30 damage.
The hero has 54 hp left.
Monster M2's turn!
Monster M3's turn!
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.| H2 M1 |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   | H3    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |    M3 |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H1's turn! Here is its information
Hero H1 is in space (2, 2)
Hero's name='Gaerdal_Ironhand'
type='Warrior'	level=1	hp=84	mp=100	strength=770	dexterity=600	agility=50	gold=1154	experience=7

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
a
attack logic
Please input the row and col of the monster you want to attack, split by space
2 1
Gaerdal_Ironhand has 100 basic damage!
The hero deals total 100 damage!
Monster M1 defences 40 damage!
Monster M1 has 240 hp left.
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.| H2 M1 |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   | H3    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |    M3 |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H2's turn! Here is its information
Hero H2 is in space (2, 1)
Hero's name='Sehanine_Monnbow'
type='Warrior'	level=1	hp=59	mp=600	strength=700	dexterity=550	agility=80	gold=2100	experience=8

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
a
attack logic
Please input the row and col of the monster you want to attack, split by space
2 1
Monster M1 dodges the attack!!!
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.| H2 M1 |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   | H3    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |    M3 |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H3's turn! Here is its information
Hero H3 is in space (3, 2)
Hero's name='Muamman_Duathall'
type='Warrior'	level=1	hp=56	mp=300	strength=900	dexterity=825	agility=50	gold=2396	experience=6

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
a
attack logic
Please input the row and col of the monster you want to attack, split by space
2 1
Muamman_Duathall has 100 basic damage!
The hero deals total 100 damage!
Monster M1 defences 40 damage!
Monster M1 has 180 hp left.
Monster M1's turn!
Monster M1 attacks hero H2!
Monster Desghidorrah is attacking!
Monster Desghidorrah may cause 30 damage!
The hero get 30 damage.
The hero has 29 hp left.
Monster M2's turn!
Monster M3's turn!
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.| H2 M1 |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   | H3    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |    M3 |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H1's turn! Here is its information
Hero H1 is in space (2, 2)
Hero's name='Gaerdal_Ironhand'
type='Warrior'	level=1	hp=92	mp=100	strength=770	dexterity=600	agility=50	gold=1154	experience=7

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
a
attack logic
Please input the row and col of the monster you want to attack, split by space
2 1
Gaerdal_Ironhand has 100 basic damage!
The hero deals total 100 damage!
Monster M1 defences 40 damage!
Monster M1 has 120 hp left.
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.| H2 M1 |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   | H3    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |    M3 |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H2's turn! Here is its information
Hero H2 is in space (2, 1)
Hero's name='Sehanine_Monnbow'
type='Warrior'	level=1	hp=31	mp=600	strength=700	dexterity=550	agility=80	gold=2100	experience=8

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
a
attack logic
Please input the row and col of the monster you want to attack, split by space
2 1
Sehanine_Monnbow has 100 basic damage!
The hero deals total 100 damage!
Monster M1 defences 40 damage!
Monster M1 has 60 hp left.
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.| H2 M1 |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   | H3    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |    M3 |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H3's turn! Here is its information
Hero H3 is in space (3, 2)
Hero's name='Muamman_Duathall'
type='Warrior'	level=1	hp=61	mp=300	strength=900	dexterity=825	agility=50	gold=2396	experience=6

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
a
attack logic
Please input the row and col of the monster you want to attack, split by space
2 1
Muamman_Duathall has 100 basic damage!
The hero deals total 100 damage!
Monster M1 defences 40 damage!
Monster M1 is killed!
Monster M1's turn!
Monster M1 attacks hero H2!
Monster Desghidorrah is attacking!
Monster Desghidorrah may cause 30 damage!
The hero get 30 damage.
The hero has 1 hp left.
Monster M2's turn!
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|    M4 |   |       |   | X X X |   |    M5 |   |       |   | X X X |   |    M6 |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.| H2 M1 |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   | H3    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H1's turn! Here is its information
Hero H1 is in space (2, 2)
Hero's name='Gaerdal_Ironhand'
type='Warrior'	level=1	hp=100	mp=100	strength=770	dexterity=600	agility=50	gold=1154	experience=7

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
a
attack logic
Please input the row and col of the monster you want to attack, split by space
2 1
Gaerdal_Ironhand has 100 basic damage!
The hero deals total 100 damage!
Monster M1 defences 40 damage!
Monster M1 is killed!
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.|    M4 |   |       |   | X X X |   |    M5 |   |       |   | X X X |   |    M6 |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.| H2    |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   | H3    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

It's hero H2's turn! Here is its information
Hero H2 is in space (2, 1)
Hero's name='Sehanine_Monnbow'
type='Warrior'	level=1	hp=1	mp=600	strength=700	dexterity=550	agility=80	gold=2100	experience=8

Input your actions:
C/c change equipments; U/u use a potion; A/a attack
S/s shift(move); T/t teleport; R/r recall; P/p pass
I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)
Q/q quit game
s
move logic
Input your actions:
W/w go up; A/a go left; S/s go down; D/d go right
w
Heroes have reached the monsters' nexus! Heroes win!
Heroes have won! Game over.
      1           2                       3           4                       5           6       
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
1.| H2 M4 |   |       |   | X X X |   |    M5 |   |       |   | X X X |   |    M6 |   |       |   
  N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   
2.|       |   | H1    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   C - C - C   K - K - K   

  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   
3.|       |   | H3    |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  O - O - O   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   C - C - C   B - B - B   

  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   
4.|       |   |       |   | X X X |   |    M2 |   |       |   | X X X |   |       |   |       |   
  C - C - C   K - K - K   I - I - I   K - K - K   C - C - C   I - I - I   K - K - K   K - K - K   

  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   
5.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   B - B - B   I - I - I   O - O - O   B - B - B   I - I - I   B - B - B   C - C - C   

  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   
6.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  C - C - C   C - C - C   I - I - I   C - C - C   P - P - P   I - I - I   O - O - O   P - P - P   

  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   
7.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  B - B - B   P - P - P   I - I - I   B - B - B   C - C - C   I - I - I   K - K - K   B - B - B   

  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   
8.|       |   |       |   | X X X |   |       |   |       |   | X X X |   |       |   |       |   
  M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   I - I - I   M - M - M   M - M - M   

Do you want to play again? input 'y' to continue
y
Choose game: input 0 to exit, 1 for tic-tac-toe, 2 for order and chaos, 3 for super tic-tac-toe, 4 for quoridor, 5 for Monsters and Heroes, 6 for Legends of Valor
0
