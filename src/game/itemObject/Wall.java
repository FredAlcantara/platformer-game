package game.itemObject;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

public class Wall extends StaticBody {

    private static final Shape wallShape = new BoxShape(0.5f,50);

    public Wall(World w) {
        super(w,wallShape);
    }
}
