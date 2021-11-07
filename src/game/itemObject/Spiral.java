package game.itemObject;

import city.cs.engine.*;

public class Spiral extends StaticBody {
    private static final Shape spiralShape = new CircleShape(0.5f);
    private BodyImage spiralImage = new BodyImage("data/images/spiral.png",4);
    public Spiral(World w) {
        super(w,spiralShape);
        addImage(spiralImage);
    }
}
