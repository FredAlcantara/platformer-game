package game.projectiles;

import city.cs.engine.*;

public class Attack extends DynamicBody {
    private static final Shape circle = new CircleShape(0.1f);
    private static final BodyImage projectTile =
            new BodyImage("data/images/projectTileatt.png",2);

    public Attack(World w) {
        super(w,circle);
        addImage(projectTile);
    }
}

