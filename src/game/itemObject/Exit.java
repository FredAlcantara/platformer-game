package game.itemObject;

import city.cs.engine.*;
import game.GameLevel;

public class Exit extends StaticBody {

    private GameLevel level;
    private static final Shape exitShape = new CircleShape(1);
    private static final BodyImage exitImage = new BodyImage("data/images/pickUps/door.png",4);

    public Exit(GameLevel level) {
        super(level);
        this.level = level;
        new GhostlyFixture(this,exitShape);
        Sensor s = new Sensor(this,exitShape);
        s.addSensorListener(new ExitSensor(this,level));
        addImage(exitImage);

    }
    public GameLevel getLevel(){
        return level;
    }
}


