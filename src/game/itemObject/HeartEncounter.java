package game.itemObject;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.MainCharacter;

public class HeartEncounter implements CollisionListener {

   private MainCharacter protagonist;

    /**
     * this is where the collision is made for the heart, if the user has 3 hearts do nothing.
     * @param protagonist - MainCharacter class is passed as it is used to retrieve the current heart.
     */
    public HeartEncounter( MainCharacter protagonist) {
        this.protagonist = protagonist;
    }

    @Override
    public void collide(CollisionEvent c) {
        //if heart is equal 2 or less increment it.
        if(c.getOtherBody() instanceof Heart){
            if(protagonist.getCurrentHealth() <=2){
                c.getOtherBody().destroy();
            }
        }

    }
}
