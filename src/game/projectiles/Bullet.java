package game.projectiles;
import city.cs.engine.*;
import game.GameLevel;
import org.jbox2d.common.Vec2;

public class Bullet extends DynamicBody{

    private static final Shape bulletShape = new CircleShape(0.1f);
    private static final BodyImage bulletImage =
            new BodyImage("data/images/turretbullet.gif",2);
    public Bullet(GameLevel w) {
        super(w,bulletShape);
        addImage(bulletImage).flipHorizontal();
        setGravityScale(0);
        applyImpulse(new Vec2(-0.3f, 0));
        setPosition(new Vec2(w.getBoss().getPosition().x-3,w.getBoss().getPosition().y));
        if(w.getBoss().getBossHealth()<=0){
            destroy();
        }
    }
}
