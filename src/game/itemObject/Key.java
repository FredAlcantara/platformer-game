package game.itemObject;

import city.cs.engine.*;

public class Key extends DynamicBody {

    private static final Shape keyShape = new CircleShape(0.5f);
    private static final BodyImage keyImage =
            new BodyImage("data/images/pickUps/key.png",2);

    public Key(World w) {
        super(w,keyShape);
        addImage(keyImage);
    }
}
