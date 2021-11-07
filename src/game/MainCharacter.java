package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class MainCharacter extends Walker{

    private static final PolygonShape mainCharacterShape = new PolygonShape(
            -0.94f,-2.29f,
            1.75f,-2.3f,
            1.74f,0.87f,
            0.84f,1.6f,
            -0.99f,0.25f,
            -0.99f,-2.27f);

    private  BodyImage mainImage =
            new BodyImage("data/images/characterStationary.gif",5);

    private boolean isRight = true; // A field which identifies the character if facing the right
    private int coinCounter;
    private int healthCounter;
    private int diamond;
    private int key;
    private int Score;
    /**
     * MainCharacter class that is used pass through the
     * object to the world.
     * @param world where the object is through, allowing
     *              the object to be initiated to world.
     */
    public MainCharacter(World world) {

        //Initiating the attributes of the character
        super(world,mainCharacterShape);
        addImage(mainImage);
        coinCounter = 0;
        healthCounter = 3;
        diamond = 0;
        key = 0;
        Score = 0;
    }
    //A getter method which calls the character position
    public boolean getisRight(){
        return this.isRight;
    }
    //A setter method which character to face right
    public void setIsRight(boolean isRight){
        this.isRight = isRight;
    }
    //A method which increments coin counter when run
    public void countCoin(){
        coinCounter++;
        Score = Score+100;
       // System.out.println("Coin: "+getCoinCounter());
       // System.out.println("Score: "+getScore());
    }//A method that coin counter to be called
    public int getCoinCounter(){
        return coinCounter;
    }//A method which decrements the health counter, in relation to certain event/collision
    public void currentHealth(){
        healthCounter--;
       // System.out.println("Health: "+healthCounter);
    }//A method which increment the health counter, in relation to certain event/collision

    public void thwompdmg(){
       healthCounter = healthCounter-3;
       // System.out.println("Health: "+healthCounter);
    }

    public void enemy2dmg(){
        healthCounter--;
        //System.out.println("Health: "+healthCounter);
    }

    public void spikedmng(){
        healthCounter--;
        //System.out.println("Health: "+healthCounter);
    }

    public void addHealth(){
        healthCounter++;
        //System.out.println("Health: "+healthCounter);
    }//A method that allows the health counter to be called
    public int getCurrentHealth (){
        return healthCounter;
    }
    public void diamondCount(){
        diamond++;
        Score = Score+500;
        //System.out.println("Coin: "+getCoinCounter());
        //System.out.println("Score: "+getScore());

    }
    public void rubyCount(){
        Score =Score+30;
    }
    public void sapCount(){
        Score =Score+20;
    }
    public void emCount(){
        Score =Score+50;
    }

    public int getDiamond(){
        return diamond;
    }

    public void keyCounter(){
        key++;
        System.out.println("[*]You Obtained a Key you can now open the door[*]");
    }

    public int getKey(){
        return key;
    }


    //A method which creates a new object and destroy main character, used to create a death effect
    public StaticBody deathGetter(){
        destroy();
        PolygonShape deathEffect = new PolygonShape(1.81f,-2.17f,
                -1.04f,-2.24f,
                -1.04f,0.01f,
                0.71f,1.61f,
                1.78f,0.77f,
                1.82f,-2.11f);
        StaticBody death = new StaticBody(getWorld(),deathEffect);
        death.setPosition(new Vec2(getPosition()));
        death.addImage(new BodyImage("data/images/characterDeath.gif",5));
        return death;
    }
    public void setCoinCounter(int value){
        coinCounter = value;
    }
    public void setHeart(int numHeart){
        healthCounter = numHeart;
    }
    public void setScore(int scoreVal){
        Score = scoreVal;
    }
    public void setKey (int kc){
        key = kc;
    }
    public int getScore(){
        return Score;
    }
}

