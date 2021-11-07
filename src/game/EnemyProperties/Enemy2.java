package game.EnemyProperties;

import city.cs.engine.*;
import game.GameLevel;
import game.levels.FinalLevel;
import game.levels.Level3;
import game.levels.Level4;

public class Enemy2 extends Walker {
    private GameLevel level;
    private int e2health;
    private static final PolygonShape enemy2Shape = new PolygonShape(
            -0.94f,-1.76f,
            1.35f,-1.73f,
            1.3f,-0.07f,
            -0.63f,0.91f,
            -1.24f,0.01f,
            -1.11f,-1.7f
    );

    private BodyImage enemy2Image =
            new BodyImage("data/images/enemySecond.gif",5.5f);
    /**
     * Enemy2 class which provides the attributes of the enemy
     * (e.g shape PolygonShape, image(BodyImage, etc).
     *
     * @param level - parameter in result passes through world, accessing this
     *              object to be initiated to the world.
     */
    public Enemy2(GameLevel level) {
        super(level,enemy2Shape);
        this.level = level;
        e2health = 3;
        addImage(enemy2Image);
        level.addStepListener(new StepListener() {
            @Override
            public void preStep(StepEvent stepEvent) {

            }

            @Override
            public void postStep(StepEvent stepEvent) {
                if (level instanceof Level3) {
                    //if validates the location of enemy2, to see if it needs to walk left or right
                    if (Math.rint(getPosition().x) == 7) {
                        stopWalking();
                        startWalking(2);
                        getImages().get(0).flipHorizontal();
                    } else if (Math.rint(getPosition().x) == 19) {
                        stopWalking();
                        startWalking(-2);
                        getImages().get(0).flipHorizontal();
                       }
                    }if(level instanceof Level4) {
                    //if validates the location of enemy2, to see if it needs to walk left or right
                    if (Math.rint(getPosition().x) == 18) {
                        stopWalking();
                        startWalking(-2);
                        getImages().get(0).flipHorizontal();
                    }else if (Math.rint(getPosition().x) == -18) {
                        stopWalking();
                        startWalking(2);
                        getImages().get(0).flipHorizontal();
                    }
                }if(level instanceof FinalLevel){
                    //if validates the location of enemy2, to see if it needs to walk left or right
                    if(Math.rint(getPosition().x)==5){
                        stopWalking();
                        startWalking(-2);
                        getImages().get(0).flipHorizontal();
                    }else if (Math.rint(getPosition().x)==-13){
                        stopWalking();
                        startWalking(2);
                        getImages().get(0).flipHorizontal();
                    }
                }
            }
        });
    }

    //a setter method which decrements the objects health when attacked
    public void setE2health(){
        e2health--;
        System.out.println("Enemy Health: " + e2health);
    }
    /**
     * A getter method which retrieve the health of the enemy
     * @return - returns the enemies health.
     */
    public int getE2health(){
        return e2health;
    }


}