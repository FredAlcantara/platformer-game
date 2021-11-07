package game.EnemyProperties;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.MainCharacter;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class EnemyCollider implements CollisionListener {

    private MainCharacter protagonist;

    /**
     * EnemyCollider class for addressing collision with
     * the Enemy class such as decrementing of health
     * assigned to main character.
     * @param p parameter takes in MainCharacter.
     */
    public EnemyCollider(MainCharacter p){
        this.protagonist = p;
    }

    @Override
    public void collide(CollisionEvent enemyCol) {
        //When main character collides decrement health
        //Create a knockback effect with the use of move(new V2(...)
        if(enemyCol.getOtherBody() instanceof Enemy){
            protagonist.currentHealth();
            protagonist.setLinearVelocity(
                    new Vec2((float)(Math.random()*10.1),
                            (float)(Math.random()*10)));
             //When health is equal to zero get position, destroy object
            //Call the death method to initialised the new object
            if(protagonist.getCurrentHealth()<=0){
                protagonist.getPosition();
                protagonist.deathGetter();
                //When enemy collides and health count is 0 call death method
            }else if(enemyCol.getOtherBody() instanceof MainCharacter){
                protagonist.deathGetter();
            }
        }if(enemyCol.getOtherBody() instanceof  Thwomp){
            protagonist.thwompdmg();
            if(protagonist.getCurrentHealth()<=0) {
                protagonist.getPosition();
                protagonist.deathGetter();
                Timer timer = new Timer(2000,e -> protagonist.deathGetter().destroy());
                timer.start();
            }
        }if (enemyCol.getOtherBody() instanceof Enemy2) {
            protagonist.enemy2dmg();
            protagonist.setLinearVelocity(
                    new Vec2((float) (Math.random() * 10.1),
                            (float) (Math.random() * 10)));
            if (protagonist.getCurrentHealth() <= 0) {
                protagonist.getPosition();
                protagonist.deathGetter();
                //When enemy collides and health count is 0 call death method
            } else if (enemyCol.getOtherBody() instanceof MainCharacter) {
                protagonist.deathGetter();
            }
        }if(enemyCol.getOtherBody() instanceof Spike){
            protagonist.spikedmng();
            protagonist.setLinearVelocity(
                    new Vec2((float) (Math.random() * 10.1),
                            (float) (Math.random() * 10)));
            if (protagonist.getCurrentHealth() <= 0) {
                protagonist.getPosition();
                protagonist.deathGetter();
                //When enemy collides and health count is 0 call death method
            } else if (enemyCol.getOtherBody() instanceof MainCharacter) {
                protagonist.deathGetter();
            }
        }
    }
}
