package game.EnemyProperties;

import city.cs.engine.*;

public class Spike extends DynamicBody {

    private static final Shape spikeShape = new BoxShape(9,0.5f);
    private BodyImage spikeImage =
            new BodyImage("data/images/spike.png",1);

    /**
     * Another enemy object that is created, which contains a body and image
     * @param w - passes through World to initialise the object to the world.
     */
    public Spike(World w) {
        super(w,spikeShape); //the object is added to world
        addImage(spikeImage);//a image is applied to this object
    }
}
