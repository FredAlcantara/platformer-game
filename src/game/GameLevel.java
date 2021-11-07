package game;

import city.cs.engine.World;
import game.EnemyProperties.Boss;
import game.EnemyProperties.Enemy;
import game.EnemyProperties.Enemy2;
import game.EnemyProperties.EnemyCollider;
import game.itemObject.*;
import org.jbox2d.common.Vec2;
public abstract class GameLevel extends World {
    private static MainCharacter protagonist;
    private Enemy enemy;
    private Exit exit;
    private Game game;
    private Enemy2 enemy2;
    private Wall wall;
    private Boss boss;
    /**
     *this class initialises which type of object would be placed in each level.
     * @param game - Game class is passed through every level.
     */
    public GameLevel(Game game){
        this.game = game;
        //initialising this wall would be in every single level as it does not change position
        wall = new Wall(this);
        wall.setPosition(new Vec2(-21,-10));
        WallCollision destroyBody = new WallCollision(wall);
        wall.addCollisionListener(destroyBody);

    }
    public void populate(Game game){
        // This section is where all the properties of the MainCharacter has
        //been initialised.
        //The main character of the game is initialised and setA
        protagonist = new MainCharacter(this);
        enemy = new Enemy(this);
        exit = new Exit(this);
        enemy2 = new Enemy2(this);
        boss = new Boss(this);

        ExitEncounter encounter = new ExitEncounter(this);
        HeartEncounter heartEncounter = new HeartEncounter(protagonist);
        protagonist.addCollisionListener(encounter);
        protagonist.addCollisionListener(heartEncounter);
        //PickUpItem class called to past through main character
        PickUpItem items = new PickUpItem(protagonist);
        protagonist.addCollisionListener(items);

        //EnemyCollider class called to past through main character
        EnemyCollider loseHealth = new EnemyCollider(protagonist);
        protagonist.addCollisionListener(loseHealth);

    }

    //a method of retrieving the main Character
    public MainCharacter getProtagonist(){
        return protagonist;
    }
    //a method of saving the character attributes throughout every level
    public void setProtagonist(MainCharacter protagonist){
       this.protagonist = protagonist;
    }
    //a method of saving the enemies attribute throughout every level
    public void setEnemy(Enemy enemy){this.enemy = enemy;}
    //a method of saving the enemies attribute throughout every level
    public void setEnemy2(Enemy2 enemy2){this.enemy2 = enemy2;}
    //a method of saving the exit location or positions throughout every level
    public void setExit(Exit exit){this.exit = exit;}
    //a method of saving the boss attribute throughout every level
    public void setBoss(Boss boss){this.boss = boss;}
    //a method of retrieving the main Enemy
    public Enemy getEnemy(){
        return enemy;
    }
    //a method of retrieving the main Enemy2
    public Enemy2 getEnemy2(){
        return enemy2;
    }
    //a method of retrieving the main Boss
    public Boss getBoss(){return boss;}
    //a method of retrieving the main Exit
    public Exit getExit(){
        return exit;
    }
    //a method of retrieving the main Game class
    public Game getGame(){
        return game;
    }
    public abstract boolean isComplete();
    public abstract String getLevelName();
}
