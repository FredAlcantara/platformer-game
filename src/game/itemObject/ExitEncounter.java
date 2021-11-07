package game.itemObject;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.GameLevel;

public class ExitEncounter implements CollisionListener {

    private GameLevel level;

    /**
     * this is a class that allows users to go to the next level when the criteria is met.
     * @param level - pass through the Gamelevel class.
     */
    public ExitEncounter(GameLevel level){
        this.level = level;
    }
    @Override
    public void collide(CollisionEvent e) {
        //if the criteria in the level is met, go to next level else nothing happens.
        if (e.getOtherBody() instanceof Exit
                && level.isComplete()) {
            level.getGame().goToNextStage();
        }
    }
}
