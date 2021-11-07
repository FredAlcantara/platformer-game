package game.itemObject;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.projectiles.Bullet;

public class WallCollision implements CollisionListener {

    private Wall wall;

    public WallCollision(Wall wall){
        this.wall = wall;
    }

    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() instanceof Bullet){
            e.getOtherBody().destroy();
        }

    }
}
