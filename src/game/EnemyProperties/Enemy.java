package game.EnemyProperties;
import city.cs.engine.*;
import game.GameLevel;
import game.levels.FinalLevel;
import game.levels.Level3;
import game.levels.Level2;
import game.levels.Level4;

public class Enemy extends Walker {
    //initialise the shape of the enemy
    private static final PolygonShape enemyShape = new PolygonShape(
            -0.81f, 1.86f,
            -1.68f, 0.23f,
            -1.38f, -2.45f,
            0.77f, -2.47f,
            1.37f, 0.76f,
            -0.67f, 1.87f);
    //create the image of the enemy.
    private BodyImage enemyImage =
            new BodyImage("data/images/enemy-unscreen.gif", 5);
    //initialise a integer variable for the enemies health, with the naming
    //convention of 'enemyHealth'
    static int enemyHealth;

    private GameLevel level;
    /**
     * Enemy class which provides the attributes of the enemy
     * (e.g shape(<code>PolygonShape</code>, image(<code>BodyImage</code>, etc).
     *
     * @param level - parameter in result passes through world, accessing this
     *              object to be initiated to the world.
     */
    public Enemy(GameLevel level) {
        super(level, enemyShape); //the shape of the enemy is added to the world
        this.level = level;
        addImage(enemyImage); // the image is add to the body
        enemyHealth = 3; // enemyHealth is initialised to 3
        level.addStepListener(new StepListener() {

            @Override
            public void preStep(StepEvent stepEvent) {


            }

            @Override
            public void postStep(StepEvent stepEvent) {
                //StepListener used to make enemy object to walk back and forth in a certain x-axis
                if(level instanceof Level2){
                    //in level if the enemy is found at -5 of the x-axis, walk left
                    if (Math.rint(getPosition().x) == -5) {
                        stopWalking();
                        startWalking(-2);
                        getImages().get(0).flipHorizontal();
                        //else if found at -19 in the x-axis walk right
                    } else if (Math.rint(getPosition().x) == -19) {
                        stopWalking();
                        startWalking(2);
                        getImages().get(0).flipHorizontal();
                    }
                }else if(level instanceof Level3){
                    //case of level 3 if the enemy is found at -16 x-axis, walk right
                    if (Math.rint(getPosition().x) == -16) {
                        stopWalking();
                        startWalking(2);
                        getImages().get(0).flipHorizontal();
                        //else if enemy is located at -8 of the x-axis, walk left
                    } else if (Math.rint(getPosition().x) == -8) {
                        stopWalking();
                        startWalking(-2);
                        getImages().get(0).flipHorizontal();
                    }
                }else if (level instanceof Level4){
                    //in level 4 if the enemy is located at -18 in the x-axis, walk to the right
                    if (Math.rint(getPosition().x) == -18) {
                        stopWalking();
                        startWalking(2);
                        getImages().get(0).flipHorizontal();
                        //else if found at 16 in x-axis, walk left
                    } else if (Math.rint(getPosition().x) == 16) {
                        stopWalking();
                        startWalking(-2);
                        getImages().get(0).flipHorizontal();
                    }
                }else if(level instanceof FinalLevel){
                    //in the final level if found at -18 x-axis walk right
                    if(Math.rint(getPosition().x) == -18){
                        stopWalking();
                        startWalking(2);
                        getImages().get(0).flipHorizontal();
                        //else walk left
                    }else if(Math.rint(getPosition().x)==0){
                        stopWalking();
                        startWalking(-2);
                        getImages().get(0).flipHorizontal();
                    }
                }
            }
        });

    }

    //a setter which decrements the enemies health when attacked
    public void setEnemyHealth() {
        enemyHealth--;
        System.out.println("Enemy Health: " + enemyHealth);

    }

    /**
     * A getter method which retrieve the health of the enemy
     * @return - returns the enemies health.
     */
    public int getEnemyHealth(){
        return enemyHealth;
    }

}

