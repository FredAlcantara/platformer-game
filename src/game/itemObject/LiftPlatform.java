package game.itemObject;

import city.cs.engine.*;

public class LiftPlatform extends StaticBody {

    private static final Shape liftShape = new BoxShape(0.5f,0.5f);
    private BodyImage liftImage = new BodyImage("data/images/question_Block.gif",2);

    public LiftPlatform(World w) {
        super(w,liftShape);
        addImage(liftImage);




    }
}
