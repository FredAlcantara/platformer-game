package game.projectiles;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class BulletCollision implements CollisionListener {

    public BulletCollision(){

    }

    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() instanceof Bullet){
           e.getOtherBody().destroy();
        }
    }
}
