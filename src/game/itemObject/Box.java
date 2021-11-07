package game.itemObject;

import city.cs.engine.*;

public class Box extends Walker {
    private static final Shape boxShape = new BoxShape(1.5f,1.5f);
    private static final BodyImage boxImage =
            new BodyImage("data/images/box.png",3.5f);
    public Box(World w) {
        super(w,boxShape);
        addImage(boxImage);
    }
}
