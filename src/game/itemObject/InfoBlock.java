package game.itemObject;

import city.cs.engine.*;

public class InfoBlock extends DynamicBody {
    private static final Shape infoShape = new BoxShape(0.5f,0.5f);
    private BodyImage infoImage =
            new BodyImage("data/images/infoBlock.png",1.5f);
    public InfoBlock(World w) {
        super(w);
        addImage(infoImage);
        Sensor s = new Sensor(this,infoShape);
        s.addSensorListener(new InfoBlockSensor(this));
        setGravityScale(0);

    }
}
